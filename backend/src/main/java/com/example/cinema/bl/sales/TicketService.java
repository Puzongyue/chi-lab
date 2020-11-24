package com.example.cinema.bl.sales;

import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.TicketForm;
import com.example.cinema.vo.TicketVO;

import java.util.List;


/**
 * Created by liying on 2019/4/16.
 */
public interface TicketService {
    /**
     * TODO:锁座【增加票但状态为未付款】
     * 订单状态：
     * 0：未完成 1：已完成 2:已失效
     * @param ticketForm
     * @return
     */
    ResponseVO addTicket(TicketForm ticketForm);

    /**
     * TODO:完成购票【不使用会员卡】流程包括校验优惠券和根据优惠活动赠送优惠券
     *
     * @param id
     * @param couponId
     * @return
     */
    ResponseVO completeTicket(List<Integer> id, int couponId);

    /**
     * 获得该场次的被锁座位和场次信息
     *
     * @param scheduleId
     * @return
     */
    ResponseVO getBySchedule(int scheduleId);

    /**
     * TODO:获得用户买过的票
     *
     * @param userId
     * @return
     */
    ResponseVO getTicketByUser(int userId);

    /**
     * TODO:完成购票【使用会员卡】流程包括会员卡扣费、校验优惠券和根据优惠活动赠送优惠券
     *
     * @param id
     * @param couponId
     * @return
     */
    ResponseVO completeByVIPCard(List<Integer> id, int couponId);

    /**
     * TODO:取消锁座（只有状态是"锁定中"的可以取消）
     *
     * @param id
     * @return
     */
    ResponseVO cancelTicket(List<Integer> id);

    /**
     * 得到所有的退票策略
     * @return
     */
    ResponseVO getRefundStrategies();
    /**
     * 退票
     * @param ticketId
     * @return
     */
    ResponseVO refundTickets(List<Integer> ticketId);

    /**
     * 得到用户所有退的票
     */
    ResponseVO getRefundedTickets(int userId);

    /**
     * 得到再支付票需要的相关信息：观众可用优惠券，所有优惠活动，总价
     *
     * @return
     */
    ResponseVO getInfoOfUnpaidTickets (int userId,int scheduleId);
}
