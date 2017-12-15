package com.example.ruslanio.experienceexchange.interfaces.view;

import com.example.ruslanio.experienceexchange.database.model.Course;
import com.example.ruslanio.experienceexchange.database.model.Interest;

import java.util.List;

/**
 * Created by Ruslanio on 29.11.2017.
 */

public interface HomeViewInterface extends BaseViewInterface {

    void setAuthors(List<String> authors);
    void setThemes(List<Interest> themes);
    void setCourses(List<Course> courses);
}
