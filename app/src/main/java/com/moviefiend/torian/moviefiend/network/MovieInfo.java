package com.moviefiend.torian.moviefiend.network;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MovieInfo implements Parcelable {
    @SerializedName("id")
    int mId;
    @SerializedName("poster_path")
    String mPosterPath;
    @SerializedName("title")
    String mTitle;
    @SerializedName("vote_average")
    float mRating;
    @SerializedName("overview")
    String mDescription;

    public MovieInfo(Parcel source) {
        mId = source.readInt();
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
        dest.writeInt(mId);
        dest.writeString(mPosterPath);
        dest.writeString(mTitle);
        dest.writeFloat(mRating);
        dest.writeString(mDescription);
    }
    //</editor-fold>


    public int getId() {
        return mId;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getTitle() {
        return mTitle;
    }

    public float getRating() {
        return mRating;
    }

    public String getDescription() {
        return mDescription;
    }
}
