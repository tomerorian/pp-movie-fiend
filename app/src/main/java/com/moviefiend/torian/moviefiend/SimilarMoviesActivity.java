package com.moviefiend.torian.moviefiend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.moviefiend.torian.moviefiend.network.MoviesResponse;

import java.util.ArrayList;

public class SimilarMoviesActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<ArrayList<MoviesResponse.MovieInfo>> {

    public static final String MOVIE_INFO_EXTRA = "movie_info";

    private static final int MOVIE_LOADER_ID = 0;

    private ViewPager mMoviePager;
    private MoviePagerAdapter mMoviePagerAdapter;
    private MoviesResponse.MovieInfo mMovieInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.similar_movies_activity);

        mMovieInfo = getIntent().getParcelableExtra(MOVIE_INFO_EXTRA);
        mMoviePagerAdapter = new MoviePagerAdapter(getSupportFragmentManager());

        mMoviePager = (ViewPager) findViewById(R.id.movie_pager);
        mMoviePager.setAdapter(mMoviePagerAdapter);

        getSupportLoaderManager().initLoader(MOVIE_LOADER_ID, null, this);
    }

    //<editor-fold desc="Loader">
    @Override
    public Loader<ArrayList<MoviesResponse.MovieInfo>> onCreateLoader(int id, Bundle args) {
        return new MovieInfoLoader(this, UrlHelper.getSimilarMoviesUrl(BuildConfig.TMDB_API_KEY, mMovieInfo.getId()));
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MoviesResponse.MovieInfo>> loader,
                               ArrayList<MoviesResponse.MovieInfo> data) {
        mMoviePagerAdapter.setMovies(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MoviesResponse.MovieInfo>> loader) {

    }
    //</editor-fold>

    private static class MoviePagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<MoviesResponse.MovieInfo> mMovies;

        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);

            mMovies = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            MovieDetailsFragment fragment = new MovieDetailsFragment();

            Bundle arguments = new Bundle();
            arguments.putParcelable(MovieDetailsFragment.MOVIE_INFO_ARG, mMovies.get(position));
            fragment.setArguments(arguments);

            return fragment;
        }

        @Override
        public int getCount() {
            return mMovies.size();
        }

        public void setMovies(ArrayList<MoviesResponse.MovieInfo> movies) {
            if (movies != null) {
                this.mMovies = movies;
            } else {
                this.mMovies = new ArrayList<>();
            }

            notifyDataSetChanged();
        }
    }
}
