package com.example.ruslanio.experienceexchange.interfaces.view;

import com.example.ruslanio.experienceexchange.database.model.Course;

import java.util.List;

/**
 * Created by Ruslanio on 29.11.2017.
 */

public interface HomeViewInterface extends BaseViewInterface {

    void setAuthors(List<String> authors);
    void setThemes(List<String> themes);
    void setCourses(List<Course> courses);
}
