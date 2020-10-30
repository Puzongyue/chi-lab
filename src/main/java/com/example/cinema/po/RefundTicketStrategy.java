package com.example.cinema.po;

import com.example.cinema.vo.RefundTicketStrategyForm;

public class RefundTicketStrategy {
    private int id;
    private float refund_percentage;//退款百分比
    private int available_time;// 单位为秒
    //private String description;//不知道有没有用

    /*public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }*/

    public int getAvailableTime() {
        return available_time;
    }

    public void setAvailableTime(int availableTime) {
        this.available_time = availableTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setRefundPercentage(float refundPercentage) {
        this.refund_percentage = refundPercentage;
    }

    public float getRefundPercentage() {
        return refund_percentage;
    }

    public RefundTicketStrategy(){
        ;
    }
    public RefundTicketStrategy(RefundTicketStrategyForm r){
        this.id=r.getId();
        this.refund_percentage=r.getRefundPercentage();
        this.available_time=r.getAvailableTime();
    }
}
