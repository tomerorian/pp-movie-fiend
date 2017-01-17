package com.moviefiend.torian.moviefiend;

import android.content.Context;
import android.support.v4.content.Loader;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.moviefiend.torian.moviefiend.network.GsonRequest;
import com.moviefiend.torian.moviefiend.network.MoviesResponse;

import java.util.ArrayList;

public class MovieInfoLoader extends Loader<ArrayList<MoviesResponse.MovieInfo>> {

    private String mUrl;

    public MovieInfoLoader(Context context, String url) {
        super(context);

        mUrl = url;
    }

    @Override
    protected void onForceLoad() {
        loadMovies();
    }

    @Override
    protected void onStartLoading() {
        loadMovies();
    }

    private void loadMovies() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        GsonRequest<MoviesResponse> request = new GsonRequest<>(mUrl, MoviesResponse.class, null,
                new Response.Listener<MoviesResponse>() {
                    @Override
                    public void onResponse(MoviesResponse response) {
                        deliverResult(response.getMovies());
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
