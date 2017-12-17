package com.example.ruslanio.experienceexchange.views;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.model.Lesson;
import com.example.ruslanio.experienceexchange.interfaces.presenter.CourseCreatingOverviewPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.CourseCreatingOverviewViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseFragment;
import com.example.ruslanio.experienceexchange.presenters.CourseCreatingOverviewPresenter;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public class CourseCreatingOverviewView extends BaseFragment<CourseCreatingOverviewPresenterInterface>
        implements CourseCreatingOverviewViewInterface {

    private List<Lesson> mLessonList = Collections.emptyList();

    @BindView(R.id.rv_course_lessons)
    RecyclerView mLessons;

    private void setLessons(List<Lesson> lessons){
        mLessonList = lessons;
    }

    @Override
    protected CourseCreatingOverviewPresenterInterface getPresenter() {
        return new CourseCreatingOverviewPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_course_creating_overview;
    }

    public static CourseCreatingOverviewView getInstance(List<Lesson> lessons) {
        CourseCreatingOverviewView overviewView = new CourseCreatingOverviewView();
        overviewView.setLessons(lessons);
        return overviewView;
    }
}
