package com.example.ruslanio.experienceexchange.views;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.adapters.BubbleInterestAdapter;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.interfaces.presenter.BubblePickerHolderPresenterInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseFragment;
import com.example.ruslanio.experienceexchange.interfaces.view.BubblePickerHolderViewInterface;
import com.example.ruslanio.experienceexchange.presenters.BubblePickerHolderPresenter;
import com.igalata.bubblepicker.BubblePickerListener;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Ruslanio on 15.12.2017.
 */

public class BubblePickerHolderView extends BaseFragment<BubblePickerHolderPresenterInterface>
        implements BubblePickerHolderViewInterface {

    @BindView(R.id.interest_bubble_picker)
    BubblePicker mBubblePicker;

    BubbleInterestAdapter mAdapter;

    private List<Interest> mInterests = Collections.emptyList();

    private void setInterests(List<Interest> interests) {
        mInterests = interests;
    }

    public static BubblePickerHolderView getInstance(List<Interest> interests){
        BubblePickerHolderView holderView = new BubblePickerHolderView();
        holderView.setInterests(interests);
        return holderView;
    }

    @Override
    protected void onInit() {
        super.onInit();
        mAdapter = new BubbleInterestAdapter(mInterests, getContext());
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
    protected BubblePickerHolderPresenterInterface getPresenter() {
        return new BubblePickerHolderPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_bubble_picker_holder;
    }

    @Override
    public void onPause() {
        super.onPause();
        mBubblePicker.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBubblePicker.onResume();
    }
}
