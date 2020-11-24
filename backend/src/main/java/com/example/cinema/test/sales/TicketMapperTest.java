package com.example.cinema.test.sales;


import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.Ticket;
import org.junit.Test;

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
public class TicketMapperTest {

    @Autowired
    private TicketMapper ticketMapper;

    @Test
    public void updateTicketConsume() {
        ticketMapper.updateTicketConsume(1,100);
    }

    @Test
    public void updateTicketWay() {
        ticketMapper.updateTicketWay(1,0);
    }

    @Test
    public void selectTicketByUserScheduleAndState() {
        List<Ticket> tickets=ticketMapper.selectTicketByUserScheduleAndState(100,68);
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println(tickets.get(i).getColumnIndex());
            System.out.println(tickets.get(i).getRowIndex());
        }
    }

    @Test
    public void insertRefundedTicket() {
        Ticket ticket=new Ticket();
        ticket.setTime(new Timestamp(System.currentTimeMillis()));
        ticket.setState(1);
        ticket.setRowIndex(1);
        ticket.setColumnIndex(1);
        ticket.setScheduleId(1000);
        ticket.setUserId(100);
        ticket.setId(100);
        ticketMapper.insertRefundedTicket(ticket);
    }

    @Test
    public void selectRefundedTicketByUser() {
        List<Ticket> tickets=ticketMapper.selectRefundedTicketByUser(100);
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println(tickets.get(i).getTime());
        }
    }
}
