package com.example.cinema.blImpl.sales;

import com.example.cinema.po.Activity;

import java.util.List;

public interface ActivityServiceForBl {
    /**
     * 得到所有的优惠活动
     * @return
     */
    List<Activity> selectActivities();
}
