package com.example.ruslanio.experienceexchange.presenters;

import com.example.ruslanio.experienceexchange.interfaces.presenter.CourseInProgressPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.CourseInProgressViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;

/**
 * Created by Ruslanio on 12.12.2017.
 */

public class CourseInProgressPresenter extends BasePresenter<CourseInProgressViewInterface>
        implements CourseInProgressPresenterInterface {

    public CourseInProgressPresenter(CourseInProgressViewInterface view) {
        super(view);
    }
}
