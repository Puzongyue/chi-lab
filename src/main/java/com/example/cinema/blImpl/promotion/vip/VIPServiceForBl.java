package com.example.cinema.blImpl.promotion.vip;

import com.example.cinema.po.VIPCard;

public interface VIPServiceForBl {

    VIPCard getVIPCardByUserId(int userId);

    void payByCard(int id, double balance);

}
