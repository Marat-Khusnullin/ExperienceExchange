package com.example.ruslanio.experienceexchange.views;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.adapters.LessonAdapter;
import com.example.ruslanio.experienceexchange.database.model.Lesson;
import com.example.ruslanio.experienceexchange.database.model.temporary.TempLesson;
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

    private static final String KEY_COURSE_NAME = "key_course_name";
    private static final String KEY_COURSE_DESCRIPTION = "key_course_description";

    @BindView(R.id.btn_course_add_lesson)
    Button mAddLesson;

    @BindView(R.id.btn_course_add_image)
    Button mAddImage;

    @BindView(R.id.et_course_name)
    TextInputEditText mName;

    @BindView(R.id.et_course_description)
    TextInputEditText mDescription;

    @BindView(R.id.iv_course_cover)
    ImageView mCourseCover;

    private LessonAdapter mAdapter;

    @BindView(R.id.rv_course_lessons)
    RecyclerView mLessons;


    public CourseCreatingOverviewView() {
        mAdapter = new LessonAdapter();
    }

    private void setLessons(List<TempLesson> lessons){
        mAdapter.setLessons(lessons);
    }

    @Override
    protected void onInit() {
        super.onInit();

        String name = getArguments().getString(KEY_COURSE_NAME);
        String description = getArguments().getString(KEY_COURSE_DESCRIPTION);

        if (name != null && !name.equals("")){
            mName.setText(name);
        }
        if (description != null && !description.equals("")){
            mDescription.setText(description);
        }

        mLessons.setLayoutManager(new LinearLayoutManager(getContext()));
        mLessons.setAdapter(mAdapter);

        mAddLesson.setOnClickListener(btn -> {
            int lessonCount = mAdapter.getItemCount();
            mPresenter.onAddLesson(lessonCount);
        });
    }

    @Override
    protected CourseCreatingOverviewPresenterInterface getPresenter() {
        return new CourseCreatingOverviewPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_course_creating_overview;
    }

    public static CourseCreatingOverviewView getInstance(List<TempLesson> lessons) {
        CourseCreatingOverviewView overviewView = new CourseCreatingOverviewView();
        overviewView.setArguments(new Bundle());
        overviewView.setLessons(lessons);
        return overviewView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getArguments().putString(KEY_COURSE_NAME, mName.getText().toString());
        getArguments().putString(KEY_COURSE_DESCRIPTION, mDescription.getText().toString());
    }
}
