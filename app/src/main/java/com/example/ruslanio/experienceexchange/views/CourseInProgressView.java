package com.example.ruslanio.experienceexchange.views;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.adapters.CoursesAdapter;
import com.example.ruslanio.experienceexchange.database.model.Course;
import com.example.ruslanio.experienceexchange.interfaces.presenter.CourseInProgressPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.CourseInProgressViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseFragment;
import com.example.ruslanio.experienceexchange.presenters.CourseInProgressPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Ruslanio on 12.12.2017.
 */

public class CourseInProgressView extends BaseFragment<CourseInProgressPresenterInterface> implements CourseInProgressViewInterface {

    @BindView(R.id.in_progress_list)
    RecyclerView recyclerView;

    CoursesAdapter mAdapter;


    @Override
    protected CourseInProgressPresenterInterface getPresenter() {
        return new CourseInProgressPresenter(this);
    }

    @Override
    protected void onInit() {
        super.onInit();
        mAdapter = new CoursesAdapter();
        mAdapter.setContext(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_courses_in_progress;
    }

    public void setCourses(List<Course> courses) {
        mAdapter.setCourses(courses);
    }

    public static CourseInProgressView getInstance() {
        return new CourseInProgressView();
    }
}
