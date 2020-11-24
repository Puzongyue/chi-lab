package com.example.cinema.controller.history;


import com.example.cinema.bl.history.ConsumeRecordService;
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
@RequestMapping("/consumeRecord")
public class ConsumeRecordController {
    @Autowired
    private ConsumeRecordService consumeRecordService;

    /**
     * 得到消费记录
     * @param userId
     * @return
     */
    @GetMapping("{userId}/get")
    public ResponseVO getConsumeRecord(@PathVariable int userId){
        return consumeRecordService.searchConsumeRecordByUserId(userId);
    }
}
