package com.example.ruslanio.experienceexchange.views;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.adapters.CoursesAdapter;
import com.example.ruslanio.experienceexchange.database.model.Course;
import com.example.ruslanio.experienceexchange.interfaces.presenter.MyCoursesPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.MyCoursesViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseFragment;
import com.example.ruslanio.experienceexchange.presenters.MyCoursesPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Ruslanio on 14.12.2017.
 */

public class MyCoursesView extends BaseFragment<MyCoursesPresenterInterface> implements MyCoursesViewInterface{


    @BindView(R.id.btn_my_courses_create)
    Button mAddCourse;

    @BindView(R.id.my_create_course)
    Button mCreateCourse;

    @BindView(R.id.rv_my_courses)
    RecyclerView mMyCourses;

    @BindView(R.id.holder_has_courses)
    View mCoursesHolder;

    @BindView(R.id.holder_no_courses)
    View mNoCoursesHolder;

    private CoursesAdapter mAdapter;

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
        mAdapter = new CoursesAdapter();

        mMyCourses.setLayoutManager(new LinearLayoutManager(getContext()));
        mMyCourses.setAdapter(mAdapter);

        mAddCourse.setOnClickListener(btn -> {
            Intent intent = new Intent(getContext(),CourseCreatingView.class);
            startActivity(intent);
        });

        mCreateCourse.setOnClickListener(btn -> {
            Intent intent = new Intent(getContext(),CourseCreatingView.class);
            startActivity(intent);
        });
    }

    @Override
    public void showMyCoursesHolder(List<Course> courses){
        mAdapter.setCourses(courses);
        mNoCoursesHolder.setVisibility(View.GONE);
        mMyCourses.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoCoursesHolder(){
        mNoCoursesHolder.setVisibility(View.VISIBLE);
        mCoursesHolder.setVisibility(View.GONE);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_my_courses;
    }
}
