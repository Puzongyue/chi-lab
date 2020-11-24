package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.schedule.MovieServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.data.promotion.CouponMapper;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.*;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    ScheduleServiceForBl scheduleService;
    @Autowired
    HallServiceForBl hallService;
    //下面是添加的
    @Autowired
    private CouponService couponService;
    @Autowired
    private MovieServiceForBl movieServiceForBl;
    @Autowired
    private CouponServiceForBl couponServiceForBl;
    @Autowired
    private ActivityServiceForBl activityServiceForBl;
    @Autowired
    private VipServiceForBl vipServiceForBl;
    @Autowired
    private VIPService vipService;
    @Autowired
    private ConsumeRecordServiceForBl consumeRecordServiceForBl;
    @Autowired
    private RefundStrategyForBl refundStrategyForBl;
    /**
     * 每一个schedule绑定一系列票，从而确定哪些座位被锁定
     * @param scheduleId
     * @return
     */
    private int[][] getLockedSeats(int scheduleId){
        List<Ticket> tickets = ticketMapper.selectTicketsBySchedule(scheduleId);
        ScheduleItem schedule=scheduleService.getScheduleItemById(scheduleId);
        Hall hall=hallService.getHallById(schedule.getHallId());
        int[][] seats=new int[hall.getRow()][hall.getColumn()];
        //每一个ticket都对应一个座位被锁定。
        tickets.stream().forEach(ticket -> {
            seats[ticket.getRowIndex()][ticket.getColumnIndex()]=1;
        });
        return  seats;
    }


    /**
     * TODO:锁座【增加票但状态为未付款】
     * 订单状态：
     * 0：未完成 1：已完成 2:已失效
     * @param ticketForm
     * @return
     */
    @Override
    @Transactional
    public ResponseVO addTicket(TicketForm ticketForm) {

        List<SeatForm> seats=ticketForm.getSeats();
        List<TicketVO> ticketVOS=new ArrayList<>();
        for (int i = 0; i < seats.size(); i++) {
            int[][] lockedSeats=getLockedSeats(ticketForm.getScheduleId());
            if (lockedSeats[seats.get(i).getRowIndex()][seats.get(i).getColumnIndex()]==1){
                return ResponseVO.buildFailure("座位已经被锁定");
            }
            Ticket ticket=new Ticket();
            ticket.setUserId(ticketForm.getUserId());
            ticket.setScheduleId(ticketForm.getScheduleId());
            ticket.setColumnIndex(seats.get(i).getColumnIndex());
            ticket.setRowIndex(seats.get(i).getRowIndex());
            ticket.setState(0);
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            ticket.setTime(timestamp);
            ticketMapper.insertTicket(ticket);
            TicketVO ticketVO=ticketMapper.selectTicketByScheduleIdAndSeat(ticket.getScheduleId(),ticket.getColumnIndex(),ticket.getRowIndex()).getVO();
            ticketVOS.add(ticketVO);
        }

        try {
            TicketWithCouponVO ticketWithCouponVO=getTicketWithCouponVO(ticketVOS);
            return ResponseVO.buildSuccess(ticketWithCouponVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    /**
     * TODO:取消锁座（只有状态是"锁定中"的可以取消）
     *
     * @param ticketId
     * @return
     */
    @Override
    public ResponseVO cancelTicket(List<Integer> ticketId) {
        try {
            for (int i = 0; i < ticketId.size(); i++) {
                Ticket ticket=ticketMapper.selectTicketById(ticketId.get(i));
                if (ticket==null||ticket.getState()==2){
                    return  ResponseVO.buildFailure("座位未被锁定");
                }
                else {
                    ticketMapper.deleteTicket(ticketId.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
        return ResponseVO.buildSuccess();
    }

    /**
     * 完成校验优惠券（初步定为仅仅删除使用过的优惠券）和赠送优惠券
     * @param ticketId
     * @param couponId
     * @return
     */
    private String checkAndGiveCoupon(List<Integer> ticketId, int couponId){
        String content="";
        Ticket ticket=ticketMapper.selectTicketById(ticketId.get(0));
        if (couponId==0) {
            ;
        }
        else {
            //删除优惠券
            couponServiceForBl.deleteCoupon(couponId,ticket.getUserId());
        }
        int scheduleId=ticket.getScheduleId();
        ScheduleItem scheduleItem=scheduleService.getScheduleItemById(scheduleId);
        Movie movie=movieServiceForBl.getMovieById(scheduleItem.getMovieId());
        List<Activity> activities=activityServiceForBl.selectActivities();
        content="用户未获得优惠券";
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i).getMovieList()==null||activities.get(i).getMovieList().size()==0){
                couponService.issueCoupon(activities.get(i).getCoupon().getId(),ticket.getUserId());
                content="用户获得优惠券";
            }
            else if (containMovie(activities.get(i).getMovieList(),movie)){
                couponService.issueCoupon(activities.get(i).getCoupon().getId(),ticket.getUserId());
                content="用户获得优惠券";
            }
            else {
                ;
            }
        }
        return content;

    }

    private boolean containMovie(List<Movie> movies,Movie movie){
        ArrayList<Integer> ids=new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            ids.add(movies.get(i).getId());
        }
        return ids.contains(movie.getId());
    }

    /**
     * 根据付款方式创建一个消费记录用于插入数据库
     * @param way 1代表会员卡支付，0代表银行卡
     * @return
     */
    private ConsumeRecordUserVO creatConsumeRecord(int way,List<Integer> ticketId,int couponId){
        ConsumeRecordUserVO consumeRecordUserVO=new ConsumeRecordUserVO();

        double amout=0;//票价
        String seat=""+ticketId.size()+"张 ";
        int scheduleID=0;
        int userID=0;
        for (int i = 0; i < ticketId.size(); i++) {
            Ticket ticket=ticketMapper.selectTicketById(ticketId.get(i));
            scheduleID=ticket.getScheduleId();
            userID=ticket.getUserId();
            ScheduleItem scheduleItem=scheduleService.getScheduleItemById(ticket.getScheduleId());
            seat=seat+(ticket.getRowIndex()+1)+"排"+(ticket.getColumnIndex()+1)+"座 ";
            amout=scheduleItem.getFare();
        }
        if (couponId==0){
            consumeRecordUserVO.setAmount(ticketId.size()*amout);
        }
        else {
            consumeRecordUserVO.setAmount(ticketId.size()*amout-couponServiceForBl.getCouponById(couponId).getDiscountAmount());
        }
        consumeRecordUserVO.setUserID(userID);
        consumeRecordUserVO.setConsumeTime(new Timestamp(System.currentTimeMillis()));
        consumeRecordUserVO.setWay(way);
        consumeRecordUserVO.setSeat(seat);
        consumeRecordUserVO.setScheduleID(scheduleID);
        return consumeRecordUserVO;
    }

    /**
     * 得到这笔订单最终实际要花多少钱
     * @param ticketId
     * @param couponId
     * @return
     */
    private double getTotalCosume(List<Integer> ticketId, int couponId){
        Ticket ticket=ticketMapper.selectTicketById(ticketId.get(0));
        ScheduleItem scheduleItem=scheduleService.getScheduleItemById(ticket.getScheduleId());
        double sum;
        if (couponId==0){
            sum=ticketId.size()*scheduleItem.getFare();
        }
        else {
            Coupon coupon=couponServiceForBl.getCouponById(couponId);
            sum=ticketId.size()*scheduleItem.getFare()-coupon.getDiscountAmount();
        }
        return sum;
    }

    /**
     * TODO:完成购票【不使用会员卡】流程包括校验优惠券(初步定为仅仅删除优惠券）和根据优惠活动赠送优惠券
     *
     * 在sales包添加了一个接口。CouponServiceImpl实现了它。（仿照schedule）
     *
     * @param ticketId
     * @param couponId
     * @return
     */
    @Override
    @Transactional
    public ResponseVO completeTicket(List<Integer> ticketId, int couponId) {
        try {
            consumeRecordServiceForBl.insertConsumeRecord(creatConsumeRecord(0,ticketId,couponId));
            double each=getTotalCosume(ticketId,couponId)/(double)ticketId.size();//平均每张票多少钱
            for (int i = 0; i < ticketId.size(); i++) {
                ticketMapper.updateTicketConsume(ticketId.get(i),each);
                ticketMapper.updateTicketWay(ticketId.get(i),0);
            }
            String content=checkAndGiveCoupon(ticketId,couponId);
            for (int i = 0; i < ticketId.size(); i++) {
                ticketMapper.updateTicketState(ticketId.get(i),1);
            }
            return ResponseVO.buildSuccess(content);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }



    /**
     * TODO:完成购票【使用会员卡】流程包括会员卡扣费、校验优惠券和根据优惠活动赠送优惠券
     *
     * @param ticketId
     * @param couponId
     * @return
     */
    @Override
    @Transactional
    public ResponseVO completeByVIPCard(List<Integer> ticketId, int couponId) {
        try{
            Ticket ticket=ticketMapper.selectTicketById(ticketId.get(0));
            VIPCard vipCard=(VIPCard)vipService.getCardByUserId(ticket.getUserId()).getContent();
            double sum=getTotalCosume(ticketId,couponId);
            boolean isEnough=vipServiceForBl.payByVipCard(vipCard.getId(),sum);
            if (isEnough){
                double each=getTotalCosume(ticketId,couponId)/(double)ticketId.size();
                for (int i = 0; i < ticketId.size(); i++) {
                    ticketMapper.updateTicketConsume(ticketId.get(i),each);
                    ticketMapper.updateTicketWay(ticketId.get(i),1);
                }
                consumeRecordServiceForBl.insertConsumeRecord(creatConsumeRecord(1,ticketId,couponId));
                vipServiceForBl.updateVipConsume(vipCard.getId(),vipCard.getConsume()+sum);
                checkAndGiveCoupon(ticketId,couponId);
                for (int i = 0; i < ticketId.size(); i++) {
                    ticketMapper.updateTicketState(ticketId.get(i),1);
                }
                return ResponseVO.buildSuccess();
            }
            else {
                return ResponseVO.buildFailure("会员卡余额不足");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }


    }

    /**
     * 获得该场次的被锁座位和场次信息
     *
     * @param scheduleId
     * @return
     */
    @Override
    public ResponseVO getBySchedule(int scheduleId) {
        try {
            int[][] seats=getLockedSeats(scheduleId);
            ScheduleWithSeatVO scheduleWithSeatVO=new ScheduleWithSeatVO();
            scheduleWithSeatVO.setScheduleItem(scheduleService.getScheduleItemById(scheduleId));
            scheduleWithSeatVO.setSeats(seats);
            return ResponseVO.buildSuccess(scheduleWithSeatVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }


    /**
     * TODO:获得用户买过的票
     *
     * @param userId
     * @return
     */
    @Override
    public ResponseVO getTicketByUser(int userId) {
        List<Ticket> tickets=ticketMapper.selectTicketByUser(userId);
        if (tickets==null){
            return ResponseVO.buildFailure("失败");
        }
        else {
            List<TicketVO> ticketVOS=new ArrayList<>();
            for (int i = 0; i < tickets.size(); i++) {
                ticketVOS.add(tickets.get(i).getVO());
            }
            return ResponseVO.buildSuccess(ticketVOS);
        }

    }

    @Override
    public ResponseVO getRefundStrategies(){
        try {
            List<RefundTicketStrategyForm> refundTicketStrategyForms = refundStrategyForBl.getRefundStrategy();
            if (refundTicketStrategyForms==null){
                return ResponseVO.buildFailure("当前未发布退票策略");
            }
            else if (refundTicketStrategyForms.size()==0){
                return ResponseVO.buildFailure("当前未发布退票策略");
            }
            else{
                return ResponseVO.buildSuccess(refundTicketStrategyForms);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
    @Override
    public ResponseVO refundTickets(List<Integer> ticketId){
        try{

            ScheduleItem scheduleItem=scheduleService.getScheduleItemById(ticketMapper.selectTicketById(ticketId.get(0)).getScheduleId());
            double rate=refundStrategyForBl.getBestRefundStrategy(scheduleItem.getStartTime());
            if (rate==0){
                return ResponseVO.buildFailure("不可以退票");
            }
            else {
                double total=0;
                for (int i = 0; i < ticketId.size(); i++) {
                    Ticket ticket=ticketMapper.selectTicketById(ticketId.get(i));
                    total=total+ticket.getConsume();
                    if (ticket.getWay()==1){
                        VIPCard vipCard=(VIPCard)vipService.getCardByUserId(ticket.getUserId()).getContent();
                        vipServiceForBl.updateVipBalance(vipCard.getId(),rate*ticket.getConsume());
                        vipServiceForBl.updateVipConsume(vipCard.getId(),vipCard.getConsume()-ticket.getConsume());
                    }
                    ticketMapper.deleteTicket(ticketId.get(i));
                    ticketMapper.insertRefundedTicket(ticket);
                }
                double out=rate*total;//退还给用户的总金额
                return ResponseVO.buildSuccess(out);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
    public ResponseVO getRefundedTickets(int userId){
        try{
            List<Ticket> tickets=ticketMapper.selectRefundedTicketByUser(userId);
            if (tickets==null||tickets.size()==0){
                return ResponseVO.buildFailure("没有退票记录");
            }
            else {
                List<TicketVO> ticketVOS=new ArrayList<>();
                for (int i = 0; i < tickets.size(); i++) {
                    ticketVOS.add(tickets.get(i).getVO());
                }
                return ResponseVO.buildSuccess(ticketVOS);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    /**
     * 得到再支付票需要的相关信息：观众可用优惠券，所有优惠活动，总价
     *
     * @return
     */
    @Override
    public ResponseVO getInfoOfUnpaidTickets (int userId,int scheduleId){
        try {
            List<Ticket> tickets=ticketMapper.selectTicketByUserScheduleAndState(userId,scheduleId);
            if(tickets==null||tickets.size()==0){
                return ResponseVO.buildFailure("由于您未在15分钟内支付，订单已经过期");
            }
            List<TicketVO> ticketVOS=new ArrayList<>();
            for (int i = 0; i < tickets.size(); i++) {
                ticketVOS.add(tickets.get(i).getVO());
            }
            TicketWithCouponVO ticketWithCouponVO=getTicketWithCouponVO(ticketVOS);
            return ResponseVO.buildSuccess(ticketWithCouponVO);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    private TicketWithCouponVO getTicketWithCouponVO(List<TicketVO> ticketVOS){
        TicketWithCouponVO ticketWithCouponVO=new TicketWithCouponVO();
        ticketWithCouponVO.setTicketVOList(ticketVOS);
        double total=ticketVOS.size()*scheduleService.getScheduleItemById(ticketVOS.get(0).getScheduleId()).getFare();
        ticketWithCouponVO.setTotal(total);
        ticketWithCouponVO.setCoupons(couponServiceForBl.selectCouponByUserAndAmount(total,ticketVOS.get(0).getUserId()));
        ticketWithCouponVO.setActivities(activityServiceForBl.selectActivities());
        return ticketWithCouponVO;
    }






}
