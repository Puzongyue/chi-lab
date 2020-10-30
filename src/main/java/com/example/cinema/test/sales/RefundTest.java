package com.example.cinema.test.sales;

import com.example.cinema.bl.sales.RefundTicketStrategyService;
import com.example.cinema.data.sales.RefundTicketMapper;
import com.example.cinema.po.RefundTicketStrategy;
import com.example.cinema.vo.RefundTicketStrategyForm;
import com.example.cinema.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RefundTest {
    @Autowired
    RefundTicketStrategyService refundTicketStrategyService;
    @Autowired
    RefundTicketMapper refundTicketMapper;
    @Test
    public void testRefund(){
        testGetAll();
        int id=testInsert();
        testGetById(id);
        testUpdate();
        RefundTicketStrategyForm f=new RefundTicketStrategyForm();
        f.setRefundPercentage(0.2f);
        f.setAvailableTime(60000);
        id=(Integer)refundTicketStrategyService.publishRefundTicketStrategy(f).getContent();
        testGetAll();
        System.out.println(id);
        f.setId(id);
        f.setAvailableTime(80000);
        f.setRefundPercentage(0.3f);
        refundTicketStrategyService.updateRefundTicketStrategy(f);
        testGetAll();
    }
    private int testInsert(){
        RefundTicketStrategyForm form=new RefundTicketStrategyForm();
        form.setAvailableTime(360000);
        form.setRefundPercentage(0.75f);
        int id=refundTicketMapper.insertRefundStrategy(new RefundTicketStrategy(form));
        System.out.println(id);
        return id;
    }
    private void testGetById(int id){
        RefundTicketStrategy strategy=refundTicketMapper.getStrategyById(id);
        System.out.println("Strategy whose id="+id+":");
        print(strategy);
        System.out.println("TestGetByIdDone\n");
    }
    private void testUpdate(){
        RefundTicketStrategy strategy=new RefundTicketStrategy();
        strategy.setId(1);
        strategy.setAvailableTime(100000);
        strategy.setRefundPercentage(0.1f);
        refundTicketMapper.updateRefundStrategy(strategy);
        print(refundTicketMapper.getStrategyById(1));
    }
    private void testGetAll(){
        List<RefundTicketStrategy> strategies=refundTicketMapper.getAllStragegies();
        System.out.println("AllStrategiesAre:");
        for(int i=0;i<strategies.size();i++){
            print(strategies.get(i));
        }
        System.out.println("StrategiesDone.\n");
    }
    private void print(RefundTicketStrategy r){
        System.out.println("-------------------");
        System.out.println("id="+r.getId());
        System.out.println("availableTime="+r.getAvailableTime());
        System.out.println("refundPercentage="+r.getRefundPercentage());
        System.out.println("-------------------");
    }
    private void print(RefundTicketStrategyForm r){
        print(new RefundTicketStrategy(r));
    }
}