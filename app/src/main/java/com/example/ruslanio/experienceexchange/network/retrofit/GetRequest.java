package com.example.ruslanio.experienceexchange.network.retrofit;



import com.example.ruslanio.experienceexchange.network.pojo.course.news.CoursesNewsResponce;
import com.example.ruslanio.experienceexchange.network.pojo.interest.InterestResponse;
import com.example.ruslanio.experienceexchange.network.pojo.user.UserProfileResponce;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public interface GetRequest {

    @GET("api/v1/interests")
    Observable<InterestResponse> getAllInterests(@Header("token") String token);

    @GET("api/v1/users/{id}/info")
    Observable<UserProfileResponce> getUserProfileInfo(@Header("token") String token,@Path("id") int id);

    @GET("api/v1/users/{id}/news")
    Observable<CoursesNewsResponce> getNews(@Header("token") String token,@Path("id") int id);

}
