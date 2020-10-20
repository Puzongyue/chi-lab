package com.example.cinema.vo;

public class TestVO {
    private double placingRate;
    private String name;

    public TestVO(double placingRate, String name){
        this.placingRate = placingRate;
        this.name = name;
    }

    public double getTestData() {
        return placingRate;
    }

    public String getName() {
        return name;
    }
}
