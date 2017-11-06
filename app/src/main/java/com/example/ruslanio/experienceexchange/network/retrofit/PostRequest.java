package com.example.ruslanio.experienceexchange.network.retrofit;

import com.example.ruslanio.experienceexchange.network.body.LoginBody;
import com.example.ruslanio.experienceexchange.network.body.RegistrationBody;
import com.example.ruslanio.experienceexchange.network.pojo.login.LoginResponce;
import com.example.ruslanio.experienceexchange.network.pojo.registration.RegistrationResponce;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public interface PostRequest {

    @POST("/login")
    Observable<LoginResponce> login(@Body LoginBody loginBody);

    @POST("/users")
    Observable<RegistrationResponce> register(@Body RegistrationBody registrationBody);
}
