package com.example.ruslanio.experienceexchange.presenters;

import android.os.Bundle;

import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.interfaces.presenter.BubblePickerHolderPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.BubblePickerHolderViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.interfaces.presenter.BasePresenterInterface;
import com.igalata.bubblepicker.model.PickerItem;

/**
 * Created by Ruslanio on 15.12.2017.
 */

public class BubblePickerHolderPresenter extends BasePresenter<BubblePickerHolderViewInterface>
        implements BubblePickerHolderPresenterInterface {
    private DataBaseManager mDataBaseManager;

    public BubblePickerHolderPresenter(BubblePickerHolderViewInterface view) {
        super(view);
    }

    @Override
    public void onInit(Bundle saveInstanceState) {
        super.onInit(saveInstanceState);
        mDataBaseManager = DataBaseManager.getInstance(mView.getContext());
    }

    @Override
    public void onInterestDeselected(PickerItem pickerItem) {
        updateInterest(false, pickerItem.getTitle());
    }

    @Override
    public void onInterestSelected(PickerItem pickerItem) {
        updateInterest(true,pickerItem.getTitle());
    }

    private void updateInterest(boolean forSafe, String interestName){
        Interest interest = mDataBaseManager.getInterestByName(interestName);
        if (forSafe){
            interest.setClient(true);
        } else {
            interest.setClient(false);
        }
        mDataBaseManager.updateInterest(interest);
    }
}
