package com.example.cinema.test.promotion;

import com.example.cinema.blImpl.promotion.CouponServiceImpl;
import com.example.cinema.po.Coupon;
import com.example.cinema.vo.CouponVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CouponServiceImplTest {
    @Autowired
    CouponServiceImpl couponService;




    @Test
    public void giveVIPCoupons() {
        List<Integer> ids=new ArrayList<>();
        ids.add(8);
        couponService.giveVIPCoupons(8,ids);
    }

    @Test
    public void searchCoupons() {
        List<CouponVO> couponVOS=(List<CouponVO>)couponService.searchCoupons().getContent();
        for (int i = 0; i < couponVOS.size(); i++) {
            System.out.println(couponVOS.get(i).getCouponId());
            System.out.println(couponVOS.get(i).getDescription());

        }
    }
}
