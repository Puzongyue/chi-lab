package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.VIPActivityService;
import com.example.cinema.data.promotion.VIPActivityMapper;
import com.example.cinema.po.VIPActivity;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPActivityForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class VIPActivityServiceImpl implements VIPActivityService {
    @Autowired
    VIPActivityMapper vipActivityMapper;

    @Override
    public ResponseVO publishVIPActivity(VIPActivityForm vipActivityForm) {
        try {
            VIPActivity activity=new VIPActivity(vipActivityForm);
            vipActivityMapper.insertActivity(activity);
            return ResponseVO.buildSuccess(activity.getId());
        } catch (Exception e) { return ResponseVO.buildFailure("Module Failed");}
    }
    @Override
    public ResponseVO getVIPActivities(){
        try{
            List<VIPActivity> activities=vipActivityMapper.getAllVIPActivities();
            System.out.println(activities.size());
            List<VIPActivityForm> a=new ArrayList<>();
            for(int i=0;i<activities.size();i++){
                a.add(new VIPActivityForm(activities.get(i)));
            }
            VIPActivityForm tempForm=new VIPActivityForm();
            for(int j=0;j<a.size();j++){
                for(int i=0;i<a.size()-j-1;i++){
                    if(a.get(i+1).getCostInNeed()<a.get(i).getCostInNeed()){
                        tempForm=a.get(i+1);
                        a.set(i+1,a.get(i));
                        a.set(i,tempForm);
                    }
                }
            }




            return ResponseVO.buildSuccess(a);
        }catch (Exception e){return ResponseVO.buildFailure("Module Failed");}
    }
    @Override
    public ResponseVO updateVIPActivity(VIPActivityForm vipActivityForm){
        try {
            VIPActivity activity=vipActivityMapper.getVIPActivityById(vipActivityForm.getId());
            if(activity==null) throw new Exception();
            vipActivityMapper.updateActivity(new VIPActivity(vipActivityForm));
            return ResponseVO.buildSuccess();
        } catch (Exception e) { return ResponseVO.buildFailure("Module Failed");}

    }

}
