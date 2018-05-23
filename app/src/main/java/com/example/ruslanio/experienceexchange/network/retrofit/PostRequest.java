package com.example.ruslanio.experienceexchange.network.retrofit;

import com.example.ruslanio.experienceexchange.network.body.CourseBody;
import com.example.ruslanio.experienceexchange.network.body.LoginBody;
import com.example.ruslanio.experienceexchange.network.body.RegistrationBody;
import com.example.ruslanio.experienceexchange.network.body.interest.InterestBody;
import com.example.ruslanio.experienceexchange.network.body.lesson.LessonBody;
import com.example.ruslanio.experienceexchange.network.pojo.course.SubscribeResponse;
import com.example.ruslanio.experienceexchange.network.pojo.course.added.CourseAddedResponce;
import com.example.ruslanio.experienceexchange.network.pojo.course.news.CourseResponse;
import com.example.ruslanio.experienceexchange.network.pojo.image.ImageResponce;
import com.example.ruslanio.experienceexchange.network.pojo.interest.send.InterestSendResponce;
import com.example.ruslanio.experienceexchange.network.pojo.lesson.LessonAddedResponce;
import com.example.ruslanio.experienceexchange.network.pojo.login.LoginResponce;
import com.example.ruslanio.experienceexchange.network.pojo.registration.RegistrationResponce;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public interface PostRequest {

    @Multipart
    @POST("images")
    Observable<ImageResponce> loadImage(@Part MultipartBody.Part image);

    @Multipart
    @POST("images")
    Call<ImageResponce> loadImageLikeDCP(@Part MultipartBody.Part image);

    @POST("api/v1/login")
    Observable<LoginResponce> login(@Body LoginBody loginBody);

    @POST("api/v1/users")
    Observable<RegistrationResponce> register(@Body RegistrationBody registrationBody);

    @POST("api/v1/users/{id}/courses")
    Observable<CourseAddedResponce> addCourse(@Header("Authorization") String token, @Path("id") int userId,@Body CourseBody body);

    @POST("api/v1/users/{userId}/courses/{courseId}/lessons")
    Call<LessonAddedResponce> addLesson(@Header("Authorization") String token,
                                              @Path("userId") int userId,
                                              @Path("courseId") int courseId,
                                              @Body LessonBody body);

    @POST("api/v1/users/{id}/interests")
    Observable<InterestSendResponce> addInterest(@Header("Authorization") String token, @Path("id") int userId, @Body InterestBody body);

    @PUT("api/v1/subscriptions")
    Observable<SubscribeResponse> subscribe(@Header("Authorization") String token, @Query("user_id") int id, @Query("course_id") int courseId);
}
