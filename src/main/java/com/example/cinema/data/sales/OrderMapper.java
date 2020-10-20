package com.example.cinema.data.sales;

import com.example.cinema.po.Order;
import com.example.cinema.po.Ticket;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    public int insertOrder(@Param("order")Order order);

    public int selectOrderById(@Param("id")int id);

    public List<Ticket> selectOrderTickets(@Param("id")int id);



}
