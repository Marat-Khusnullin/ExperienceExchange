package com.example.ruslanio.experienceexchange.interfaces.view;

import com.example.ruslanio.experienceexchange.database.model.Interest;

import java.util.List;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public interface CourseCreatingOverviewViewInterface extends BaseViewInterface {
    void setInterests(List<Interest> interests);
}
