package com.zuehlke.movieticketservice;

import java.util.List;

public class MovieDetail {

    public MovieDetail(int id, String title, String poster, String plot, int year, String genre, List<Rating> ratings) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.plot = plot;
        this.year = year;
        this.genre = genre;
        this.ratings = ratings;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getPlot() {
        return plot;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    int id;
    String title;
    String poster;
    String plot;
    int year;
    String genre;
    List<Rating> ratings;

    public MovieDetail setRatings(List<Rating> ratings) {
        this.ratings = ratings;
        return this;
    }
}
