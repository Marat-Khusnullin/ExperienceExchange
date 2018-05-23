package com.example.ruslanio.experienceexchange.interfaces.view;

import com.example.ruslanio.experienceexchange.database.model.Course;
import com.example.ruslanio.experienceexchange.database.model.LessonTrue;

import java.util.List;

/**
 * Created by Марат on 12.04.2018.
 */

public interface CourseViewActivityInterface extends BaseViewInterface {
    int getCourseId();
    void setData(Course course);
    void setLessons(List<LessonTrue> lessons);
}
