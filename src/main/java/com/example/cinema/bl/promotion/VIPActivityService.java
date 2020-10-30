package com.example.cinema.bl.promotion;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPActivityForm;

/**
 * @author lyp
 * created on 2019.05.26
 */
public interface VIPActivityService {
    /**
     * 发布充值会员卡的优惠策略
     * @param vipActivityForm
     * @return
     */
    ResponseVO publishVIPActivity(VIPActivityForm vipActivityForm);

    /**
     * 得到当前充值会员卡的优惠策略
     * @return
     */
    ResponseVO getVIPActivities();

    /**
     * 修改充值会员卡的优惠策略
     * @param vipActivityForm
     * @return
     */
    ResponseVO updateVIPActivity(VIPActivityForm vipActivityForm);
}
