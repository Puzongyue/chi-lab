package com.example.cinema.vo;

import java.sql.Timestamp;

public class ChargeRecordUserVO {
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
    private String VIPActivity;
    /**
     *被充值优惠赠送的金额
     */
    private int givenAmount;

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

    public String getVIPActivity() {
        return VIPActivity;
    }

    public void setVIPActivity(String VIPActivity) {
        this.VIPActivity = VIPActivity;
    }

    public int getGivenAmount() {
        return givenAmount;
    }

    public void setGivenAmount(int givenAmount) {
        this.givenAmount = givenAmount;
    }
}
