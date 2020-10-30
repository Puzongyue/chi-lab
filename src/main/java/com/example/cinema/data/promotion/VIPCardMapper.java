package com.example.cinema.data.promotion;

import com.example.cinema.po.VIPCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liying on 2019/4/14.
 */
@Mapper
public interface VIPCardMapper {

    int insertOneCard(VIPCard vipCard);

    VIPCard selectCardById(int id);

    void updateCardBalance(@Param("id") int id,@Param("balance") double balance);

    VIPCard selectCardByUserId(int userId);

    /**
     * 更新会员卡的消费额度
     * @param id
     * @param consume
     */
    void updateVipConsume(@Param("id")int id,@Param("consume") double consume);

    /**
     * 选择达到消费额度的会员卡
     * @param consume
     * @return
     */
    List<VIPCard> selectVipByConsume(double consume);



}
