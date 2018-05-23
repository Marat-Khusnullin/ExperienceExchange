package com.example.ruslanio.experienceexchange.network;

import com.example.ruslanio.experienceexchange.network.body.CourseBody;
import com.example.ruslanio.experienceexchange.network.body.LoginBody;
import com.example.ruslanio.experienceexchange.network.body.RegistrationBody;
import com.example.ruslanio.experienceexchange.network.body.interest.InterestBody;
import com.example.ruslanio.experienceexchange.network.body.lesson.LessonBody;
import com.example.ruslanio.experienceexchange.network.pojo.course.SubscribeResponse;
import com.example.ruslanio.experienceexchange.network.pojo.course.added.CourseAddedResponce;
import com.example.ruslanio.experienceexchange.network.pojo.course.news.CourseResponse;
import com.example.ruslanio.experienceexchange.network.pojo.course.news.CoursesNewsResponce;
import com.example.ruslanio.experienceexchange.network.pojo.image.ImageResponce;
import com.example.ruslanio.experienceexchange.network.pojo.interest.InterestResponse;
import com.example.ruslanio.experienceexchange.network.pojo.interest.send.InterestSendResponce;
import com.example.ruslanio.experienceexchange.network.pojo.lesson.LessonAddedResponce;
import com.example.ruslanio.experienceexchange.network.pojo.lesson.LessonsByCourseResponse;
import com.example.ruslanio.experienceexchange.network.pojo.login.LoginResponce;
import com.example.ruslanio.experienceexchange.network.pojo.registration.RegistrationResponce;
import com.example.ruslanio.experienceexchange.network.pojo.user.UserProfileResponce;
import com.example.ruslanio.experienceexchange.network.retrofit.GetRequest;
import com.example.ruslanio.experienceexchange.network.retrofit.PostRequest;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public class ApiManager {
    private static ApiManager mApiManager;
    private final static String BASE_URL = "http://ec2-13-59-201-150.us-east-2.compute.amazonaws.com:8080/exex/";

    private final PostRequest mPostRequest;
    private final GetRequest mGetRequest;

    public static ApiManager getInstance() {
        if (mApiManager == null) {
            mApiManager = new ApiManager();
        }
        return mApiManager;
    }

    private ApiManager() {
        mGetRequest = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .baseUrl(BASE_URL)
                .build()
                .create(GetRequest.class);
        mPostRequest = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .baseUrl(BASE_URL)
                .build()
                .create(PostRequest.class);
    }

    public Observable<LoginResponce> login(LoginBody loginBody) {
        return mPostRequest.login(loginBody);
    }

    public Observable<RegistrationResponce> register(RegistrationBody registrationBody) {
        return mPostRequest.register(registrationBody);
    }

    public Observable<InterestResponse> getAllInterests(String token){
        return mGetRequest.getAllInterests("Bearer " + token);
    }

    public Observable<InterestResponse> getUserInterests(String token, int id){
        return mGetRequest.getUserInterests("Bearer " + token, id);
    }


    public Observable<ImageResponce> uploadImage(File image){
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),image);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file",image.getName(),requestBody);
        return mPostRequest.loadImage(part);
    }

    public Call<ImageResponce> uploadImageLikeDCP(File image){
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),image);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file",image.getName(),requestBody);
        return mPostRequest.loadImageLikeDCP(part);
    }



    public Observable<UserProfileResponce> getProfileData(String token, int id){
        return mGetRequest.getUserProfileInfo("Bearer " + token, id);
    }

    public Observable<CourseAddedResponce> uploadCourse(String token, int id, CourseBody body){
        return mPostRequest.addCourse("Bearer " + token,id,body);
    }

    public Call<LessonAddedResponce> uploadLesson(String token, int userId,int courseId,LessonBody body){
        return mPostRequest.addLesson("Bearer " + token,userId,courseId,body);
    }

    public Observable<InterestSendResponce> attachInterests(String token, int id, InterestBody body){
        return mPostRequest.addInterest("Bearer " + token,id,body);
    }

    public Observable<CoursesNewsResponce> getNews(String token, int id, int number){
        return mGetRequest.getNews("Bearer " + token,id, number);
    }

    public Observable<CoursesNewsResponce> getCoursesBySearch(String token, String tag){
        return mGetRequest.getCoursesBySearch("Bearer " + token, tag);
    }

    public Observable<CoursesNewsResponce> getCreatedCourses(String token, int id){
        return mGetRequest.getCreatedCourses("Bearer " + token, id);
    }

    public Observable<CourseResponse> getCourse(String token, int id, int courseId){
        return mGetRequest.getCourse("Bearer " + token, id, courseId);
    }

    public Observable<LessonsByCourseResponse> getLessonsByCourse(String token, int id, int courseId){
        return mGetRequest.getLessonsByCourse("Bearer " + token, id, courseId);
    }

    public Observable<LessonAddedResponce> getLesson(String token, int id, int courseId, int lessonId){
        return mGetRequest.getLesson("Bearer " + token, id, courseId, lessonId);
    }

    public Observable<SubscribeResponse> subscribe(String token, int id, int courseId) {
        return mPostRequest.subscribe("Bearer " + token, id, courseId);
    }

    public Observable<CoursesNewsResponce> getSubscriptions(String token, int id){
        return mGetRequest.getSubscriptions("Bearer " + token, id);
    }


}
