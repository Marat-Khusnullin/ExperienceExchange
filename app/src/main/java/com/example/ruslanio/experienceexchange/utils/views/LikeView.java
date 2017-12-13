package com.example.ruslanio.experienceexchange.utils.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.utils.Util;

/**
 * Created by Ruslanio on 12.12.2017.
 */

public class LikeView extends View {

    private int mLikeNumber;
    private int mColor;
    private boolean mIsLiked;

    private Paint mPaint;
    private Bitmap mHeartBitmap;

    private OnViewLikedListener mOnViewLikedListener;

    public void setOnViewLikedListener(OnViewLikedListener onViewLikedListener) {
        mOnViewLikedListener = onViewLikedListener;
    }

    public int getLikeNumber() {
        return mLikeNumber;
    }

    public boolean isLiked() {
        return mIsLiked;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public void setColorId(@ColorRes int id) {
        mColor = getContext().getResources().getColor(id);
    }

    public void setLiked(boolean isLiked) {
        mIsLiked = isLiked;
    }

    public void setLikeNumber(int number) {
        mLikeNumber = number;
    }

    public LikeView(Context context) {
        super(context);
        initAttrs(null);
    }

    public LikeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public LikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs == null)
            return;

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LikeView);
        try {
            mLikeNumber = typedArray.getInt(R.styleable.LikeView_like_number, 0);
            mColor = typedArray.getColor(R.styleable.LikeView_base_color, 20);
            mIsLiked = typedArray.getBoolean(R.styleable.LikeView_is_liked, false);
            if (mLikeNumber < 0)
                throw new IllegalArgumentException("likes number must be 0 or larger!");
        } finally {
            typedArray.recycle();
        }

        mPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(mColor);

        if (mIsLiked)
            mHeartBitmap = Util.drawableToBitmap(getResources().getDrawable(R.drawable.ic_like_fill));
        else
            mHeartBitmap = Util.drawableToBitmap(getResources().getDrawable(R.drawable.ic_like_empty));

        String text = String.valueOf(mLikeNumber);

        float width = mPaint.measureText(text);
        mPaint.setTextSize(40);
        canvas.drawText(text,30,30,mPaint);
        canvas.translate(width,0);
        canvas.drawBitmap(mHeartBitmap,30,30,mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mIsLiked)
            mIsLiked = false;
        else
            mIsLiked = true;
        if (mOnViewLikedListener != null) {
            mOnViewLikedListener.onLike(mLikeNumber);
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    public interface OnViewLikedListener{
        void onLike(int likesNumber);
    }
}
