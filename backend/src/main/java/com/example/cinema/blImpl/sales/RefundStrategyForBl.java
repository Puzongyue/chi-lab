package com.example.cinema.blImpl.sales;

import com.example.cinema.vo.RefundTicketStrategyForm;

import java.util.Date;
import java.util.List;

public interface RefundStrategyForBl {
    /**
     * 得到所有退票策略
     * @return
     */
    List<RefundTicketStrategyForm> getRefundStrategy();

    /**
     * 得到最优退票策略的折扣，如果不能退票，则返回0
     * @return
     */
    double getBestRefundStrategy(Date date);
}
