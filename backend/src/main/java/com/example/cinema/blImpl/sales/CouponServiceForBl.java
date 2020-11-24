package com.example.cinema.blImpl.sales;

import com.example.cinema.po.Coupon;
import com.example.cinema.vo.TicketForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lyp
 * @date 2019/5/13 17:16 PM
 */
public interface CouponServiceForBl {
    /**
     *按照优惠券id查找优惠券
     * @param couponId
     * @return
     */
     Coupon getCouponById(Integer couponId);

    /**
     * 删除用户的某张优惠券
     * @param couponId
     * @param userId
     */
     void deleteCoupon(int couponId,int userId);

    /**
     * 得到该用户在票的总价为total的情况下可以使用的优惠券
     * @param total 票的总价
     * @param userId
     * @return
     */
     List<Coupon> selectCouponByUserAndAmount(double total,int userId);
}
