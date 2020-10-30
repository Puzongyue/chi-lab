package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.management.ScheduleService;
import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.blImpl.sales.CouponServiceForBl;
import com.example.cinema.data.promotion.CouponMapper;
import com.example.cinema.po.Coupon;
import com.example.cinema.po.ScheduleItem;
import com.example.cinema.po.VIPCard;
import com.example.cinema.vo.CouponForm;
import com.example.cinema.vo.CouponVO;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.TicketForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liying on 2019/4/17.
 */
@Service
public class CouponServiceImpl implements CouponService, CouponServiceForBl {

    @Autowired
    CouponMapper couponMapper;
    @Autowired
    ScheduleServiceForBl scheduleServiceForBl;
    @Autowired
    VIPServiceForPromotionBl vipServiceForPromotionBl;

    @Override
    public ResponseVO getCouponsByUser(int userId) {
        try {
            return ResponseVO.buildSuccess(couponMapper.selectCouponByUser(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO addCoupon(CouponForm couponForm) {
        try {
            Coupon coupon=new Coupon();
            coupon.setName(couponForm.getName());
            coupon.setDescription(couponForm.getDescription());
            coupon.setTargetAmount(couponForm.getTargetAmount());
            coupon.setDiscountAmount(couponForm.getDiscountAmount());
            coupon.setStartTime(couponForm.getStartTime());
            coupon.setEndTime(couponForm.getEndTime());
            couponMapper.insertCoupon(coupon);
            return ResponseVO.buildSuccess(coupon);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO issueCoupon(int couponId, int userId) {
        try {
            couponMapper.insertCouponUser(couponId,userId);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    public Coupon getCouponById(Integer couponId){
        try {
            return couponMapper.selectById(couponId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteCoupon(int couponId,int userId){
        couponMapper.deleteCouponUser(couponId,userId);
    }

    @Override
    public List<Coupon> selectCouponByUserAndAmount(double total,int userId){
        return couponMapper.selectCouponByUserAndAmount(userId,total);
    }

    @Override
    public ResponseVO giveVIPCoupons(double consume, List<Integer> couponIds){
        try {
            List<VIPCard> vipCards=vipServiceForPromotionBl.selectVipByConsume(consume);
            if (vipCards==null){
                return ResponseVO.buildFailure("没有会员符合要求");
            }
            else if (vipCards.size()==0){
                return ResponseVO.buildFailure("没有会员符合要求");
            }
            else {
                for (int i = 0; i < vipCards.size(); i++) {
                    for (int j = 0; j < couponIds.size(); j++) {
                        couponMapper.insertCouponUser(couponIds.get(j),vipCards.get(i).getUserId());
                    }
                }
                return ResponseVO.buildSuccess();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO searchCoupons(){
        try {
            Timestamp now=new Timestamp(System.currentTimeMillis());
            List<Coupon> coupons=couponMapper.selectValidCoupon(now);
            if(coupons==null){
                return ResponseVO.buildFailure("无有效的优惠券");
            }
            else if (coupons.size()==0){
                return ResponseVO.buildFailure("无有效的优惠券");
            }
            else {
                List<CouponVO> couponVOS=new ArrayList<>();
                for (int i = 0; i < coupons.size(); i++) {
                    couponVOS.add(new CouponVO(coupons.get(i)));
                }
                return ResponseVO.buildSuccess(couponVOS);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }
}
