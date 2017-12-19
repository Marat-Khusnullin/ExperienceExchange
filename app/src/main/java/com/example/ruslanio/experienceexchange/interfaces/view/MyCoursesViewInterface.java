package com.example.ruslanio.experienceexchange.interfaces.view;

import com.example.ruslanio.experienceexchange.database.model.Course;

import java.util.List;

/**
 * Created by Ruslanio on 14.12.2017.
 */

public interface MyCoursesViewInterface extends BaseViewInterface {
    void showMyCoursesHolder(List<Course> courses);

    void showNoCoursesHolder();
}
