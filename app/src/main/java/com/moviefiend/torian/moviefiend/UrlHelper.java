package com.moviefiend.torian.moviefiend;

import android.net.Uri;

import java.util.Locale;

public class UrlHelper {

    public static String getNowPlayingUrl(String apiKey) {
        Uri.Builder builder = new Uri.Builder();
        return builder.scheme("http")
                .authority("api.themoviedb.org")
                .appendEncodedPath("3/movie/now_playing")
                .appendQueryParameter("api_key", apiKey)
                .build()
                .toString();
    }

    public static String getPosterUrl(int size, String poster) {
        Uri.Builder builder = new Uri.Builder();
        return builder.scheme("http")
                .authority("image.tmdb.org")
                .appendEncodedPath("t/p")
                .appendPath(String.format(Locale.US, "w%d", size))
                .appendEncodedPath(poster)
                .build()
                .toString();
    }
}
