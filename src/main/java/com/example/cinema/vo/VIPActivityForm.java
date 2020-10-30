package com.example.cinema.vo;


import com.example.cinema.po.VIPActivity;

/**
 * @author lyp
 * created on 2019.05.26
 */
public class VIPActivityForm {
    private String name;
    private float costInNeed; //固定金额减免所需要的最低消费
    private int id;
    private float bonusBalance;
    private String description;
    public VIPActivityForm(VIPActivity activity){
        name=activity.getName();
        costInNeed=activity.getCost_in_need();id=activity.getId();
        bonusBalance=activity.getBonus_balance();
        description=activity.getDescription();
    }
    public VIPActivityForm(){

    }
    public float getBonusBalance() {
        return bonusBalance;
    }

    public void setBonusBalance(float bonusBalance) {
        this.bonusBalance = bonusBalance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public float getCostInNeed() {
        return costInNeed;
    }

    public void setCostInNeed(float costInNeed) {
        this.costInNeed = costInNeed;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}