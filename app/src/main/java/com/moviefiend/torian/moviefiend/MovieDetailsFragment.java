package com.moviefiend.torian.moviefiend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moviefiend.torian.moviefiend.network.MoviesResponse;

public class MovieDetailsFragment extends Fragment implements MovieDetailsCustomView.Listener {

    public interface Listener {
        void onSimilarMoviesClicked(MoviesResponse.MovieInfo movieInfo);
    }

    public static final String MOVIE_INFO_ARG = "movie_info";

    private MoviesResponse.MovieInfo mMovieInfo;
    private Listener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_details_fragment, container, false);
        MovieDetailsCustomView customView = (MovieDetailsCustomView) view;

        mMovieInfo = getArguments().getParcelable(MOVIE_INFO_ARG);
        customView.setMovie(mMovieInfo);
        customView.setListener(this);

        if (getUserVisibleHint()) {
            setTitle(mMovieInfo.getTitle());
        }

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && mMovieInfo != null && getActivity() != null) {
            setTitle(mMovieInfo.getTitle());
        }
    }

    @Override
    public void onSimilarMoviesClicked(MoviesResponse.MovieInfo movieInfo) {
        if (mListener != null) {
            mListener.onSimilarMoviesClicked(movieInfo);
        }
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    private void setTitle(String title) {
        getActivity().setTitle(title);
    }
}
