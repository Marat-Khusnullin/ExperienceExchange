package com.example.ruslanio.experienceexchange.presenters;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.database.model.Course;
import com.example.ruslanio.experienceexchange.database.model.Lesson;
import com.example.ruslanio.experienceexchange.database.model.LessonBlock;
import com.example.ruslanio.experienceexchange.database.model.temporary.TempLesson;
import com.example.ruslanio.experienceexchange.interfaces.presenter.CourseCreatingOverviewPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.CourseCreatingOverviewViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.network.ApiManager;
import com.example.ruslanio.experienceexchange.network.body.CourseBody;
import com.example.ruslanio.experienceexchange.network.body.lesson.Block;
import com.example.ruslanio.experienceexchange.network.body.lesson.LessonBody;
import com.example.ruslanio.experienceexchange.network.pojo.course.added.Result;
import com.example.ruslanio.experienceexchange.network.pojo.lesson.LessonAddedResponce;
import com.example.ruslanio.experienceexchange.utils.Util;
import com.example.ruslanio.experienceexchange.utils.rxbus.BusEvents;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public class CourseCreatingOverviewPresenter extends BasePresenter<CourseCreatingOverviewViewInterface>
        implements CourseCreatingOverviewPresenterInterface {

    private DataBaseManager mDataBaseManager;
    private ApiManager mApiManager;

    public CourseCreatingOverviewPresenter(CourseCreatingOverviewViewInterface view) {
        super(view);
    }

    @Override
    public void onInit(Bundle saveInstanceState) {
        super.onInit(saveInstanceState);
        mDataBaseManager = DataBaseManager.getInstance(mView.getContext());
        mApiManager = ApiManager.getInstance();

        mView.setInterests(mDataBaseManager.getAllInterests());
    }

    @Override
    public void onAddLesson(Integer lessonCount) {
        publish(BusEvents.TAG_CREATE_LESSON, lessonCount);
    }

    @Override
    public void onTempLessonClosed(TempLesson tempLesson) {
        mDataBaseManager.deleteTempLesson(tempLesson);
    }

    @Override
    public void buildCourse(String name, String description, String image , String interest) {
        Course course = new Course();
        course.setCourseName(name);
        course.setDescription(description);
        course.setMy(true);
        course.setLikesNumber(0);

        int interestId = mDataBaseManager.getInterestByName(interest).getId();
        course.setInterestId(interestId);

        mApiManager.uploadImage(new File(Util.getRealPathFromURI(Uri.parse(image), mView.getContext())))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(imageResponce -> {
                    if (Util.checkCode(imageResponce.getStatus())){
                        String imageToken = imageResponce.getResult();

                        course.setCoverImage(imageToken);
                        List<TempLesson> tempLessons = mDataBaseManager.getTemporaryLessons();
                        List<Lesson> lessons = mDataBaseManager.TempLessonsToRawLessons(tempLessons);
                        course.setLessons(lessons);

                        mDataBaseManager.deleteAllTemporaryLessons();
                        uploadCourse(course);
                    } else {
                        mView.showSnackbar(R.string.connection_error);
                    }
                }, throwable -> {
                    mView.showSnackbar(R.string.server_error);
                    throwable.printStackTrace();
                });


}

    private void uploadCourse(Course course) {
        CourseBody body = new CourseBody();
        body.setTitle(course.getCourseName());
        body.setSummary(course.getDescription());
        body.setCover(course.getCoverImage());
        body.setInterest_id(course.getInterestId());


        String token = mDataBaseManager.getCurrentToken();
        int id = mDataBaseManager.getCurrentUserId();

        mApiManager.uploadCourse(token,id,body)
                .subscribe(courseAddedResponce -> {
                    if (Util.checkCode(courseAddedResponce.getStatus())){
                        Result result = courseAddedResponce.getResult();
                        course.setId(result.getId());
                        mDataBaseManager.insertCourse(course);
                        loadLessons(course.getLessons(),course.getId());
                    } else {
                        mView.showSnackbar(R.string.connection_error);
                    }
                }, throwable -> {
                    mView.showSnackbar(R.string.server_error);
                    throwable.printStackTrace();
                });

    }

    private int mCourseId;
    private void loadLessons(List<Lesson> lessons, int courseId) {
        mCourseId= courseId;
        new TempAsyncTask().execute(lessons.toArray(new Lesson[lessons.size()]));
    }


    private void onLessonsSend() {
        publish(BusEvents.TAG_COURSE_CREATED);
    }


    private class TempAsyncTask extends AsyncTask<Lesson, Integer, Void> {

        private int userId;
        private int courseId;
        private String token;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            userId = mDataBaseManager.getCurrentUserId();
            token = mDataBaseManager.getCurrentToken();
            courseId = mCourseId;

        }

        @Override
        protected Void doInBackground(Lesson... lessons) {

            for (Lesson lesson: lessons) {
                LessonBody body = new LessonBody();
                body.setName(lesson.getName());
                List<Block> blocks = new ArrayList<>();
                for (LessonBlock lessonBlock:lesson.getBlocks()){
                    Block block = new Block();
                    block.setContent(lessonBlock.getValue());
                    switch (lessonBlock.getType()){
                        case BLOCK_TEXT:
                            block.setType("text");
                            break;
                        case BLOCK_IMAGE:
                            block.setType("image");
                            break;
                        case (BLOCK_VIDEO):
                            block.setType("video");
                            break;
                    }
                    block.setNumber(lessonBlock.getOrderNumber());

                    blocks.add(block);
                }
                body.setBlocks(blocks);

                Call<LessonAddedResponce> call = mApiManager.uploadLesson(token, userId, courseId, body);
                try {
                    Response<LessonAddedResponce> response = call.execute();
                    if (Util.checkCode(response.code())){
                        LessonAddedResponce lessonAddedResponce = response.body();
                        com.example.ruslanio.experienceexchange.network.pojo.lesson.Result result =lessonAddedResponce.getResult();
                    } else {
                        System.out.println(response.code() + "");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            onLessonsSend();
        }
    }

}
