package com.example.ruslanio.experienceexchange.presenters;

import com.example.ruslanio.experienceexchange.interfaces.presenter.ChoicePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.ChoiceViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.igalata.bubblepicker.model.PickerItem;

/**
 * Created by Ruslanio on 13.11.2017.
 */

public class ChoicePresenter extends BasePresenter<ChoiceViewInterface> implements ChoicePresenterInterface {

    public ChoicePresenter(ChoiceViewInterface view) {
        super(view);
    }

    @Override
    public void onInterestSelected(PickerItem pickerItem) {

    }

    @Override
    public void onInterestDeselected(PickerItem pickerItem) {

    }
}
