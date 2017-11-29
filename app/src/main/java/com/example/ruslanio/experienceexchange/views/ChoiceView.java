package com.example.ruslanio.experienceexchange.views;

import android.widget.Button;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.adapters.BubbleInterestAdapter;
import com.example.ruslanio.experienceexchange.interfaces.presenter.ChoicePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.ChoiceViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseActivity;
import com.example.ruslanio.experienceexchange.presenters.ChoicePresenter;
import com.igalata.bubblepicker.BubblePickerListener;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Ruslanio on 29.11.2017.
 */

public class ChoiceView extends BaseActivity<ChoicePresenterInterface> implements ChoiceViewInterface{

    @BindView(R.id.interest_bubble_picker)
    BubblePicker mBubblePicker;

    @BindView(R.id.btn_ready)
    Button mReady;

    private BubbleInterestAdapter mAdapter;

    @Override
    protected ChoicePresenterInterface getPresenter() {
        return new ChoicePresenter(this);
    }

    @Override
    protected void onInit() {
        List<String> strings = new ArrayList<>();
        strings.add("string");
        strings.add("string");
        strings.add("string");
        strings.add("string");
        strings.add("string");
        strings.add("string");

        mAdapter = new BubbleInterestAdapter(strings,getContext());
        mBubblePicker.setAdapter(mAdapter);
        mBubblePicker.setCenterImmediately(true);
        mBubblePicker.setListener(new BubblePickerListener() {
            @Override
            public void onBubbleSelected(PickerItem pickerItem) {
                mPresenter.onInterestSelected(pickerItem);
            }

            @Override
            public void onBubbleDeselected(PickerItem pickerItem) {
                mPresenter.onInterestDeselected(pickerItem);
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        mBubblePicker.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBubblePicker.onResume();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_choice;
    }
}
