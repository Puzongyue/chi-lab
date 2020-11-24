package com.example.cinema.vo;

import java.sql.Timestamp;

/**
 * @author lyp
 */
public class ConsumeRecordVO {
    /**
     * 消费金额
     */
    private double amount;

    /**
     * 消费时间
     */
    private Timestamp consumeTime;

    /**
     * 消费方式（银行卡/会员卡）
     */
    private int way;

    /**
     * 电影名称
     */
    private String filmName;

    /**
     * 影厅名
     */
    private String hallName;

    /**
     * 座位，例如：1排1座
     */
    private String seat;

    /**
     * 电影开始时间
     */
    private Timestamp begin;

    /**
     * 电影结束时间
     */
    private Timestamp end;

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

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Timestamp getBegin() {
        return begin;
    }

    public void setBegin(Timestamp begin) {
        this.begin = begin;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }
}
