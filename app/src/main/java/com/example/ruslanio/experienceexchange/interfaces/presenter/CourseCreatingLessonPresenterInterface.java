package com.example.ruslanio.experienceexchange.interfaces.presenter;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public interface CourseCreatingLessonPresenterInterface extends BasePresenterInterface {
    void onTextBlock(String text);

    void onImageBlock(String uri);

    void buildLesson(String lessonName);
}
