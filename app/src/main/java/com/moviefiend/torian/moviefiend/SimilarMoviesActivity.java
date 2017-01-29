package com.moviefiend.torian.moviefiend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.moviefiend.torian.moviefiend.network.MovieInfo;

import java.util.ArrayList;
import java.util.List;

public class SimilarMoviesActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<MovieInfo>>, MovieDetailsFragment.Listener, ViewPager.OnPageChangeListener {

    public static final String MOVIE_INFO_EXTRA = "movie_info";

    private static final int MOVIE_LOADER_ID = 0;

    private ViewPager mMoviePager;
    private MoviePagerAdapter mMoviePagerAdapter;
    private MovieInfo mMovieInfo;
    private List<MovieInfo> mSimilarMovies;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.similar_movies_activity);

        mMovieInfo = getIntent().getParcelableExtra(MOVIE_INFO_EXTRA);
        mMoviePagerAdapter = new MoviePagerAdapter(getSupportFragmentManager(), this);

        mMoviePager = (ViewPager) findViewById(R.id.movie_pager);
        mMoviePager.setAdapter(mMoviePagerAdapter);
        mMoviePager.addOnPageChangeListener(this);

        getSupportLoaderManager().initLoader(MOVIE_LOADER_ID, null, this);
    }

    //<editor-fold desc="Loader">
    @Override
    public Loader<List<MovieInfo>> onCreateLoader(int id, Bundle args) {
        return new MovieInfoListLoader(this, UrlHelper.getSimilarMoviesUrl(BuildConfig.TMDB_API_KEY, mMovieInfo.getId()));
    }

    @Override
    public void onLoadFinished(Loader<List<MovieInfo>> loader,
                               List<MovieInfo> data) {
        mSimilarMovies = data;
        mMoviePagerAdapter.setMovies(mSimilarMovies);

        setTitle(mSimilarMovies.get(0).getTitle());
    }

    @Override
    public void onLoaderReset(Loader<List<MovieInfo>> loader) {

    }
    //</editor-fold>

    //<editor-fold desc="MovieDetailsFragment.Listener">
    @Override
    public void onSimilarMoviesClicked(MovieInfo movieInfo) {
        Intent intent = new Intent(this, SimilarMoviesActivity.class);
        intent.putExtra(MOVIE_INFO_EXTRA, movieInfo);
        startActivity(intent);
    }
    //</editor-fold>

    //<editor-fold desc="ViewPager.PageChangeListener">
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        setTitle(mSimilarMovies.get(position).getTitle());
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
    //</editor-fold>

    private static class MoviePagerAdapter extends FragmentStatePagerAdapter {

        private List<MovieInfo> mMovies;
        private MovieDetailsFragment.Listener mDetailsFragmentListener;

        public MoviePagerAdapter(FragmentManager fm, MovieDetailsFragment.Listener detailsFragmentListener) {
            super(fm);

            mDetailsFragmentListener = detailsFragmentListener;
            mMovies = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            MovieDetailsFragment fragment = new MovieDetailsFragment();

            fragment.setListener(mDetailsFragmentListener);

            Bundle arguments = new Bundle();
            arguments.putParcelable(MovieDetailsFragment.MOVIE_INFO_ARG, mMovies.get(position));
            fragment.setArguments(arguments);

            return fragment;
        }

        @Override
        public int getCount() {
            return mMovies.size();
        }

        public void setMovies(List<MovieInfo> movies) {
            if (movies != null) {
                this.mMovies = movies;
            } else {
                this.mMovies = new ArrayList<>();
            }

            notifyDataSetChanged();
        }
    }
}
