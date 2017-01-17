package com.moviefiend.torian.moviefiend.network;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NowPlayingResponse {
    @SerializedName("results")
    private ArrayList<MovieInfo> mMovies;

    public ArrayList<MovieInfo> getMovies() {
        return mMovies;
    }

    public static class MovieInfo implements Parcelable {
        @SerializedName("poster_path")
        String mPosterPath;
        @SerializedName("title")
        String mTitle;
        @SerializedName("vote_average")
        Float mRating;
        @SerializedName("overview")
        String mDescription;

        public MovieInfo(Parcel source) {
            mPosterPath = source.readString();
            mTitle = source.readString();
            mRating = source.readFloat();
            mDescription = source.readString();
        }

        //<editor-fold desc="Parcelable">
        public static final Parcelable.Creator<MovieInfo> CREATOR = new ClassLoaderCreator<MovieInfo>() {
            @Override
            public MovieInfo createFromParcel(Parcel source, ClassLoader loader) {
                return new MovieInfo(source);
            }

            @Override
            public MovieInfo createFromParcel(Parcel source) {
                return new MovieInfo(source);
            }

            @Override
            public MovieInfo[] newArray(int size) {
                return new MovieInfo[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(mPosterPath);
            dest.writeString(mTitle);
            dest.writeFloat(mRating);
            dest.writeString(mDescription);
        }
        //</editor-fold>

        public String getPosterPath() {
            return mPosterPath;
        }

        public String getTitle() {
            return mTitle;
        }

        public Float getRating() {
            return mRating;
        }

        public String getDescription() {
            return mDescription;
        }
    }
}
