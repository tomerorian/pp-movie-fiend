package com.moviefiend.torian.moviefiend.network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NowPlayingResponse {
    @SerializedName("results")
    private ArrayList<MovieInfo> movies;

    public ArrayList<MovieInfo> getMovies() {
        return movies;
    }

    public static class MovieInfo {
        @SerializedName("poster_path")
        String posterPath;
        String title;
        @SerializedName("vote_average")
        Float rating;

        public String getPosterPath() {
            return posterPath;
        }

        public String getTitle() {
            return title;
        }

        public Float getRating() {
            return rating;
        }
    }
}
