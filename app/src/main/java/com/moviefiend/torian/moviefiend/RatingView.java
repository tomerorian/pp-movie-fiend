package com.moviefiend.torian.moviefiend;

import android.content.Context;
import android.support.annotation.FloatRange;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RatingView extends LinearLayout {

    ArrayList<StarView> mStarViews;
    TextView mRatingText;

    public RatingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.rating_view, this, true);

        mRatingText = (TextView) findViewById(R.id.rating_text);

        mStarViews = new ArrayList<>(10);
        mStarViews.add(0, (StarView) findViewById(R.id.star0));
        mStarViews.add(1, (StarView) findViewById(R.id.star1));
        mStarViews.add(2, (StarView) findViewById(R.id.star2));
        mStarViews.add(3, (StarView) findViewById(R.id.star3));
        mStarViews.add(4, (StarView) findViewById(R.id.star4));
        mStarViews.add(5, (StarView) findViewById(R.id.star5));
        mStarViews.add(6, (StarView) findViewById(R.id.star6));
        mStarViews.add(7, (StarView) findViewById(R.id.star7));
        mStarViews.add(8, (StarView) findViewById(R.id.star8));
        mStarViews.add(9, (StarView) findViewById(R.id.star9));
    }

    public void setRating(@FloatRange(from=0.0, to=10.0) float rating) {
        mRatingText.setText(getContext().getString(R.string.rating_label, rating));

        int roundedRating = (int) Math.ceil(rating);

        for (int i = 0; i < roundedRating; i++) {
            mStarViews.get(i).setVisibility(VISIBLE);

            @FloatRange(from=0.0, to=1.0)
            float percent = i < (rating - 1) ? 1.0f : rating - i;
            mStarViews.get(i).setPercent(percent);
        }

        for (int i = roundedRating; i < mStarViews.size(); i++) {
            mStarViews.get(i).setVisibility(INVISIBLE);
            mStarViews.get(i).setPercent(0);
        }
    }
}
