package com.example.cinema.test.history;


import com.example.cinema.blImpl.history.ChargeRecordServiceImpl;
import com.example.cinema.vo.ChargeRecordUserVO;
import com.example.cinema.vo.ChargeRecordVO;
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
public class ChargeRecordServiceImplTest {

    @Autowired
    private ChargeRecordServiceImpl chargeRecordService;
    @Test
    public void searchChargeRecordByUserId() {
        List<ChargeRecordVO> chargeRecordVOS=(List<ChargeRecordVO>)chargeRecordService.searchChargeRecordByUserId(16).getContent();
        for (int i = 0; i < chargeRecordVOS.size(); i++) {
            System.out.println(chargeRecordVOS.get(i).getChargeTime());
        }
    }

    @Test
    public void insertChargeRecord() {
        ChargeRecordUserVO chargeRecordVO=new ChargeRecordUserVO();
        chargeRecordVO.setChargeTime(new Timestamp(System.currentTimeMillis()));
        chargeRecordVO.setVIPActivity("满200送20");
        chargeRecordVO.setGivenAmount(100);
        chargeRecordVO.setAmount(188.8);
        chargeRecordVO.setUserID(100);
        chargeRecordService.insertChargeRecord(chargeRecordVO);
    }
}
