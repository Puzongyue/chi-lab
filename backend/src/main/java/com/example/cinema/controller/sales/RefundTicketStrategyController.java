package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.RefundTicketStrategyService;
import com.example.cinema.vo.RefundTicketStrategyForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyp
 * created on 2019.05.26
 */
@RestController()
public class RefundTicketStrategyController {
    @Autowired
    private RefundTicketStrategyService refundTicketStrategyService;

    @RequestMapping(value = "/refund/add",method = RequestMethod.POST)
    public ResponseVO publishRefundTicketStragety(@RequestBody RefundTicketStrategyForm refundTicketStrategyForm){
        return refundTicketStrategyService.publishRefundTicketStrategy(refundTicketStrategyForm);
    }
    @RequestMapping(value = "/refund/update",method = RequestMethod.POST)
    public ResponseVO updateRefundTicketStragety(@RequestBody RefundTicketStrategyForm refundTicketStrategyForm){
        return refundTicketStrategyService.updateRefundTicketStrategy(refundTicketStrategyForm);
    }
    @RequestMapping(value="/refund/get",method=RequestMethod.GET)
    public ResponseVO getAllRefundStrategies(){
        return refundTicketStrategyService.getAllRefundTicketStrategies();
    }
}

