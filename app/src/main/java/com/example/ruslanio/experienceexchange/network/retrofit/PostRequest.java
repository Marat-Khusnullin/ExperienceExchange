package com.example.ruslanio.experienceexchange.network.retrofit;

import com.example.ruslanio.experienceexchange.network.body.LoginBody;
import com.example.ruslanio.experienceexchange.network.body.RegistrationBody;
import com.example.ruslanio.experienceexchange.network.pojo.image.ImageResponce;
import com.example.ruslanio.experienceexchange.network.pojo.login.LoginResponce;
import com.example.ruslanio.experienceexchange.network.pojo.registration.RegistrationResponce;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public interface PostRequest {

    @Multipart
    @POST("images")
    Observable<ImageResponce> loadImage(@Part("file") RequestBody image);

    @POST("api/v1/login")
    Observable<LoginResponce> login(@Body LoginBody loginBody);

    @POST("api/v1/users")
    Observable<RegistrationResponce> register(@Body RegistrationBody registrationBody);
}
