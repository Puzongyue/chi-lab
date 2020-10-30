package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.sales.RefundTicketStrategyService;
import com.example.cinema.data.sales.RefundTicketMapper;
import com.example.cinema.po.RefundTicketStrategy;
import com.example.cinema.vo.RefundTicketStrategyForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Service
public class RefundTicketStrategyServiceImpl implements RefundStrategyForBl, RefundTicketStrategyService{
    @Autowired
    private RefundTicketMapper refundTicketMapper;

    @Override
    public ResponseVO publishRefundTicketStrategy(RefundTicketStrategyForm refundTicketStrategyForm){
        try{
            RefundTicketStrategy r=new RefundTicketStrategy(refundTicketStrategyForm);
            refundTicketMapper.insertRefundStrategy(r);
            int x=r.getId();

            return ResponseVO.buildSuccess(x);
        }catch (Exception e){
            return ResponseVO.buildFailure("Module Failed");
        }

    }
    @Override
    public ResponseVO getAllRefundTicketStrategies(){
        return ResponseVO.buildSuccess(getRefundStrategy());
    }

    @Override
    public ResponseVO updateRefundTicketStrategy(RefundTicketStrategyForm refundTicketStrategyForm){
        try{
            if(refundTicketMapper.getStrategyById(refundTicketStrategyForm.getId())==null){
                throw new Exception();
            }
            refundTicketMapper.updateRefundStrategy(new RefundTicketStrategy(refundTicketStrategyForm));
            return ResponseVO.buildSuccess();

        }catch (Exception e){
            return ResponseVO.buildFailure("Module Failed");
        }
    }

    @Override
    public List<RefundTicketStrategyForm> getRefundStrategy(){
        try {
            List<RefundTicketStrategy> strategies=refundTicketMapper.getAllStragegies();
            List<RefundTicketStrategyForm> strategyForms=new ArrayList<>();
            for(int i=0;i<strategies.size();i++){
                strategyForms.add(new RefundTicketStrategyForm(strategies.get(i)));
            }

            RefundTicketStrategyForm temp;
            for(int i=0;i<strategyForms.size();i++){
                for(int j=0;j<strategyForms.size()-i-1;j++){
                    if(strategyForms.get(j).getAvailableTime()>strategyForms.get(j+1).getAvailableTime()){
                        temp=strategyForms.get(j+1);
                        strategyForms.set(j+1,strategyForms.get(j));
                        strategyForms.set(j,temp);
                    }
                }
            }
            return strategyForms;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }



    }

    @Override
    public double getBestRefundStrategy(Date date){
        try {
            Date currentTime = new Date();
            long diff = (date.getTime() - currentTime.getTime())/1000;
            List<RefundTicketStrategy> strategies = refundTicketMapper.getAllStragegies();
            float refundPercentage=strategies.get(0).getRefundPercentage();
            for(int i=1;i<strategies.size();i++){
                if(diff>strategies.get(i).getAvailableTime() && refundPercentage<strategies.get(i).getRefundPercentage()){
                    refundPercentage=strategies.get(i).getRefundPercentage();
                }
            }
            return refundPercentage;
        }   catch (Exception e){return 0;}
    }


}
