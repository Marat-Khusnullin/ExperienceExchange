package com.example.ruslanio.experienceexchange.utils.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.ruslanio.experienceexchange.R;

/**
 * Created by Ruslanio on 27.11.2017.
 */

public class EntitledTextView extends TypefaceTextView {
    private String mTitle;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
        applyTitle();
    }

    public EntitledTextView(Context context) {
        super(context);
        initAttrs(null);
    }

    public EntitledTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public EntitledTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs == null)
            return;

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.EntitledTextView);
        try {
            String title = typedArray.getString(R.styleable.EntitledTextView_title_text);
            mTitle = title;
        } finally {
            typedArray.recycle();
        }
        applyTitle();
    }

    private void applyTitle() {
        if (mTitle.equals("") || mTitle.equals(" ") || mTitle == null)
            return;
        StringBuilder builder = new StringBuilder();
        builder.append(mTitle)
                .append(": ")
                .append(getText().toString());
        setText(builder.toString());
        invalidate();
    }
}
