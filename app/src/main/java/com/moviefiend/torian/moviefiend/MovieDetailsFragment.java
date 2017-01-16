package com.moviefiend.torian.moviefiend;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviefiend.torian.moviefiend.network.MoviesResponse;
import com.squareup.picasso.Picasso;

public class MovieDetailsFragment extends Fragment {

    public interface Listener {
        void onSimilarMoviesClicked(MoviesResponse.MovieInfo movieInfo);
    }

    public static final int MOVIE_POSTER_SIZE = 780;

    private MoviesResponse.MovieInfo mMovieInfo;
    private ImageView mPosterView;
    private TextView mRatingView;
    private TextView mSimilarMoviesBtn;
    private TextView mDescriptionView;
    private Listener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_details_fragment, container, false);

        mMovieInfo = getArguments().getParcelable("movie_info");

        mPosterView = (ImageView) view.findViewById(R.id.poster);
        mRatingView = (TextView) view.findViewById(R.id.rating);
        mDescriptionView = (TextView) view.findViewById(R.id.description);
        mSimilarMoviesBtn = (TextView) view.findViewById(R.id.similar_movies);

        setTitle(mMovieInfo.getTitle());
        setPosterView(getString(R.string.tmdb_poster_url, MOVIE_POSTER_SIZE, mMovieInfo.getPosterPath()));
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

        return view;
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    private void setTitle(String title) {
        getActivity().setTitle(title);
    }

    private void setPosterView(String url) {
        Picasso.with(getActivity())
                .load(url)
                .into(mPosterView);
    }

    private void setRating(float rating) {
        mRatingView.setText(getString(R.string.rating_lable, rating));
    }

    private void setDescription(String description) {
        mDescriptionView.setText(description);
    }
}
