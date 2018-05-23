package com.example.ruslanio.experienceexchange.presenters;

import android.os.Bundle;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.database.model.Course;
import com.example.ruslanio.experienceexchange.interfaces.presenter.CourseInProgressPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.CourseInProgressViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.network.ApiManager;
import com.example.ruslanio.experienceexchange.network.pojo.course.news.Result;
import com.example.ruslanio.experienceexchange.utils.Util;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Ruslanio on 12.12.2017.
 */

public class CourseInProgressPresenter extends BasePresenter<CourseInProgressViewInterface>
        implements CourseInProgressPresenterInterface {

    private ApiManager mApiManager;
    private DataBaseManager mDataBaseManager;
    public CourseInProgressPresenter(CourseInProgressViewInterface view) {
        super(view);
    }

    @Override
    public void onInit(Bundle saveInstanceState) {
        super.onInit(saveInstanceState);
        mApiManager = ApiManager.getInstance();
        mDataBaseManager = DataBaseManager.getInstance(mView.getContext());
        setData();


    }

    private void setData() {
        mApiManager.getSubscriptions(mDataBaseManager.getCurrentToken(), mDataBaseManager.getCurrentUserId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(coursesNewsResponce -> {
                    if (Util.checkCode(coursesNewsResponce.getStatus())){
                        List<Course> courses = new ArrayList<>();
                        List<Result> results = coursesNewsResponce.getResult();
                        for (Result result: results){
                            Course course = new Course();
                            course.setId(result.getId());
                            course.setCourseName(result.getTitle());
                            course.setDescription(result.getSummary());
                            course.setCoverImage(result.getCover());
                            course.setAvailableLessons(result.getLessonsNumber());
                            course.setLikesNumber(result.getLikersNumber());
                            course.setAuthor(result.getAuthor());
                            courses.add(course);
                        }
                        if(courses.size()!=0) {
                            mView.setCourses(courses);
                        } else {
                            //mView.showNoCoursesHolder();
                        }
                    } else {
                        mView.showSnackbar(R.string.connection_error);
                    }
                },throwable -> {
                    mView.showSnackbar(R.string.server_error);
                    throwable.printStackTrace();
                });
    }
}
