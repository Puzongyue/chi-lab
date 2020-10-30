package com.example.cinema.po;

import com.example.cinema.vo.ChargeRecordVO;

import java.sql.Timestamp;

public class ChargeRecordPO {
    private int id;
    private int userID;
    /**
     * 充值金额
     */
    private double amount;

    /**
     * 充值时间
     */
    private Timestamp chargeTime;

    /**
     * 会员卡充值优惠的描述
     */
    private String VIPactivity;

    /**
     * 被充值优惠赠送的金额
     */
    private int givenAmount;

    public ChargeRecordVO getVO(){
        ChargeRecordVO chargeRecordVO=new ChargeRecordVO();
        chargeRecordVO.setAmount(this.amount);
        chargeRecordVO.setChargeTime(this.chargeTime);
        chargeRecordVO.setGivenAmount(this.givenAmount);
        chargeRecordVO.setVIPActivity(this.VIPactivity);
        return chargeRecordVO;
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

    public Timestamp getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(Timestamp chargeTime) {
        this.chargeTime = chargeTime;
    }

    public String getVIPactivity() {
        return VIPactivity;
    }

    public void setVIPactivity(String VIPactivity) {
        this.VIPactivity = VIPactivity;
    }

    public int getGivenAmount() {
        return givenAmount;
    }

    public void setGivenAmount(int givenAmount) {
        this.givenAmount = givenAmount;
    }
}
