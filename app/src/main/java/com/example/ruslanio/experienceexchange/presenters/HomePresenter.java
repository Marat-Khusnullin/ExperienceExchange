package com.example.ruslanio.experienceexchange.presenters;

import android.os.Bundle;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.database.model.Course;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.interfaces.presenter.HomePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.HomeViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.network.ApiManager;
import com.example.ruslanio.experienceexchange.network.pojo.course.news.Result;
import com.example.ruslanio.experienceexchange.utils.Util;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Ruslanio on 29.11.2017.
 */

public class HomePresenter extends BasePresenter<HomeViewInterface> implements HomePresenterInterface {

    private DataBaseManager mDataBaseManager;
    private ApiManager mApiManager;
    private static final double MIN_POPULAR_PERCENTAGE = 0.0;

    public HomePresenter(HomeViewInterface view) {
        super(view);
    }

    @Override
    public void onInit(Bundle saveInstanceState) {
        super.onInit(saveInstanceState);

        mDataBaseManager = DataBaseManager.getInstance(mView.getContext());
        mApiManager = ApiManager.getInstance();

        mView.setThemes(getPopularInterests());


        List<String> authors = new ArrayList<>();
        authors.add("John Kennedy");
        authors.add("Victor Reznov");
        authors.add("Guy Richy");

        mView.setAuthors(authors);

        String token = mDataBaseManager.getCurrentToken();
        int id = mDataBaseManager.getCurrentUserId();

        mApiManager.getNews(token,id)
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
                            course.setAuthor("Author");
                            courses.add(course);
                        }
                        mView.setCourses(courses);
                    } else {
                        mView.showSnackbar(R.string.connection_error);
                    }
                },throwable -> {
                    mView.showSnackbar(R.string.server_error);
                    throwable.printStackTrace();
                });

    }



    private List<Interest> getPopularInterests() {
        List<Interest> interests = mDataBaseManager.getAllInterests();
        for (Interest interest : interests) {
            if (interest.getPercentage() < MIN_POPULAR_PERCENTAGE)
                interests.remove(interest);
        }
        return interests;
    }
}
