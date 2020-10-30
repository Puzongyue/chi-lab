package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.blImpl.sales.VipServiceForBl;
import com.example.cinema.data.promotion.VIPActivityMapper;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.data.sales.RefundTicketMapper;
import com.example.cinema.po.VIPActivity;
import com.example.cinema.vo.*;
import com.example.cinema.po.VIPCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


/**
 * Created by liying on 2019/4/14.
 */
@Service
public class VIPServiceImpl implements VIPService, VipServiceForBl,VIPServiceForPromotionBl {
    @Autowired
    VIPCardMapper vipCardMapper;
    @Autowired
    ChargeRecordServiceForBl chargeRecordServiceForBl;
    @Autowired
    VIPActivityMapper vipActivityMapper;

    @Override
    public ResponseVO addVIPCard(int userId) {
        VIPCard vipCard = new VIPCard();
        vipCard.setUserId(userId);
        vipCard.setBalance(0);
        try {
            int id = vipCardMapper.insertOneCard(vipCard);
            return ResponseVO.buildSuccess(vipCardMapper.selectCardById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCardById(int id) {
        try {
            return ResponseVO.buildSuccess(vipCardMapper.selectCardById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getVIPInfo() {
        VIPInfoVO vipInfoVO = new VIPInfoVO();
        vipInfoVO.setDescription(VIPCard.description);
        vipInfoVO.setPrice(VIPCard.price);
        return ResponseVO.buildSuccess(vipInfoVO);
    }
    private void insertChargeRecord(VIPCardForm vipCardForm,String VIPActivity,int givenAmount){
        ChargeRecordUserVO chargeRecordUserVO=new ChargeRecordUserVO();
        VIPCard vipCard=vipCardMapper.selectCardById(vipCardForm.getVipId());
        chargeRecordUserVO.setUserID(vipCard.getUserId());
        chargeRecordUserVO.setAmount(vipCardForm.getAmount());
        chargeRecordUserVO.setChargeTime(new Timestamp(System.currentTimeMillis()));
        chargeRecordUserVO.setVIPActivity(VIPActivity);
        chargeRecordUserVO.setGivenAmount(givenAmount);
        chargeRecordServiceForBl.insertChargeRecord(chargeRecordUserVO);
    }
    @Override
    public ResponseVO charge(VIPCardForm vipCardForm) {

        VIPCard vipCard = vipCardMapper.selectCardById(vipCardForm.getVipId());
        if (vipCard == null) {
            return ResponseVO.buildFailure("会员卡不存在");
        }
        List<VIPActivity> vipActivities=vipActivityMapper.getAllVIPActivities();
        int pos=0;
        double maxBonus=0;
        for(int i=0;i<vipActivities.size();i++){
            if(vipCardForm.getAmount()>=vipActivities.get(i).getCost_in_need() && vipActivities.get(i).getBonus_balance()>maxBonus){
                maxBonus=vipActivities.get(i).getBonus_balance();
                pos=i;
            }
        }
        double balance = vipCardForm.getAmount()+(int)maxBonus+vipCard.getBalance();

        insertChargeRecord(vipCardForm,vipActivities.get(pos).getDescription(),(int)maxBonus);//插入充值记录

        vipCard.setBalance(balance);
        try {
            vipCardMapper.updateCardBalance(vipCardForm.getVipId(), vipCard.getBalance());
            return ResponseVO.buildSuccess(vipCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCardByUserId(int userId) {
        try {
            VIPCard vipCard = vipCardMapper.selectCardByUserId(userId);
            if(vipCard==null){
                return ResponseVO.buildFailure("用户卡不存在");
            }
            return ResponseVO.buildSuccess(vipCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
    @Override
    public boolean payByVipCard(int id,double fare){
        VIPCard vipCard = vipCardMapper.selectCardById(id);
        if (vipCard.getBalance()>=fare){
            vipCardMapper.updateCardBalance(id,vipCard.getBalance()-fare);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void updateVipConsume(int vipcardID,double consume){
        vipCardMapper.updateVipConsume(vipcardID,consume);
    }

    @Override
    public List<VIPCard> selectVipByConsume(double consume){
        return vipCardMapper.selectVipByConsume(consume);
    }

    @Override
    public void updateVipBalance(int vipcardID,double refund){
        VIPCard vipCard=vipCardMapper.selectCardById(vipcardID);
        vipCardMapper.updateCardBalance(vipcardID,vipCard.getBalance()+refund);
    }

}
