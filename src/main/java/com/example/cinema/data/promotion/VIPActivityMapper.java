package com.example.cinema.data.promotion;

import com.example.cinema.po.VIPActivity;
import com.example.cinema.vo.VIPActivityForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface VIPActivityMapper {
    /**
     * 得到所有的充值优惠活动
     * @return
     */
    List<VIPActivity> getAllVIPActivities();

    /**
     * 按照id查找充值优惠活动
     * @param id
     * @return
     */
    VIPActivity getVIPActivityById(int id);

    /**
     * 插入一个充值优惠活动
     * @param vipActivity
     * @return
     */
    int insertActivity(VIPActivity vipActivity);

    /**
     * 更新充值优惠活动
     * @param vipActivity
     */
    void updateActivity(VIPActivity vipActivity);

    /**
     * 按照id删除充值优惠活动
     * @param id
     */
    void deleteActivity(int id);
}
