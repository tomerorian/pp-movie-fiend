package com.moviefiend.torian.moviefiend;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.moviefiend.torian.moviefiend.network.GsonRequest;
import com.moviefiend.torian.moviefiend.network.NowPlayingResponse;

public class MovieListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_list_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.movie_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMovieAdapter = new MovieAdapter();
        mRecyclerView.setAdapter(mMovieAdapter);

        loadMovies();

        return view;
    }

    private void loadMovies() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        // TODO: Extract key to gradle/resource (can also extract the url... maybe)
        String url ="http://api.themoviedb.org/3/movie/now_playing?api_key=4ef5ef8497673e7c432e66126be4a324";

        GsonRequest<NowPlayingResponse> request = new GsonRequest<>(url, NowPlayingResponse.class, null,
                new Response.Listener<NowPlayingResponse>() {
                    @Override
                    public void onResponse(NowPlayingResponse response) {
                        mMovieAdapter.setMovies(response.getMovies());
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
}
