package com.moviefiend.torian.moviefiend;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.util.AttributeSet;
import android.view.View;

public class StarView extends View {

    private final Paint mLinePaint;
    private final Paint mFillPaint;
    private final Path mStarLinePath;
    private final Path mStarFillPath;

    private @FloatRange(from=0.0, to=1.0) float mPercent;

    public StarView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mLinePaint = new Paint();
        mLinePaint.setStrokeWidth(10);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(Color.BLACK);

        mFillPaint = new Paint();
        mFillPaint.setStyle(Paint.Style.FILL);
        mFillPaint.setColor(Color.YELLOW);

        mStarLinePath = new Path();
        mStarFillPath = new Path();

        mPercent = 0f;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        createStarPath(mStarLinePath);
        createStarPath(mStarFillPath);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.clipPath(mStarLinePath, Region.Op.DIFFERENCE);
        canvas.drawPath(mStarLinePath, mLinePaint);

        canvas.clipPath(mStarFillPath, Region.Op.UNION);
        canvas.clipRect(getWidth() * mPercent, 0f, getWidth(), getHeight(), Region.Op.DIFFERENCE);
        canvas.drawPath(mStarFillPath, mFillPaint);
    }

    public void setLineColor(@ColorInt int lineColor) {
        mLinePaint.setColor(lineColor);
        invalidate();
        requestLayout();
    }

    public void setFillColor(@ColorInt int fillColor) {
        mFillPaint.setColor(fillColor);
        invalidate();
        requestLayout();
    }

    public void setPercent(@FloatRange(from=0.0, to=1.0) float percent) {
        mPercent = percent;
        invalidate();
        requestLayout();
    }

    private void createStarPath(Path path) {
        float mid = getWidth() / 2;
        float min = Math.min(getWidth(), getHeight());
        float half = min / 2;
        mid = mid - half;

        path.moveTo(mid + half * 0.5f, half * 0.84f);
        path.lineTo(mid + half * 1.5f, half * 0.84f);
        path.lineTo(mid + half * 0.68f, half * 1.45f);
        path.lineTo(mid + half * 1.0f, half * 0.5f);
        path.lineTo(mid + half * 1.32f, half * 1.45f);
        path.lineTo(mid + half * 0.5f, half * 0.84f);

        path.close();
    }
}
