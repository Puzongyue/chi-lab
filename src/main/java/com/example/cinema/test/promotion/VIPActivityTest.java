package com.example.cinema.test.promotion;

import com.example.cinema.bl.promotion.VIPActivityService;
import com.example.cinema.data.promotion.VIPActivityMapper;
import com.example.cinema.po.VIPActivity;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPActivityForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@SpringBootTest
@RunWith(SpringRunner.class)
public class VIPActivityTest {
    @Autowired
    VIPActivityService vipActivityService;
    @Autowired
    VIPActivityMapper vipActivityMapper;

    @Test
    public void testPublish(){
        printAll();
        VIPActivity vipActivity=new VIPActivity();
        vipActivity.setCost_in_need(1000);
        vipActivity.setBonus_balance(200);
        vipActivity.setName("4396");
        vipActivity.setDescription("嘤嘤嘤");
        vipActivityService.publishVIPActivity(new VIPActivityForm(vipActivity));
        printAll();
    }
    public void testMapperPublish(){
        VIPActivity vipActivity=new VIPActivity();
        vipActivity.setCost_in_need(1000);
        vipActivity.setBonus_balance(200);
        vipActivity.setName("4396");
        vipActivity.setDescription("嘤嘤嘤");
        vipActivityMapper.insertActivity(vipActivity);
        printAll();
    }
    @Test
    public void testUpdate(){
        VIPActivity vipActivity=new VIPActivity();
        vipActivity.setCost_in_need(2000);
        vipActivity.setBonus_balance(500);
        vipActivity.setName("2800");
        vipActivity.setDescription("嘤嘤嘤嘤嘤嘤");
        vipActivity.setId(2);
        vipActivityService.updateVIPActivity(new VIPActivityForm(vipActivity));
        printAll();
    }
    public void testMapperUpdate(){
        VIPActivity vipActivity=new VIPActivity();
        vipActivity.setCost_in_need(2000);
        vipActivity.setBonus_balance(500);
        vipActivity.setName("2800");
        vipActivity.setDescription("嘤嘤嘤嘤嘤嘤");
        vipActivity.setId(2);
        vipActivityMapper.updateActivity(vipActivity);
        printAll();
    }
    private void print(VIPActivity vipActivity){
        System.out.println("---------------");
        System.out.println("id="+vipActivity.getId());
        System.out.println("name="+vipActivity.getName());
        System.out.println("description="+vipActivity.getDescription());
        System.out.println("cost="+vipActivity.getCost_in_need());
        System.out.println("bonus="+vipActivity.getBonus_balance());
        System.out.println("---------------");

    }
    private void printAll(){

        System.out.println("*#*#*#*#*#*#*#*#*#*#*#*");
        List<VIPActivity> activities=vipActivityMapper.getAllVIPActivities();
        for(int i=0;i<activities.size();i++){
            print(activities.get(i));
        }
        System.out.println("*#*#*#*#*#*#*#*#*#*#*#*\n");
    }


    @Test
    public void testGet(){
        ResponseVO vo=vipActivityService.getVIPActivities();
        List<VIPActivityForm> vipActivityForms=(List<VIPActivityForm>)vo.getContent();
        System.out.println(vipActivityForms.size());
    }
}
