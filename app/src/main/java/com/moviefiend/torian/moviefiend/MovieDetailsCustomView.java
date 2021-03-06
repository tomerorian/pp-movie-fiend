package com.moviefiend.torian.moviefiend;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.moviefiend.torian.moviefiend.network.MoviesResponse;
import com.squareup.picasso.Picasso;

public class MovieDetailsCustomView extends ScrollView {

    public interface Listener {
        void onSimilarMoviesClicked(MoviesResponse.MovieInfo movieInfo);
    }

    private static final int MOVIE_POSTER_SIZE = 780;

    private ImageView mPosterView;
    private RatingView mRatingView;
    private TextView mDescriptionView;
    private TextView mSimilarMoviesBtn;
    private MoviesResponse.MovieInfo mMovieInfo;
    private Listener mListener;

    public MovieDetailsCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.movie_details_custom_view, this, true);
        findChildren();
    }

    public void setMovie(MoviesResponse.MovieInfo movie) {
        mMovieInfo = movie;

        setPosterView(UrlHelper.getPosterUrl(MOVIE_POSTER_SIZE, mMovieInfo.getPosterPath()));
        setRating(mMovieInfo.getRating());
        setDescription(mMovieInfo.getDescription());

        mSimilarMoviesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onSimilarMoviesClicked(mMovieInfo);
                }
            }
        });
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    private void findChildren() {
        mPosterView = (ImageView) findViewById(R.id.poster);
        mRatingView = (RatingView) findViewById(R.id.rating);
        mDescriptionView = (TextView) findViewById(R.id.description);
        mSimilarMoviesBtn = (TextView) findViewById(R.id.similar_movies);
    }

    private void setPosterView(String url) {
        Picasso.with(getContext())
                .load(url)
                .placeholder(R.drawable.movie_placeholder)
                .into(mPosterView);
    }

    private void setRating(float rating) {
        mRatingView.setMinStarsShown((int) Math.ceil(rating));

        ObjectAnimator animator = ObjectAnimator.ofFloat(mRatingView, "rating", 0, rating);
        animator.setDuration(2500);
        animator.start();
    }

    private void setDescription(String description) {
        mDescriptionView.setText(description);
    }
}
