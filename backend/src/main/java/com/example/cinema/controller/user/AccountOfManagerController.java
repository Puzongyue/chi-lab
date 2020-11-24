package com.example.cinema.controller.user;

import com.example.cinema.bl.user.AccountOfManagerService;
import com.example.cinema.config.InterceptorConfiguration;
import com.example.cinema.vo.ManagerForm;
import com.example.cinema.vo.ManagerUpdateVO;
import com.example.cinema.vo.ManagerVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author lyp
 * created on 2019.05.26
 */
@RestController()
@RequestMapping("/accountOfManager")
public class AccountOfManagerController {
    private final static String ACCOUNT_INFO_ERROR="用户名或密码错误";
    @Autowired
    private AccountOfManagerService accountOfManagerService;

    @PostMapping("/add")
    public ResponseVO addManager(@RequestBody ManagerForm managerForm){
        return accountOfManagerService.addManager(managerForm);
    }

    @DeleteMapping("/delete")
    public ResponseVO deleteManager(@RequestParam String name){
        return accountOfManagerService.deleteManager(name);
    }

    @PostMapping("/update")
    public ResponseVO updateManager(@RequestBody ManagerUpdateVO managerUpdateVO){
        return  accountOfManagerService.updateManager(managerUpdateVO);
    }

    @GetMapping("/get")
    public ResponseVO searchByName(@RequestParam String name){
        return accountOfManagerService.searchByName(name);
    }

    @GetMapping("/get/all")
    public ResponseVO searchAllManagers(){
        return accountOfManagerService.searchAllManagers();
    }
    @PostMapping("/login")
    public ResponseVO login(@RequestBody ManagerForm managerForm, HttpSession session){
        ManagerVO managerVO = accountOfManagerService.login(managerForm);
        if(managerVO==null){
            return ResponseVO.buildFailure(ACCOUNT_INFO_ERROR);
        }
        //注册session
        session.setAttribute(InterceptorConfiguration.SESSION_KEY,managerForm);
        return ResponseVO.buildSuccess(managerVO);
    }

    @PostMapping("/logout")
    public String logOut(HttpSession session){
        session.removeAttribute(InterceptorConfiguration.SESSION_KEY);
        return "index";
    }
}
