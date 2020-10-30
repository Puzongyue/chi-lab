package com.example.cinema.po;

import com.example.cinema.vo.ManagerForm;
import com.example.cinema.vo.ManagerVO;

public class ManagerPO {
    private String username;
    private String password;
    private int managerID;

    public ManagerForm getManagerForm(){
        ManagerForm managerForm=new ManagerForm();
        managerForm.setUsername(this.username);
        managerForm.setPassword(this.password);
        return managerForm;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }
}
