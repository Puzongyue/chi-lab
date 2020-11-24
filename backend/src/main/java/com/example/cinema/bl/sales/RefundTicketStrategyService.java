package com.example.cinema.bl.sales;

import com.example.cinema.vo.RefundTicketStrategyForm;
import com.example.cinema.vo.ResponseVO;

/**
 * @author lyp
 * created on 2019.05.26
 */
public interface RefundTicketStrategyService {
    /**
     * 新增退票策略
     * @param refundTicketStrategyForm
     * @return
     */
    ResponseVO publishRefundTicketStrategy(RefundTicketStrategyForm refundTicketStrategyForm);

    /**
     * 修改退票策略
     * @param refundTicketStrategyForm
     * @return
     */
    ResponseVO updateRefundTicketStrategy(RefundTicketStrategyForm refundTicketStrategyForm);

    /**
     * 得到所有的退票策略
     * @return
     */
    ResponseVO getAllRefundTicketStrategies();
}
