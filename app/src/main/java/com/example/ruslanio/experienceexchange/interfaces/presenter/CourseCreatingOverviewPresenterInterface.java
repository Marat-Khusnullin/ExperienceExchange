package com.example.ruslanio.experienceexchange.interfaces.presenter;

import com.example.ruslanio.experienceexchange.database.model.temporary.TempLesson;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public interface CourseCreatingOverviewPresenterInterface extends BasePresenterInterface {
    void onAddLesson(Integer lessonCount);

    void onTempLessonClosed(TempLesson tempLesson);

    void buildCourse(String name , String description, String image, String chosenInterest);
}
