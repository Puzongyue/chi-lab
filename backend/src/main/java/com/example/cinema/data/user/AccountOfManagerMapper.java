package com.example.cinema.data.user;

import com.example.cinema.po.ManagerPO;
import com.example.cinema.vo.ManagerForm;
import com.example.cinema.vo.ManagerUpdateVO;
import com.example.cinema.vo.ManagerVO;
import com.example.cinema.vo.ResponseVO;
import org.apache.catalina.Manager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountOfManagerMapper {
    /**
     * 添加管理员
     * @param managerForm
     * @return
     */
    int addManager(ManagerForm managerForm);

    /**
     * 删除管理员（根据用户名
     * @param
     * @return
     */
    int deleteManager(String name);

    /**
     * 修改管理员的信息
     * @param
     * @return
     */
    int updateManager(ManagerUpdateVO managerUpdateVO);

    /**
     * 查找管理员的信息（根据用户名）
     * @param
     * @return
     */
    ManagerPO searchByName(String name);

    /**
     * 查找所有的管理员
     * @return
     */
    List<ManagerPO> searchAllManagers();
}
