package com.moviefiend.torian.moviefiend;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.moviefiend.torian.moviefiend.network.MovieInfo;

public class MovieDetailsLoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<MovieInfo> {

    private static final int MOVIE_LOADER_ID = 0;
    private String mMovieId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_loader_activity);

        Uri data = getIntent().getData();
        mMovieId = data.getQueryParameter("id");

        if (mMovieId != null) {
            getSupportLoaderManager().initLoader(MOVIE_LOADER_ID, null, this);
        } else {
            gotoHome();
        }
    }

    //<editor-fold desc="Loader">
    @Override
    public Loader<MovieInfo> onCreateLoader(int id, Bundle args) {
        return new SingleMovieInfoLoader(this, mMovieId);
    }

    @Override
    public void onLoadFinished(Loader<MovieInfo> loader, MovieInfo data) {
        if (data != null) {
            Intent intent = MovieDetailsActivity.createIntent(this, data);

            TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(intent)
                    .startActivities();

            finish();
        } else {
            gotoHome();
        }
    }

    @Override
    public void onLoaderReset(Loader<MovieInfo> loader) {
    }
    //</editor-fold>


    @Override
    public void onBackPressed() {
        gotoHome();
    }

    private void gotoHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
