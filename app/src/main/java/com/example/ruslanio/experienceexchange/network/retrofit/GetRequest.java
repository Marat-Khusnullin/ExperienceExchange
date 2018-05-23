package com.example.ruslanio.experienceexchange.network.retrofit;



import com.example.ruslanio.experienceexchange.network.pojo.course.news.CourseResponse;
import com.example.ruslanio.experienceexchange.network.pojo.course.news.CoursesNewsResponce;
import com.example.ruslanio.experienceexchange.network.pojo.interest.InterestResponse;
import com.example.ruslanio.experienceexchange.network.pojo.lesson.LessonAddedResponce;
import com.example.ruslanio.experienceexchange.network.pojo.lesson.LessonsByCourseResponse;
import com.example.ruslanio.experienceexchange.network.pojo.user.UserProfileResponce;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public interface GetRequest {

    @GET("api/v1/interests")
    Observable<InterestResponse> getAllInterests(@Header("Authorization") String token);

    @GET("api/v1/users/{id}/info")
    Observable<UserProfileResponce> getUserProfileInfo(@Header("Authorization") String token, @Path("id") int id);

    @GET("api/v1/users/{id}/news")
    Observable<CoursesNewsResponce> getNews(@Header("Authorization") String token, @Path("id") int id, @Query("page_number") int number);

    @GET("api/v1/users/{id}/interests")
    Observable<InterestResponse> getUserInterests(@Header("Authorization") String token, @Path("id") int id);

    @GET("api/v1/search/courses")
    Observable<CoursesNewsResponce> getCoursesBySearch(@Header("Authorization") String token, @Query("q") String tag);

    @GET("api/v1/users/{id}/courses?type=created")
    Observable<CoursesNewsResponce> getCreatedCourses(@Header("Authorization") String token, @Path("id") int id);

    @GET("api/v1/users/{id}/courses/{cid}")
    Observable<CourseResponse> getCourse(@Header("Authorization") String token, @Path("id") int id, @Path("cid") int courseId);

    @GET("api/v1/users/{id}/courses/{cid}/lessons")
    Observable<LessonsByCourseResponse> getLessonsByCourse(@Header("Authorization") String token, @Path("id") int id, @Path("cid") int courseId);

    @GET("api/v1/users/{id}/courses/{cid}/lessons/{lid}")
    Observable<LessonAddedResponce> getLesson(@Header("Authorization") String token, @Path("id") int id, @Path("cid") int courseId, @Path("lid") int lessonId);

    @GET("api/v1/users/{id}/courses?type=relative")
    Observable<CoursesNewsResponce> getSubscriptions(@Header("Authorization") String token, @Path("id") int id);
}
