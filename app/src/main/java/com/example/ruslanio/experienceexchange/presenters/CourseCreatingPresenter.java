package com.example.ruslanio.experienceexchange.presenters;

import android.os.Bundle;

import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.database.model.Lesson;
import com.example.ruslanio.experienceexchange.database.model.temporary.TempLesson;
import com.example.ruslanio.experienceexchange.interfaces.presenter.CourseCreatingPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.CourseCreatingViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.utils.rxbus.BusEvents;
import com.example.ruslanio.experienceexchange.utils.rxbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public class CourseCreatingPresenter extends BasePresenter<CourseCreatingViewInterface> implements CourseCreatingPresenterInterface {
    private DataBaseManager mDataBaseManager;

    public CourseCreatingPresenter(CourseCreatingViewInterface view) {
        super(view);
    }

    @Override
    public void onInit(Bundle saveInstanceState) {
        super.onInit(saveInstanceState);

        mDataBaseManager = DataBaseManager.getInstance(mView.getContext());
        mView.showOverView(new ArrayList<>());

    }

    @Subscriber(tag = BusEvents.TAG_LESSON_CREATED)
    public void onLessonCreated(){
        List<TempLesson> tempLessons = mDataBaseManager.getTemporaryLessons();
        mView.showOverView(tempLessons);
    }

    @Subscriber(tag = BusEvents.TAG_CREATE_LESSON)
    public void onCreateLesson(Integer lessonCount){
        mView.showLesson(lessonCount);
    }
}
