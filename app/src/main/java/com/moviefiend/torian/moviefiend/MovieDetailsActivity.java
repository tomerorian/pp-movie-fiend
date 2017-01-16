package com.moviefiend.torian.moviefiend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String MOVIE_INFO_EXTRA = "movie_info";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);

        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(getIntent().getExtras());

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_fragment, fragment)
                .commit();

    }
}
