package com.example.ruslanio.experienceexchange.interfaces.presenter;

import com.igalata.bubblepicker.model.PickerItem;

/**
 * Created by Ruslanio on 13.11.2017.
 */

public interface ChoicePresenterInterface extends BasePresenterInterface {

    boolean onReady();
    void checkForInterests();
}
