package com.example.cinema.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Order {

    private Integer id;

    private Integer moveId;

    private Double price;

    private Integer ticketNum;

    private Date orderTime;

}
