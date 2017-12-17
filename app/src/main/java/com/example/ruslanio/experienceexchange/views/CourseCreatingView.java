package com.example.ruslanio.experienceexchange.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.model.Lesson;
import com.example.ruslanio.experienceexchange.interfaces.presenter.CourseCreatingPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.CourseCreatingViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseActivity;
import com.example.ruslanio.experienceexchange.presenters.CourseCreatingPresenter;

import java.util.List;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public class CourseCreatingView extends BaseActivity<CourseCreatingPresenterInterface> implements CourseCreatingViewInterface {
    private static final String TAG_OVERVIEW = "tag_overview";
    private static final String TAG_LESSON = "tag_lesson";
    @Override
    protected CourseCreatingPresenterInterface getPresenter() {
        return new CourseCreatingPresenter(this);
    }

    @Override
    protected void onInit() {

    }


    @Override
    public void showOverView(List<Lesson> lessons){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment overView = fragmentManager.findFragmentByTag(TAG_LESSON);

        if (overView == null)
            overView = CourseCreatingOverviewView.getInstance(lessons);
        fragmentManager
                .beginTransaction()
                .replace(R.id.container_course_creating,overView, TAG_OVERVIEW)
                .commit();
    }

    @Override
    public void showLesson(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment lesson = fragmentManager.findFragmentByTag(TAG_LESSON);

        if (lesson == null)
            lesson = CourseCreatingLessonView.getInstance();
        fragmentManager
                .beginTransaction()
                .replace(R.id.container_course_creating,lesson, TAG_LESSON)
                .commit();
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_course_creating;
    }
}
