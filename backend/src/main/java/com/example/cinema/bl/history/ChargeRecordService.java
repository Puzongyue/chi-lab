package com.example.cinema.bl.history;

import com.example.cinema.vo.ResponseVO;
/**
 * @author lyp
 * created on 2019.05.26
 */
public interface ChargeRecordService {
    /**
     * 查看历史充值记录（根据用户id）
     * @param userId
     * @return
     */
    ResponseVO searchChargeRecordByUserId(Integer userId);
}
