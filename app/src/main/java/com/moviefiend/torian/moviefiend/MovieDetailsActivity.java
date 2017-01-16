package com.moviefiend.torian.moviefiend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.moviefiend.torian.moviefiend.network.MoviesResponse;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsFragment.Listener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);

        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(getIntent().getExtras());
        fragment.setListener(this);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.content_fragment, fragment)
                .commit();

    }

    @Override
    public void onSimilarMoviesClicked(MoviesResponse.MovieInfo movieInfo) {
        Intent intent = new Intent(this, SimilarMoviesActivity.class);
        startActivity(intent);
    }
}
