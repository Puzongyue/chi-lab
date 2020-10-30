package com.example.cinema.vo;

/**
 * @author tianduyingcai
 */
public class PlacingRateVO {
    private int movieId;
    private double placingRate;
    private String name;
    public int getMovieId(){
        return movieId;
    }
    public void setMovieId(int movieId){
        this.movieId=movieId;
    }
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPlacingRate() {
        return placingRate;
    }

    public void setPlacingRate(double placingRate) {
        this.placingRate = placingRate;
    }

    public PlacingRateVO(int movieId,double placingRate){
        this.movieId=movieId;this.placingRate=placingRate;
    }
}
