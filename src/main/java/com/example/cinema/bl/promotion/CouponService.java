package com.example.cinema.bl.promotion;

import com.example.cinema.vo.CouponForm;
import com.example.cinema.vo.ResponseVO;

import java.util.List;

/**
 * Created by liying on 2019/4/17.
 */
public interface CouponService {

    ResponseVO getCouponsByUser(int userId);

    ResponseVO addCoupon(CouponForm couponForm);

    ResponseVO issueCoupon(int couponId,int userId);

    /**
     * 根据消费金额给会员优惠券
     * @param consume 消费金额
     * @param couponIds 优惠券id
     * @return
     */
    ResponseVO giveVIPCoupons(double consume, List<Integer> couponIds);

    /**
     * 查询已发布的所有优惠券
     * @return 返回couponVO的list
     */
    ResponseVO searchCoupons();

}
