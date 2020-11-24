package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @GetMapping("{userId}/get")
    public ResponseVO getCoupons(@PathVariable int userId){
        return couponService.getCouponsByUser(userId);
    }

    @PostMapping("/giveVIP/give")
    public ResponseVO giveCoupons(@RequestParam double consume, @RequestParam List<Integer> couponIds){
        return couponService.giveVIPCoupons(consume,couponIds);
    }
    @GetMapping("/giveVIP/get")
    public ResponseVO searchCoupons(){
        return couponService.searchCoupons();
    }



}
