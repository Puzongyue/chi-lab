package com.example.cinema.data.promotion;

import com.example.cinema.po.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liying on 2019/4/17.
 */
@Mapper
public interface CouponMapper {

    int insertCoupon(Coupon coupon);

    List<Coupon> selectCouponByUser(int userId);

    Coupon selectById(int id);

    void insertCouponUser(@Param("couponId") int couponId,@Param("userId")int userId);

    void deleteCouponUser(@Param("couponId") int couponId,@Param("userId")int userId);

    /**
     * 得到观众在一次购票中可用的优惠券
     * @param userId
     * @param amount 票的总价
     * @return
     */
    List<Coupon> selectCouponByUserAndAmount(@Param("userId") int userId,@Param("amount") double amount);

    List<Coupon> selectValidCoupon(@Param("now")Timestamp now);
}
