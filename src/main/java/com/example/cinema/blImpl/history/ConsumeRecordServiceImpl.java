package com.example.cinema.blImpl.history;

import com.example.cinema.bl.history.ConsumeRecordService;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.blImpl.sales.ConsumeRecordServiceForBl;
import com.example.cinema.data.history.ConsumeRecordMapper;
import com.example.cinema.po.ConsumeRecordPO;
import com.example.cinema.po.ScheduleItem;
import com.example.cinema.vo.ConsumeRecordUserVO;
import com.example.cinema.vo.ConsumeRecordVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumeRecordServiceImpl implements ConsumeRecordService , ConsumeRecordServiceForBl {
    @Autowired
    private ConsumeRecordMapper consumeRecordMapper;
    @Autowired
    private ScheduleServiceForBl scheduleServiceForBl;
    @Override
    public ResponseVO searchConsumeRecordByUserId(Integer userId){
        try {
            List<ConsumeRecordPO> consumeRecordPOS=consumeRecordMapper.getConsumeRecords(userId);
            if (consumeRecordPOS==null){
                return ResponseVO.buildFailure("没有消费记录");
            }
            if (consumeRecordPOS.size()==0){
                return ResponseVO.buildFailure("没有消费记录");
            }
            else {
                List<ConsumeRecordVO> consumeRecordVOS=new ArrayList<>();
                for (int i = 0; i < consumeRecordPOS.size(); i++) {
                    ScheduleItem scheduleItem=scheduleServiceForBl.getScheduleItemById(consumeRecordPOS.get(i).getScheduleID());
                    ConsumeRecordVO consumeRecordVO=consumeRecordPOS.get(i).getVO();
                    consumeRecordVO.setBegin(new Timestamp(scheduleItem.getStartTime().getTime()));
                    consumeRecordVO.setEnd(new Timestamp(scheduleItem.getEndTime().getTime()));
                    consumeRecordVO.setFilmName(scheduleItem.getMovieName());
                    consumeRecordVO.setHallName(scheduleItem.getHallName());
                    consumeRecordVOS.add(consumeRecordVO);
                }
                return ResponseVO.buildSuccess(consumeRecordVOS);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
    @Override
    public void  insertConsumeRecord(ConsumeRecordUserVO consumeRecordUserVO){
        consumeRecordMapper.addConsumeRecord(consumeRecordUserVO);
    }

}
