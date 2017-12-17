package com.example.ruslanio.experienceexchange.views;

import android.content.Intent;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.MyCoursesPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.MyCoursesViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseFragment;
import com.example.ruslanio.experienceexchange.presenters.MyCoursesPresenter;

import butterknife.BindView;

/**
 * Created by Ruslanio on 14.12.2017.
 */

public class MyCoursesView extends BaseFragment<MyCoursesPresenterInterface> implements MyCoursesViewInterface{

    @BindView(R.id.holder_no_courses)
    LinearLayout mNoCoursesHolder;

    @BindView(R.id.my_create_course)
    Button mCreateCourse;
    @Override
    protected MyCoursesPresenterInterface getPresenter() {
        return new MyCoursesPresenter(this);
    }

    public static MyCoursesView getInstance(){
        return new MyCoursesView();
    }

    @Override
    protected void onInit() {
        super.onInit();

        mCreateCourse.setOnClickListener(btn -> {
            Intent intent = new Intent(getContext(),CourseCreatingView.class);
            startActivity(intent);
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_my_courses;
    }
}
