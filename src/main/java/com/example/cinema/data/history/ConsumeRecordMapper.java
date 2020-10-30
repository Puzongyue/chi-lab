package com.example.cinema.data.history;



import com.example.cinema.po.ConsumeRecordPO;
import com.example.cinema.vo.ConsumeRecordUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lyp
 * created on 2019.05.28
 */
@Mapper
public interface ConsumeRecordMapper {
    /**
     * 得到某个用户所有的消费记录
     * @param userID
     * @return
     */
    List<ConsumeRecordPO> getConsumeRecords(@Param("userID")int userID);

    /**
     * 插入消费记录
     * @param consumeRecordUserVO
     * @return
     */
    int addConsumeRecord(ConsumeRecordUserVO consumeRecordUserVO);
}
