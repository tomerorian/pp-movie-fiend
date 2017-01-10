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
        RequestQueue queue = Volley.newRequestQueue(getContext());
        // TODO: Extract key to gradle/resource (can also extract the url... maybe)
        String url ="http://api.themoviedb.org/3/movie/now_playing?api_key=4ef5ef8497673e7c432e66126be4a324";

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
                        // TODO: Error
                    }
                });
        queue.add(request);
    }

    @Override
    protected void onStartLoading() {
        onForceLoad();
    }
}
