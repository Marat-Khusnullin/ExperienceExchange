package com.example.ruslanio.experienceexchange.presenters;

import android.os.Bundle;

import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.interfaces.presenter.CourseCreatingOverviewPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.CourseCreatingOverviewViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.utils.rxbus.BusEvents;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public class CourseCreatingOverviewPresenter extends BasePresenter<CourseCreatingOverviewViewInterface>
        implements CourseCreatingOverviewPresenterInterface {

    private DataBaseManager mDataBaseManager;

    public CourseCreatingOverviewPresenter(CourseCreatingOverviewViewInterface view) {
        super(view);
    }

    @Override
    public void onInit(Bundle saveInstanceState) {
        super.onInit(saveInstanceState);
        mDataBaseManager = DataBaseManager.getInstance(mView.getContext());
    }

    @Override
    public void onAddLesson(Integer lessonCount) {
        publish(BusEvents.TAG_CREATE_LESSON, lessonCount);
    }
}
