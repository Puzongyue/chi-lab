package com.example.cinema.test.management;

import com.example.cinema.bl.management.HallService;
import com.example.cinema.data.management.HallMapper;
import com.example.cinema.po.Hall;
import com.example.cinema.vo.HallForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HallTest {
    @Autowired
    HallService hallService;
    @Autowired
    HallMapper hallMapper;
    @Test
    public void testInsert(){
        printAll();
        HallForm hallForm=new HallForm();
        hallForm.setName("嘤嘤嘤");
        hallForm.setColumn(6324);
        hallForm.setRow(6324);
        hallService.addHall(hallForm);
        printAll();
    }
    @Test
    public void testUpdate(){
        printAll();
        HallForm hallForm=new HallForm();
        hallForm.setName("嘤嘤嘤");
        hallForm.setColumn(4396);
        hallForm.setRow(4396);
        hallForm.setId(2);
        hallService.updateHall(hallForm);
        printAll();
    }


    private void print(Hall hall){
        System.out.println("-----------------");
        System.out.println("id="+hall.getId());
        System.out.println("name="+hall.getName());
        System.out.println("row="+hall.getRow());
        System.out.println("column="+hall.getColumn());
        System.out.println("-----------------");
    }
    private void printAll(){
        System.out.println("?!?!?!?!?!?!?!?!?!?");
        List<Hall> halls=hallMapper.selectAllHall();
        for(int i=0;i<halls.size();i++){
            print(halls.get(i));
        }
        System.out.println("?!?!?!?!?!?!?!?!?!?");
    }
}
