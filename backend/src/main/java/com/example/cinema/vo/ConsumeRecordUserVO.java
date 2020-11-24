package com.example.cinema.vo;

import java.sql.Timestamp;

public class ConsumeRecordUserVO {
    private int userID;
    /**
     * 消费金额
     */
    private double amount;

    /**
     * 消费时间
     */
    private Timestamp consumeTime;

    /**
     * 消费方式：1表示会员卡，0表示银行卡
     */
    private int way;

    /**
     * 座位，例如：1排1座
     */
    private String seat;

    /**
     * 排片id
     */
    private int scheduleID;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Timestamp consumeTime) {
        this.consumeTime = consumeTime;
    }

    public int getWay() {
        return way;
    }

    public void setWay(int way) {
        this.way = way;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }


}
