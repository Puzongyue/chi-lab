package com.example.cinema.data.history;

import com.example.cinema.po.ChargeRecordPO;
import com.example.cinema.vo.ChargeRecordUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lyp
 * created on 2019.05.28
 */
@Mapper
public interface ChargeRecordMapper {
    /**
     * 得到某个用户的所有充值记录
     * @param userID
     * @return
     */
    List<ChargeRecordPO> getChargeRecords(@Param("userID")int userID);

    /**
     * 增加充值记录
     * @param chargeRecordUserVO
     * @return
     */
    int addChargeRecord(ChargeRecordUserVO chargeRecordUserVO);
}
