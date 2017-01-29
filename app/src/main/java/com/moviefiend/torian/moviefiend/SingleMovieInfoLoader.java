package com.moviefiend.torian.moviefiend;

import android.content.Context;
import android.support.v4.content.Loader;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.moviefiend.torian.moviefiend.network.GsonRequest;
import com.moviefiend.torian.moviefiend.network.MovieInfo;

public class SingleMovieInfoLoader extends Loader<MovieInfo> {

    private final String mMovieId;

    public SingleMovieInfoLoader(Context context, String movieId) {
        super(context);

        mMovieId = movieId;
    }

    @Override
    protected void onForceLoad() {
        loadMovie();
    }

    @Override
    protected void onStartLoading() {
        loadMovie();
    }

    private void loadMovie() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        GsonRequest<MovieInfo> request = new GsonRequest<>(UrlHelper.getMovieDetailsUrl(BuildConfig.TMDB_API_KEY, mMovieId),
                                                                                        MovieInfo.class, null,
                new Response.Listener<MovieInfo>() {
                    @Override
                    public void onResponse(MovieInfo response) {
                        deliverResult(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        deliverResult(null);
                    }
                });
        queue.add(request);
    }
}
