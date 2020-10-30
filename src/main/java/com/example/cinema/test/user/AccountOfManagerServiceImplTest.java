package com.example.cinema.test.user;

import com.example.cinema.blImpl.user.AccountOfManagerServiceImpl;
import com.example.cinema.vo.ManagerForm;
import com.example.cinema.vo.ManagerUpdateVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountOfManagerServiceImplTest {
    @Autowired
    AccountOfManagerServiceImpl accountOfManagerService;
    @Test
    public void addManager() {
        ManagerForm managerForm=new ManagerForm();
        managerForm.setPassword("12efrf");
        managerForm.setUsername("frefeg");
        accountOfManagerService.addManager(managerForm);
    }

    @Test
    public void deleteManager() {
        accountOfManagerService.deleteManager("frefeg");
    }

    @Test
    public void updateManager() {
        ManagerUpdateVO managerUpdateVO=new ManagerUpdateVO();
        managerUpdateVO.setOldname("frefeg");
        managerUpdateVO.setPassword("lllkkkii");
        managerUpdateVO.setUsername("hhhii");
        accountOfManagerService.updateManager(managerUpdateVO);
    }

    @Test
    public void searchByName() {
        ManagerForm managerForm=(ManagerForm)accountOfManagerService.searchByName("hhhii").getContent();
        System.out.println(managerForm.getPassword());
        System.out.println(managerForm.getUsername());
    }

    @Test
    public void searchAllManagers() {
        List<ManagerForm> managerForms=(List<ManagerForm>)accountOfManagerService.searchAllManagers().getContent();
        for (int i = 0; i < managerForms.size(); i++) {
            System.out.println(managerForms.get(i).getUsername());
            System.out.println(managerForms.get(i).getPassword());

        }
    }
}
