package com.moviefiend.torian.moviefiend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moviefiend.torian.moviefiend.network.MoviesResponse;

import java.util.ArrayList;

public class MovieListFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<MoviesResponse.MovieInfo>>,MovieViewHolder.MovieClickListener {

    public interface Listener {
        void onMovieSelected(MoviesResponse.MovieInfo movieInfo);
    }

    private static final int MOVIE_LOADER_ID = 0;

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private Listener mListener;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(MOVIE_LOADER_ID, null, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_list_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.movie_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMovieAdapter = new MovieAdapter();
        mMovieAdapter.setClickListener(this);
        mRecyclerView.setAdapter(mMovieAdapter);

        return view;
    }

    // <editor-fold desc="Loader">
    @Override
    public Loader<ArrayList<MoviesResponse.MovieInfo>> onCreateLoader(int id, Bundle args) {
        return new MovieInfoLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MoviesResponse.MovieInfo>> loader, ArrayList<MoviesResponse.MovieInfo> data) {
        mMovieAdapter.setMovies(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MoviesResponse.MovieInfo>> loader) {

    }
    // </editor-fold>

    //<editor-fold desc="MovieClickListener">
    @Override
    public void onMovieClicked(MoviesResponse.MovieInfo movieInfo) {
        mListener.onMovieSelected(movieInfo);
    }
    //</editor-fold>

    public void setListener(Listener listener) {
        mListener = listener;
    }
}
