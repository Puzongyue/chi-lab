package com.example.cinema.blImpl.promotion.coupon;

import com.example.cinema.po.Coupon;

import java.util.List;

public interface CouponServiceForBl {

    Coupon getCouponById(int id);

    void removeCoupon(int couponId, int userId);

    void addCoupon(int couponId, int userId);

    List<Coupon> getCouponByUserId(int userId);

}
