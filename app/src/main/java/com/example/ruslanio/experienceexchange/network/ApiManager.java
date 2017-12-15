package com.example.ruslanio.experienceexchange.network;

import com.example.ruslanio.experienceexchange.network.body.LoginBody;
import com.example.ruslanio.experienceexchange.network.body.RegistrationBody;
import com.example.ruslanio.experienceexchange.network.pojo.interest.InterestResponse;
import com.example.ruslanio.experienceexchange.network.pojo.login.LoginResponce;
import com.example.ruslanio.experienceexchange.network.pojo.registration.RegistrationResponce;
import com.example.ruslanio.experienceexchange.network.retrofit.GetRequest;
import com.example.ruslanio.experienceexchange.network.retrofit.PostRequest;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public class ApiManager {
    private static ApiManager mApiManager;
    private final static String BASE_URL = "http://18.221.121.255:8080/exex/api/v1/";

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
        return mGetRequest.getAllInterests(token);
    }
}
