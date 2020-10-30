package com.example.cinema.test.history;

import com.example.cinema.blImpl.history.ConsumeRecordServiceImpl;
import com.example.cinema.vo.ConsumeRecordUserVO;
import com.example.cinema.vo.ConsumeRecordVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConsumeRecordServiceImplTest {
    @Autowired
    ConsumeRecordServiceImpl consumeRecordService;

    @Test
    public void searchConsumeRecordByUserId() {
        List<ConsumeRecordVO> consumeRecordVOS=(List<ConsumeRecordVO>)consumeRecordService.searchConsumeRecordByUserId(16).getContent();
        for (int i = 0; i < consumeRecordVOS.size(); i++) {
            System.out.println(consumeRecordVOS.get(i).getSeat());
        }
    }

    @Test
    public void insertConsumeRecord() {
        ConsumeRecordUserVO consumeRecordUserVO=new ConsumeRecordUserVO();
        consumeRecordUserVO.setScheduleID(68);
        consumeRecordUserVO.setUserID(100);
        consumeRecordUserVO.setSeat("1张 1排1座");
        consumeRecordUserVO.setWay(1);
        consumeRecordUserVO.setConsumeTime(new Timestamp(System.currentTimeMillis()));
        consumeRecordUserVO.setAmount(99.1);
        consumeRecordService.insertConsumeRecord(consumeRecordUserVO);
    }
}
