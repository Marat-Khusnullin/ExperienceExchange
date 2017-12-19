package com.example.ruslanio.experienceexchange.presenters;

import android.os.Bundle;

import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.database.model.Course;
import com.example.ruslanio.experienceexchange.interfaces.presenter.MyCoursesPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.MyCoursesViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;

import java.util.List;

/**
 * Created by Ruslanio on 14.12.2017.
 */

public class MyCoursesPresenter extends BasePresenter<MyCoursesViewInterface> implements MyCoursesPresenterInterface {
    public MyCoursesPresenter(MyCoursesViewInterface view) {
        super(view);
    }

    private DataBaseManager mDataBaseManager;

    @Override
    public void onInit(Bundle saveInstanceState) {
        super.onInit(saveInstanceState);
        mDataBaseManager = DataBaseManager.getInstance(mView.getContext());

        List<Course> courses = mDataBaseManager.getAllMyCourses();
        if (courses != null && courses.size() != 0){
            mView.showMyCoursesHolder(courses);
        } else {
            mView.showNoCoursesHolder();
        }

    }
}
