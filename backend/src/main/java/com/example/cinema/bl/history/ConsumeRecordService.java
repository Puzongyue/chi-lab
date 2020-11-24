package com.example.cinema.bl.history;

import com.example.cinema.vo.ResponseVO;
/**
 * @author lyp
 * created on 2019.05.26
 */
public interface ConsumeRecordService {
    /**
     * 查看历史消费记录（根据用户id）
     * @param userId
     * @return
     */
    ResponseVO searchConsumeRecordByUserId(Integer userId);
}
