package com.example.cinema.po;

public class MoviePlacingRate implements Comparable<MoviePlacingRate> {

    private Integer movieId;
    private String name;
    private Integer ticketCount;
    private Integer totalSeat;
    private Double placingRate;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public Integer getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(Integer totalSeat) {
        this.totalSeat = totalSeat;
    }

    public Double getPlacingRate() {
        return placingRate;
    }

    public void setPlacingRate(Double moviePlacing) {
        this.placingRate = moviePlacing;
    }

    @Override
    public int compareTo(MoviePlacingRate o) {
        return this.placingRate.compareTo(o.placingRate);
    }
}
