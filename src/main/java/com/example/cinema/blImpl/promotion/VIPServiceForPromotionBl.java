package com.example.cinema.blImpl.promotion;

import com.example.cinema.po.VIPCard;

import java.util.List;

public interface VIPServiceForPromotionBl {
    /**
     * 查找得到达到消费额度的会员卡
     * @param consume
     * @return
     */
    List<VIPCard> selectVipByConsume(double consume);
}
