package com.example.cinema.blImpl.promotion;

import com.example.cinema.vo.ChargeRecordUserVO;

public interface ChargeRecordServiceForBl {
    /**
     * 插入充值记录
     * @param chargeRecordUserVO
     */
    void insertChargeRecord(ChargeRecordUserVO chargeRecordUserVO);
}
