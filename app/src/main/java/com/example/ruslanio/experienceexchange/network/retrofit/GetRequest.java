package com.example.ruslanio.experienceexchange.network.retrofit;



import com.example.ruslanio.experienceexchange.network.pojo.interest.InterestResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public interface GetRequest {

    @GET("api/v1/interests")
    Observable<InterestResponse> getAllInterests(@Header("token") String token);

}
