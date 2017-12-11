package com.example.ruslanio.experienceexchange.utils.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Ruslanio on 11.12.2017.
 */

public class TypefaceTextView extends android.support.v7.widget.AppCompatTextView {

    private static final String TYPEFACE_MAIN = "open_sans.ttf";

    public TypefaceTextView(Context context) {
        super(context);
        setTypeface();
    }

    public TypefaceTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setTypeface();
    }

    public TypefaceTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface();
    }

    private void setTypeface(){
        if (!isInEditMode()){
            Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), TYPEFACE_MAIN);
            setTypeface(typeface);
        }

    }
}
