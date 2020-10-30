package com.example.cinema.blImpl.sales;

import com.example.cinema.vo.ConsumeRecordUserVO;

public interface ConsumeRecordServiceForBl {
    /**
     * 插入消费记录
     * @param consumeRecordUserVO
     */
    void insertConsumeRecord(ConsumeRecordUserVO consumeRecordUserVO);
}
