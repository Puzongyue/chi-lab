package com.example.cinema.po;

public class MovieLatestBoxOffice implements Comparable<MovieLatestBoxOffice> {
    private Integer movieId;
    private Integer boxOffice;
    private String name;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(MovieLatestBoxOffice o) {
        return this.boxOffice.compareTo(o.boxOffice);
    }
}
