package com.example.ruslanio.experienceexchange.interfaces.view;

import android.os.Bundle;

import com.example.ruslanio.experienceexchange.database.model.Interest;

import java.util.List;

/**
 * Created by Ruslanio on 13.11.2017.
 */

public interface ChoiceViewInterface extends BaseViewInterface {
    void nextView(List<Integer> ids);

    void showBubblePicker(List<Interest> interests);
}
