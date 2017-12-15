package com.example.ruslanio.experienceexchange.views;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.MyCoursesPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.MyCoursesViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseFragment;
import com.example.ruslanio.experienceexchange.presenters.MyCoursesPresenter;

/**
 * Created by Ruslanio on 14.12.2017.
 */

public class MyCoursesView extends BaseFragment<MyCoursesPresenterInterface> implements MyCoursesViewInterface{
    @Override
    protected MyCoursesPresenterInterface getPresenter() {
        return new MyCoursesPresenter(this);
    }

    public static MyCoursesView getInstance(){
        return new MyCoursesView();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_my_courses;
    }
}
