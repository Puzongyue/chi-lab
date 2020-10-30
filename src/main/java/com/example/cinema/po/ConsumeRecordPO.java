package com.example.cinema.po;

import com.example.cinema.vo.ConsumeRecordVO;

import java.sql.Timestamp;

/**
 * @author lyp
 * created on 2019.05.28
 */
public class ConsumeRecordPO {
    private int id;
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
     *
     */
    private int scheduleID;

    /**
     * 只给部分vo的属性赋值，排片相关的4个属性在具体的方法中赋值
     * @return
     */
    public ConsumeRecordVO getVO(){
        ConsumeRecordVO consumeRecordVO=new ConsumeRecordVO();
        consumeRecordVO.setAmount(this.amount);
        consumeRecordVO.setSeat(this.seat);
        consumeRecordVO.setConsumeTime(this.consumeTime);
        consumeRecordVO.setWay(this.way);
        return consumeRecordVO;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }


}
