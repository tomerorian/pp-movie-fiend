package com.moviefiend.torian.moviefiend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.moviefiend.torian.moviefiend.network.MovieInfo;

public class MainActivity extends AppCompatActivity implements MovieListFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieListFragment fragment = (MovieListFragment) getSupportFragmentManager().findFragmentById(R.id.movie_list_fragment);
        fragment.setListener(this);
    }

    @Override
    public void onMovieSelected(MovieInfo movieInfo) {
        Intent intent = MovieDetailsActivity.createIntent(this, movieInfo);
        startActivity(intent);
    }
}
