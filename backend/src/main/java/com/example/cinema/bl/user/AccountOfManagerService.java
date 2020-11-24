package com.example.cinema.bl.user;

import com.example.cinema.vo.ManagerForm;
import com.example.cinema.vo.ManagerUpdateVO;
import com.example.cinema.vo.ManagerVO;
import com.example.cinema.vo.ResponseVO;
/**
 * @author lyp
 * created on 2019.05.26
 */
public interface AccountOfManagerService {
    /**
     * 添加管理员
     * @param managerForm
     * @return
     */
    ResponseVO addManager(ManagerForm managerForm);

    /**
     * 删除管理员（根据用户名）
     * @param name 管理员的用户名
     * @return
     */
    ResponseVO deleteManager(String name);

    /**
     * 修改管理员的信息
     * @param
     * @return
     */
    ResponseVO updateManager(ManagerUpdateVO managerUpdateVO);

    /**
     * 查找管理员的信息（根据用户名）
     * @param name 管理员的用户名
     * @return
     */
    ResponseVO searchByName(String name);

    /**
     * 返回所有管理员的信息
     */
    ResponseVO searchAllManagers();
    /**
     * 管理员登录，登录成功会将用户信息保存在session中
     * @return
     */
    ManagerVO login(ManagerForm managerForm);

}
