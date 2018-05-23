package com.example.ruslanio.experienceexchange.interfaces.view;

import com.example.ruslanio.experienceexchange.database.model.Course;

import java.util.List;

/**
 * Created by Ruslanio on 12.12.2017.
 */

public interface CourseInProgressViewInterface extends BaseViewInterface {

    void setCourses(List<Course> courses);
}
