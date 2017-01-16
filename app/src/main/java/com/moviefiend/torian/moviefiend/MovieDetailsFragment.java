package com.moviefiend.torian.moviefiend;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moviefiend.torian.moviefiend.network.NowPlayingResponse;

public class MovieDetailsFragment extends Fragment {

    private NowPlayingResponse.MovieInfo mMovieInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_details_fragment, container, false);

        mMovieInfo = getArguments().getParcelable("movie_info");

        return view;
    }
}
