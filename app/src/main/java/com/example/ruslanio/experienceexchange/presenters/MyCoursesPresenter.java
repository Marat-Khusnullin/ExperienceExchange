package com.example.ruslanio.experienceexchange.presenters;

import com.example.ruslanio.experienceexchange.interfaces.presenter.MyCoursesPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.MyCoursesViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;

/**
 * Created by Ruslanio on 14.12.2017.
 */

public class MyCoursesPresenter extends BasePresenter<MyCoursesViewInterface> implements MyCoursesPresenterInterface {
    public MyCoursesPresenter(MyCoursesViewInterface view) {
        super(view);
    }
}
