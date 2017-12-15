package com.example.ruslanio.experienceexchange.adapters;

import android.content.Context;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.igalata.bubblepicker.adapter.BubblePickerAdapter;
import com.igalata.bubblepicker.model.PickerItem;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ruslanio on 29.11.2017.
 */

public class BubbleInterestAdapter implements BubblePickerAdapter {
    private List<Interest> mInterests = Collections.emptyList();
    private Context mContext;

    public BubbleInterestAdapter(List<Interest> interests, Context context) {
        mInterests = interests;
        mContext = context;
    }

    @Override
    public int getTotalCount() {
        return mInterests.size();
    }

    @NotNull
    @Override
    public PickerItem getItem(int i) {
        PickerItem item = new PickerItem();
        item.setTextColor(mContext.getResources().getColor(R.color.white_text));
        item.setColor(mContext.getResources().getColor(R.color.accent));
        item.setTitle(mInterests.get(i).getInterestName());

        return item;
    }

}
