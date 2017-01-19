package com.moviefiend.torian.moviefiend.network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MoviesResponse {
    @SerializedName("results")
    private ArrayList<MovieInfo> mMovies;

    public ArrayList<MovieInfo> getMovies() {
        return mMovies;
    }
}
