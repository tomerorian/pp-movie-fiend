package com.moviefiend.torian.moviefiend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.moviefiend.torian.moviefiend.network.MoviesResponse;

public class MainActivity extends AppCompatActivity implements MovieListFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieListFragment fragment = (MovieListFragment) getSupportFragmentManager().findFragmentById(R.id.movie_list_fragment);
        fragment.setListener(this);
    }

    @Override
    public void onMovieSelected(MoviesResponse.MovieInfo movieInfo) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("movie_info", movieInfo);
        startActivity(intent);
    }
}
