package com.gwr.nextmovie.domain;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by willi on 09/07/2016.
 */
public class Movie implements Serializable{

    private long id;
    private String original_title;
    private double vote_average;
    private String poster_path;
    private String overview;
    private String release_date;
    private String backdrop_path;

    public Movie(long id,String original_title, double vote_average, String poster_path, String overview, String relase_date, String backdrop_path) {
        this.id = id;
        this.original_title = original_title;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = relase_date;
        this.backdrop_path = backdrop_path;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String relase_date) {
        this.release_date = relase_date;
    }
}
