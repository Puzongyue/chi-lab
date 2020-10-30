package com.example.cinema.data.sales;

import com.example.cinema.po.RefundTicketStrategy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RefundTicketMapper {
    /**
     *
     * @return 返回所有的退票策略
     */
    List<RefundTicketStrategy> getAllStragegies();

    /**
     * 按照id查找退票策略
     * @param refundTicketId
     * @return
     */
    RefundTicketStrategy getStrategyById(int refundTicketId);

    /**
     * @return 返回生成的退票策略的id
     */
    int insertRefundStrategy(RefundTicketStrategy refundTicketStrategy);

    void deleteRefundStrategy(int refundTicketId);

    void updateRefundStrategy(RefundTicketStrategy refundTicketStrategy);
    int getMaxKey();
}
