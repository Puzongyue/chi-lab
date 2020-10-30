package com.example.cinema.controller.history;

import com.example.cinema.bl.history.ChargeRecordService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyp
 * created on 2019.05.26
 */
@RestController()
@RequestMapping("/chargeRecord")
public class ChargeRecordController {
    @Autowired
    private ChargeRecordService chargeRecordService;

    /**
     * 得到充值记录
     * @param userId
     * @return
     */
    @GetMapping("{userId}/get")
    public ResponseVO getChargeRecord(@PathVariable int userId){
        return chargeRecordService.searchChargeRecordByUserId(userId);
    }
}
