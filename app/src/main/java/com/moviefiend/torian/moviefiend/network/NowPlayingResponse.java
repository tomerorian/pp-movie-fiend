package com.moviefiend.torian.moviefiend.network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NowPlayingResponse {
    @SerializedName("results")
    private ArrayList<MovieInfo> mMovies;

    public ArrayList<MovieInfo> getMovies() {
        return mMovies;
    }

    public static class MovieInfo {
        @SerializedName("poster_path")
        String mPosterPath;
        String mTitle;
        @SerializedName("vote_average")
        Float rating;

        public String getPosterPath() {
            return mPosterPath;
        }

        public String getTitle() {
            return mTitle;
        }

        public Float getRating() {
            return rating;
        }
    }
}
