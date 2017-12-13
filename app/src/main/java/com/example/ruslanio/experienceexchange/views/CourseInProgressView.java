package com.example.ruslanio.experienceexchange.views;

import android.support.v4.app.Fragment;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.CourseInProgressPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.CourseInProgressViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseFragment;
import com.example.ruslanio.experienceexchange.presenters.CourseInProgressPresenter;

/**
 * Created by Ruslanio on 12.12.2017.
 */

public class CourseInProgressView extends BaseFragment<CourseInProgressPresenterInterface>
        implements CourseInProgressViewInterface {

    @Override
    protected CourseInProgressPresenterInterface getPresenter() {
        return new CourseInProgressPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_courses_in_progress;
    }

    public static CourseInProgressView getInstance() {
        return new CourseInProgressView();
    }
}
