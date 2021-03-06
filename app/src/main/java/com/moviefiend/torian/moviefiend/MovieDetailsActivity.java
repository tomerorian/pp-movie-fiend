package com.moviefiend.torian.moviefiend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.moviefiend.torian.moviefiend.network.MoviesResponse;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsFragment.Listener {

    public static final String MOVIE_INFO_EXTRA = "movie_info";

    private MoviesResponse.MovieInfo mMovieInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);

        mMovieInfo = getIntent().getParcelableExtra(MOVIE_INFO_EXTRA);

        setTitle(mMovieInfo.getTitle());

        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(MovieDetailsFragment.MOVIE_INFO_ARG, mMovieInfo);
        fragment.setArguments(arguments);
        fragment.setListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_fragment, fragment)
                .commit();

    }

    @Override
    public void onSimilarMoviesClicked(MoviesResponse.MovieInfo movieInfo) {
        Intent intent = new Intent(this, SimilarMoviesActivity.class);
        intent.putExtra(SimilarMoviesActivity.MOVIE_INFO_EXTRA, mMovieInfo);
        startActivity(intent);
    }
}
