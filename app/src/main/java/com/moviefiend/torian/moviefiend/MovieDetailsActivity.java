package com.moviefiend.torian.moviefiend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.moviefiend.torian.moviefiend.network.NowPlayingResponse;

public class MovieDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);

        NowPlayingResponse.MovieInfo movieInfo = getIntent().getParcelableExtra("movie_info");
    }
}
