package com.example.cinema.vo;

public class PopularMovieVO {
    private int movieId;
    private String name;
    private int popularRank;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularRank() {
        return popularRank;
    }

    public void setPopularRank(int popularRank) {
        this.popularRank = popularRank;
    }
}
