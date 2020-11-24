package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.VIPActivityService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPActivityForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyp
 * created on 2019.05.26
 */
@RestController()
public class VIPActivityController {
    @Autowired
    private VIPActivityService vipActivityService;
    @RequestMapping(value = "/vipActivity/publish",method = RequestMethod.POST)
    public ResponseVO publishVIPActivity(@RequestBody VIPActivityForm vipActivityForm){
        return vipActivityService.publishVIPActivity(vipActivityForm);
    }
    @RequestMapping(value = "/vipActivity/get",method = RequestMethod.GET)
    public ResponseVO getVIPActivity(){
        return vipActivityService.getVIPActivities();
    }

    @RequestMapping(value = "/vipActivity/update",method = RequestMethod.POST)
    public ResponseVO updateVIPActivity(@RequestBody VIPActivityForm vipActivityForm){
        return vipActivityService.updateVIPActivity(vipActivityForm);
    }
}
