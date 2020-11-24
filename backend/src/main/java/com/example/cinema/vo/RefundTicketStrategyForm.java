package com.example.cinema.vo;

import com.example.cinema.po.RefundTicketStrategy;

/**
 * @author lyp
 * created on 2019.05.26
 */
public class RefundTicketStrategyForm {
    private int id;
    private float refundPercentage;//退款百分比
    private int availableTime;// 单位为秒
    //private String description;//不知道有没有用

    public RefundTicketStrategyForm(RefundTicketStrategy refundTicketStrategy){
        this.id=refundTicketStrategy.getId();
        this.refundPercentage=refundTicketStrategy.getRefundPercentage();
        this.availableTime=refundTicketStrategy.getAvailableTime();
    }
    public RefundTicketStrategyForm(){

    }

    /*public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }*/

    public int getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(int availableTime) {
        this.availableTime = availableTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setRefundPercentage(float refundPercentage) {
        this.refundPercentage = refundPercentage;
    }

    public float getRefundPercentage() {
        return refundPercentage;
    }
}
