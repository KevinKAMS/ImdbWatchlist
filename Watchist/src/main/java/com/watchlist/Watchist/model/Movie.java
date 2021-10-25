package com.watchlist.Watchist.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import info.movito.themoviedbapi.model.MovieDb;

@Entity
public class Movie implements Serializable, Comparable<Movie>{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;  
    private String title;
    private String year;
    private String rate;
    private boolean favorite;
    private String imageUrl;

    public Movie() {}

    public Movie(Long id, String title, String year, String rate, boolean favorite, String imageUrl){
        this.id = id;
        this.title = title;
        this.year = year;
        this.rate = rate;
        this.favorite = favorite;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int compareTo(Movie movie) {
        if (getYear() == null || movie.getYear() == null) {
        return 0;
        }
        return getYear().compareTo(movie.getYear());
    }

    @Override
    public String toString() {
        return "Movie{"+
                "id=" + id + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", rate='" + rate + '\''+
                ", favorite=" + favorite +
                "}";
    }

    public Movie saveMovieFromApi(MovieDb apiMovie) {
        this.title = apiMovie.getTitle();
        this.year = apiMovie.getReleaseDate();
        if(this.year!=null|!this.year.isEmpty()){
            this.year = this.year.substring(0,4);
        } else {
            this.year = "Unknow Release Year";
        }
        this.imageUrl = apiMovie.getPosterPath();
        if (this.imageUrl!=null){
            this.imageUrl = "https://image.tmdb.org/t/p/original/"+this.imageUrl;
        } else {
            this.imageUrl = "";
        }
        this.rate = Float.toString(apiMovie.getVoteAverage());

        return this;
    }



}