package com.moviefiend.torian.moviefiend;

import android.content.Context;
import android.content.Loader;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.moviefiend.torian.moviefiend.network.GsonRequest;
import com.moviefiend.torian.moviefiend.network.NowPlayingResponse;

import java.util.ArrayList;

public class MovieInfoLoader extends Loader<ArrayList<NowPlayingResponse.MovieInfo>> {

    public MovieInfoLoader(Context context) {
        super(context);
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
        String url = getContext().getString(R.string.tmdb_now_playing_url, BuildConfig.TMDB_API_KEY);

        GsonRequest<NowPlayingResponse> request = new GsonRequest<>(url, NowPlayingResponse.class, null,
                new Response.Listener<NowPlayingResponse>() {
                    @Override
                    public void onResponse(NowPlayingResponse response) {
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
