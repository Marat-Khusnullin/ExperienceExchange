package com.example.ruslanio.experienceexchange.interfaces.presenter;

import com.igalata.bubblepicker.model.PickerItem;

/**
 * Created by Ruslanio on 15.12.2017.
 */

public interface BubblePickerHolderPresenterInterface extends BasePresenterInterface {
    void onInterestDeselected(PickerItem pickerItem);

    void onInterestSelected(PickerItem pickerItem);
}
