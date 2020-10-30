package com.example.cinema.blImpl.user;

import com.example.cinema.bl.user.AccountOfManagerService;
import com.example.cinema.data.user.AccountOfManagerMapper;
import com.example.cinema.po.ManagerPO;
import com.example.cinema.vo.ManagerForm;
import com.example.cinema.vo.ManagerUpdateVO;
import com.example.cinema.vo.ManagerVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountOfManagerServiceImpl implements AccountOfManagerService {
    @Autowired
    private AccountOfManagerMapper accountOfManagerMapper;

    /**
     * 添加管理员(用户名不能重复）
     * @param managerForm
     * @return
     */
    @Override
    public ResponseVO addManager(ManagerForm managerForm){
        try {
            accountOfManagerMapper.addManager(managerForm);
        }
        catch (Exception e){
            return ResponseVO.buildFailure("添加失败，用户名已存在");
        }
        return ResponseVO.buildSuccess();
    }

    /**
     * 删除管理员（根据用户名）
     * @param name 管理员的用户名
     * @return
     */
    @Override
    public ResponseVO deleteManager(String name){
        try{
            ManagerPO managerPO=accountOfManagerMapper.searchByName(name);
            if (managerPO==null){
                return ResponseVO.buildFailure("该用户不存在");
            }
            accountOfManagerMapper.deleteManager(name);
            return ResponseVO.buildSuccess();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("删除失败");
        }
    }

    /**
     * 修改管理员的信息
     * @param
     * @return
     */
    public ResponseVO updateManager(ManagerUpdateVO managerUpdateVO){
        try {
            accountOfManagerMapper.updateManager(managerUpdateVO);
            return ResponseVO.buildSuccess();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("修改失败");
        }
    }

    /**
     * 查找管理员的信息（根据用户名）
     * @param name 管理员的用户名
     * @return
     */
    public ResponseVO searchByName(String name){
        try {
            ManagerPO managerPO=accountOfManagerMapper.searchByName(name);
            if (managerPO==null){
                return ResponseVO.buildFailure("管理员不存在");
            }
            else {
                return ResponseVO.buildSuccess(managerPO.getManagerForm());
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("查询失败");
        }
    }

    @Override
    public ResponseVO searchAllManagers(){
        List<ManagerPO> managerPOS=accountOfManagerMapper.searchAllManagers();
        List<ManagerForm> managerForms=new ArrayList<>();
        for (int i = 0; i < managerPOS.size(); i++) {
            managerForms.add(managerPOS.get(i).getManagerForm());
        }
        return ResponseVO.buildSuccess(managerForms);
    }
    @Override
    public ManagerVO login(ManagerForm managerForm){
        ManagerPO managerPO=accountOfManagerMapper.searchByName(managerForm.getUsername());
        if (managerPO==null||!managerPO.getPassword().equals(managerForm.getPassword())){
            return null;
        }
        return new ManagerVO(managerPO);
    }
}
