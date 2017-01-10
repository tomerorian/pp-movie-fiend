package com.moviefiend.torian.moviefiend;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private TextView mTitleView;
    private ImageView mPosterView;
    private TextView mRatingView;

    public MovieViewHolder(View itemView) {
        super(itemView);

        mTitleView = (TextView) itemView.findViewById(R.id.title);
        mPosterView = (ImageView) itemView.findViewById(R.id.poster);
        mRatingView = (TextView) itemView.findViewById(R.id.rating);
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
        mRatingView.setText(itemView.getContext().getString(R.string.rating_lable, rating));
    }
}