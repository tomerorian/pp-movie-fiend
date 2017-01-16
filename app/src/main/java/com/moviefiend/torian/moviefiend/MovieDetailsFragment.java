package com.moviefiend.torian.moviefiend;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviefiend.torian.moviefiend.network.NowPlayingResponse;
import com.squareup.picasso.Picasso;

public class MovieDetailsFragment extends Fragment {

    private NowPlayingResponse.MovieInfo mMovieInfo;
    private TextView mTitleView;
    private ImageView mPosterView;
    private TextView mRatingView;
    private TextView mDescriptionView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_details_fragment, container, false);

        mMovieInfo = getArguments().getParcelable("movie_info");

        mTitleView = (TextView) view.findViewById(R.id.title);
        mPosterView = (ImageView) view.findViewById(R.id.poster);
        mRatingView = (TextView) view.findViewById(R.id.rating);
        mDescriptionView = (TextView) view.findViewById(R.id.description);

        setTitle(mMovieInfo.getTitle());
        setPosterView(getString(R.string.tmdb_poster_url, mMovieInfo.getPosterPath()));
        setRating(mMovieInfo.getRating());
        setDescription("TODO TODO TODO");

        return view;
    }

    private void setTitle(String title) {
        mTitleView.setText(title);
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
