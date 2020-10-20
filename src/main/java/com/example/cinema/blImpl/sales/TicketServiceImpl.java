package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.blImpl.management.hall.HallServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.blImpl.promotion.activity.ActivityServiceForBl;
import com.example.cinema.blImpl.promotion.coupon.CouponServiceForBl;
import com.example.cinema.blImpl.promotion.vip.VIPServiceForBl;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.*;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liying on 2019/4/16.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Resource
    TicketMapper ticketMapper;
    @Autowired
    ScheduleServiceForBl scheduleService;
    @Autowired
    HallServiceForBl hallService;
    @Autowired
    CouponServiceForBl couponService;
    @Autowired
    VIPServiceForBl vipService;
    @Autowired
    ActivityServiceForBl activityService;

    @Override
    public ResponseVO getBySchedule(int scheduleId) {
        try {
            List<Ticket> tickets = ticketMapper.selectTicketsBySchedule(scheduleId);
            ScheduleItem schedule = scheduleService.getScheduleItemById(scheduleId);
            Hall hall = hallService.getHallById(schedule.getHallId());
            int[][] seats = new int[hall.getRow()][hall.getColumn()];
            tickets.forEach(ticket -> seats[ticket.getRowIndex()][ticket.getColumnIndex()] = 1);
            ScheduleWithSeatVO scheduleWithSeatVO = new ScheduleWithSeatVO();
            scheduleWithSeatVO.setScheduleItem(schedule);
            scheduleWithSeatVO.setSeats(seats);
            return ResponseVO.buildSuccess(scheduleWithSeatVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    @Transactional
    public ResponseVO addTicket(TicketForm ticketForm) {
        try {
            int userId = ticketForm.getUserId();
            int scheduleId = ticketForm.getScheduleId();
            int column;
            int row;
            TicketWithCouponVO ticketWithCouponVO = new TicketWithCouponVO();
            double total = 0.0;
            List<TicketVO> ticketVOList = new ArrayList<>();
            List<Coupon> coupons = couponService.getCouponByUserId(userId);
            List<Activity> activities = activityService.getAllAcitivities();
            for (SeatForm seatForm : ticketForm.getSeats()) {
                column = seatForm.getColumnIndex();
                row = seatForm.getRowIndex();
                Ticket ticket = new Ticket();
                ticket.setUserId(userId);
                ticket.setScheduleId(scheduleId);
                ticket.setColumnIndex(column);
                ticket.setRowIndex(row);
                ticket.setState(0);
                ticketMapper.insertTicket(ticket);
                ticketVOList.add(ticketMapper.selectTicketByScheduleIdAndSeat(scheduleId, column, row).getVO());
                total += scheduleService.getScheduleItemById(scheduleId).getFare();
            }
            ticketWithCouponVO.setTicketVOList(ticketVOList);
            ticketWithCouponVO.setTotal(total);
            ticketWithCouponVO.setCoupons(coupons);
            ticketWithCouponVO.setActivities(activities);
            return ResponseVO.buildSuccess(ticketWithCouponVO);
        } catch (Exception e) {
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    @Transactional
    public ResponseVO completeTicket(List<Integer> id, int couponId) {
        return pay(id, couponId, false);
    }

    @Override
    @Transactional
    public ResponseVO completeByVIPCard(List<Integer> id, int couponId) {
        return pay(id, couponId, true);
    }

    @Override
    public ResponseVO getTicketByUser(int userId) {
        try {
            return ResponseVO.buildSuccess(ticketList2TicketVOList(ticketMapper.selectTicketByUser(userId)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO cancelTicket(List<Integer> id) {
        try {
            for (int ticketId : id) {
                if (ticketMapper.selectTicketById(ticketId).getState() == 0) {
                    ticketMapper.updateTicketState(ticketId, 2);
                }
            }
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            return ResponseVO.buildFailure("失败");
        }
    }

    private List<TicketVO> ticketList2TicketVOList(List<Ticket> ticketList) {
        List<TicketVO> ticketVOList = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            ticketVOList.add(ticket.getVO());
        }
        return ticketVOList;
    }

    private ResponseVO pay(List<Integer> id, int couponId, boolean byVIP) {
        try {
            //获取电影票
            List<Ticket> ticketList = new ArrayList<>();
            for (int i : id) {
                ticketList.add(ticketMapper.selectTicketById(i));
            }
            //获取优惠券
            Coupon coupon;
            if (couponId == 0) {
                coupon = null;
            } else {
                coupon = couponService.getCouponById(couponId);
            }
            //获取排片信息
            List<TicketWithScheduleVO> tickets = new ArrayList<>();
            for (Ticket ticket : ticketList) {
                TicketWithScheduleVO ticketWithScheduleVO = ticket.getWithScheduleVO();
                ticketWithScheduleVO.setSchedule(scheduleService.getScheduleItemById(ticket.getScheduleId()));
                tickets.add(ticketWithScheduleVO);
            }
            //获取用户ID和VIP卡
            int userId = ticketList.get(0).getUserId();
            VIPCard vipCard = vipService.getVIPCardByUserId(userId);
            //获取所有活动
            List<Activity> activityList = activityService.getAllAcitivities();

            //判断电影票是否正确（状态），如果状态不是0抛出异常
            for (Ticket ticket : ticketList) {
                if (ticket.getState() != 0) {
                    throw new Exception();
                }
            }
            //判断优惠券是否正确（时间、总价）
            if (coupon != null) {
                double price = 0.0;
                for (TicketWithScheduleVO ticket : tickets) {
                    Timestamp timestamp = ticket.getTime();
                    if (timestamp.compareTo(coupon.getStartTime()) >= 0 && timestamp.compareTo(coupon.getEndTime()) <= 0) {
                        price += ticket.getSchedule().getFare();
                    }
                }
                if (price < coupon.getTargetAmount()) {
                    coupon = null;
                }
            }
            //计算价格
            double price = 0.0;
            for (TicketWithScheduleVO ticket : tickets) {
                price += ticket.getSchedule().getFare();
            }
            if (coupon != null) {
                price -= coupon.getDiscountAmount();
            }
            //判断VIP卡是否正确（余额），如果使用vip卡且余额不足抛出异常
            if (byVIP) {
                if (vipCard == null || price > vipCard.getBalance()) {
                    throw new Exception();
                }
            }
            //判断能否获得优惠券
            List<Activity> joinedActivity = new ArrayList<>();
            for (Activity activity : activityList) {
                List<Integer> idList = activity.getMovieList().stream().map(Movie::getId).collect(Collectors.toList());
                for (TicketWithScheduleVO ticket : tickets) {
                    int movieId = ticket.getSchedule().getMovieId();
                    if (idList.contains(movieId) || idList.size() == 0) {
                        Timestamp timestamp = ticket.getTime();
                        if (timestamp.compareTo(activity.getStartTime()) >= 0 && timestamp.compareTo(activity.getEndTime()) <= 0) {
                            joinedActivity.add(activity);
                            break;
                        }
                    }
                }
            }

            //改变电影票状态
            for (Ticket ticket : ticketList) {
                ticketMapper.updateTicketState(ticket.getId(), 1);
            }
            //去掉符合条件的优惠券
            if (coupon != null) {
                couponService.removeCoupon(coupon.getId(), userId);
            }
            //VIP卡付款
            if (byVIP) {
                vipService.payByCard(vipCard.getId(), vipCard.getBalance() - price);
            }
            //增加可获得的优惠券
            for (Activity activity : joinedActivity) {
                couponService.addCoupon(activity.getCoupon().getId(), userId);
            }

            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

}
