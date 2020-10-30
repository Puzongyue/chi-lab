package com.example.cinema.blImpl.sales;

public interface VipServiceForBl {
    /**
     * 返回vip卡的余额是否够,如果够则更新vip的余额
     * @param id
     * @param fare
     * @return
     */
    boolean payByVipCard(int id,double fare);

    /**
     * 更新会员的累计消费额度
     * @param vipcardID
     * @param consume
     */
    void updateVipConsume(int vipcardID,double consume);

    /**
     * 通过退款更新会员的余额
     * @param refund 退款
     */
    void updateVipBalance(int vipcardID,double refund);
}
