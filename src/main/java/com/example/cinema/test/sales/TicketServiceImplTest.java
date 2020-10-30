package com.example.cinema.test.sales;


import com.example.cinema.blImpl.sales.TicketServiceImpl;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.Ticket;
import com.example.cinema.vo.RefundTicketStrategyForm;
import com.example.cinema.vo.TicketVO;
import com.example.cinema.vo.TicketWithCouponVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketServiceImplTest {
    @Autowired
    private TicketServiceImpl ticketService;

    @Autowired
    private TicketMapper ticketMapper;

    @Test
    public void getRefundStrategies() {
        List<RefundTicketStrategyForm> refundTicketStrategyForms=(List<RefundTicketStrategyForm>)ticketService.getRefundStrategies().getContent();
        for (int i = 0; i < refundTicketStrategyForms.size(); i++) {
            System.out.println(refundTicketStrategyForms.get(i).getId());
            System.out.println(refundTicketStrategyForms.get(i).getRefundPercentage());
        }
    }

    @Test
    public void refundTickets() {
        List<Integer> ids=new ArrayList<>();
        ids.add(10);
        ticketService.refundTickets(ids);
    }

    @Test
    public void getRefundedTickets() {
        List<TicketVO> ticketVOS=(List<TicketVO>)ticketService.getRefundedTickets(100).getContent();
        for (int i = 0; i < ticketVOS.size(); i++) {
            System.out.println(ticketVOS.get(i).getId());
            System.out.println(ticketVOS.get(i).getTime());
        }
    }

    @Test
    public void getInfoOfUnpaidTickets() {
        Ticket ticket=new Ticket();
        ticket.setState(0);
        ticket.setTime(new Timestamp(System.currentTimeMillis()));
        ticket.setRowIndex(1);
        ticket.setColumnIndex(1);
        ticket.setScheduleId(68);
        ticket.setUserId(100);
        ticket.setId(100);
        ticketMapper.insertTicket(ticket);
        TicketWithCouponVO ticketWithCouponVO=(TicketWithCouponVO)ticketService.getInfoOfUnpaidTickets(100,68).getContent();
        System.out.println(ticketWithCouponVO.getTotal());
    }
}
