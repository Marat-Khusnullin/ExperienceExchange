package com.example.ruslanio.experienceexchange.presenters;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.database.model.Course;
import com.example.ruslanio.experienceexchange.database.model.Lesson;
import com.example.ruslanio.experienceexchange.database.model.LessonBlock;
import com.example.ruslanio.experienceexchange.database.model.LessonTrue;
import com.example.ruslanio.experienceexchange.interfaces.presenter.CourseViewActivityPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.CourseViewActivityInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.network.ApiManager;
import com.example.ruslanio.experienceexchange.network.pojo.course.news.CourseResult;
import com.example.ruslanio.experienceexchange.network.pojo.course.news.Result;
import com.example.ruslanio.experienceexchange.network.pojo.lesson.Block;
import com.example.ruslanio.experienceexchange.network.pojo.lesson.ResultByCourse;
import com.example.ruslanio.experienceexchange.network.pojo.lesson.ResultLesson;
import com.example.ruslanio.experienceexchange.utils.Util;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Марат on 12.04.2018.
 */

public class CourseViewActivityPresenter extends BasePresenter<CourseViewActivityInterface> implements CourseViewActivityPresenterInterface {

    private ApiManager mApiManager;
    private DataBaseManager mDataBaseManager;
    int count =0;
    List<ResultByCourse> results;
    ArrayList list;


    public CourseViewActivityPresenter(CourseViewActivityInterface view) {
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
        mApiManager.getCourse(mDataBaseManager.getCurrentToken(), mDataBaseManager.getCurrentUserId(), mView.getCourseId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(courseResponce -> {
                    if (Util.checkCode(courseResponce.getStatus())){
                        CourseResult result = courseResponce.getResult();
                            Course course = new Course();
                            course.setId(result.getId());
                            course.setCourseName(result.getTitle());
                            course.setDescription(result.getSummary());
                            course.setCoverImage(result.getCover());
                            course.setAvailableLessons(result.getLessonsNumber());
                            course.setLikesNumber(result.getLikersNumber());
                            course.setAuthor(result.getAuthor());
                            mView.setData(course);
                            setLessons(course.getId());
                    } else {
                        mView.showSnackbar(R.string.connection_error);
                    }
                },throwable -> {
                    mView.showSnackbar(R.string.server_error);
                    throwable.printStackTrace();
                });
    }

    private void setLessons(int id) {
        mApiManager.getLessonsByCourse(mDataBaseManager.getCurrentToken(), mDataBaseManager.getCurrentUserId(),id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lessonsByCourseResponse -> {
                    if (Util.checkCode(lessonsByCourseResponse.getStatus())){
                        results = lessonsByCourseResponse.getResult();
                        list = new ArrayList();
                        for (ResultByCourse result: results){
                            mApiManager.getLesson(mDataBaseManager.getCurrentToken(), mDataBaseManager.getCurrentUserId(), mView.getCourseId(), result.getId())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(lessonAddedResponce -> {
                                        if (Util.checkCode(lessonAddedResponce.getStatus())){
                                            LessonTrue lesson = new LessonTrue();
                                            ResultLesson result1 = lessonAddedResponce.getResult();
                                            List<Block> blocks = new ArrayList<>();
                                            blocks = result1.getBlocks();
                                            lesson.setBlocks(blocks);
                                            lesson.setId(result1.getId());
                                            lesson.setCourseId(mView.getCourseId());
                                            lesson.setName(result1.getName());
                                            lesson.setNumber(result1.getNumber());
                                            list.add(lesson);
                                            if(list.size() == results.size()){
                                                mView.setLessons(list);
                                            }
                                        } else {
                                            mView.showSnackbar(R.string.connection_error);
                                        }
                                    },throwable -> {
                                        mView.showSnackbar("Ошибка получения полного урока!");
                                        throwable.printStackTrace();
                                    });
                        }


                    } else {
                        mView.showSnackbar(R.string.connection_error);
                    }
                },throwable -> {
                    mView.showSnackbar(R.string.server_error);
                    throwable.printStackTrace();
                });


    }

    private void setFull(int count) {

    }


}
