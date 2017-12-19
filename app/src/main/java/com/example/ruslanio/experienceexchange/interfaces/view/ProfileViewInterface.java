package com.example.ruslanio.experienceexchange.interfaces.view;

import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.database.model.User;

import java.util.List;

/**
 * Created by Ruslanio on 18.11.2017.
 */

public interface ProfileViewInterface extends BaseViewInterface {
    void setData(User user);

    void setInterests(List<Interest> interests);
}
