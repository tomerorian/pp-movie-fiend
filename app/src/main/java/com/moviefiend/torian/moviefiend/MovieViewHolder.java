package com.moviefiend.torian.moviefiend;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviefiend.torian.moviefiend.network.MovieInfo;
import com.squareup.picasso.Picasso;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    public interface MovieClickListener {
        void onMovieClicked(MovieInfo movieInfo);
    }

    public static final int MOVIE_POSTER_SIZE = 154;

    private TextView mTitleView;
    private ImageView mPosterView;
    private TextView mRatingView;
    private MovieInfo mMovieInfo;
    private MovieClickListener mClickListener;

    public MovieViewHolder(View itemView, MovieClickListener clickListener) {
        super(itemView);

        mClickListener = clickListener;

        mTitleView = (TextView) itemView.findViewById(R.id.title);
        mPosterView = (ImageView) itemView.findViewById(R.id.poster);
        mRatingView = (TextView) itemView.findViewById(R.id.rating);
    }

    public void bindMovie(MovieInfo movieInfo) {
        mMovieInfo = movieInfo;

        setTitle(mMovieInfo.getTitle());
        setPosterView(UrlHelper.getPosterUrl(MOVIE_POSTER_SIZE, mMovieInfo.getPosterPath()));
        setRating(mMovieInfo.getRating());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onMovieClicked(mMovieInfo);
                }
            }
        });
    }

    public void setTitle(String title) {
        mTitleView.setText(title);
    }

    public void setPosterView(String url) {
        Picasso.with(itemView.getContext())
                .load(url)
                .fit()
                .centerInside()
                .into(mPosterView);
    }

    public void setRating(float rating) {
        mRatingView.setText(itemView.getContext().getString(R.string.rating_label, rating));
    }
}
