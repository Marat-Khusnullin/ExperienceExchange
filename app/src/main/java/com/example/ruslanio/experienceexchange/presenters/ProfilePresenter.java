package com.example.ruslanio.experienceexchange.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.database.model.User;
import com.example.ruslanio.experienceexchange.interfaces.presenter.ProfilePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.ProfileViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.network.ApiManager;
import com.example.ruslanio.experienceexchange.network.pojo.user.Result;
import com.example.ruslanio.experienceexchange.utils.Util;
import com.example.ruslanio.experienceexchange.views.LoginActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Ruslanio on 18.11.2017.
 */

public class ProfilePresenter extends BasePresenter<ProfileViewInterface> implements ProfilePresenterInterface {

    private DataBaseManager mDataBaseManager;
    private ApiManager mApiManager;

    public ProfilePresenter(ProfileViewInterface view) {
        super(view);
    }

    @Override
    public void onInit(Bundle saveInstanceState) {
        super.onInit(saveInstanceState);
        mDataBaseManager = DataBaseManager.getInstance(mView.getContext());
        mApiManager = ApiManager.getInstance();

        int currentId = mDataBaseManager.getCurrentUserId();
        String currentToken = mDataBaseManager.getCurrentToken();

        User user = new User();

        mApiManager.getProfileData(currentToken, currentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userProfileResponce -> {
                    Toast.makeText(mContext, userProfileResponce.getError(), Toast.LENGTH_SHORT).show();
                    if (Util.checkCode(userProfileResponce.getStatus())){
                        Result result = userProfileResponce.getResult();
                        user.setId(result.getId());
                        if(result.getAge()!=null) {
                            user.setAge(result.getAge());
                        } else {
                            user.setAge(0);
                        }
                        if(result.getAbout()!=null) {
                            user.setAbout(result.getAbout());
                        } else {
                            user.setAbout("Не указано");
                        }
                        if(result.getCity()!=null) {
                            user.setCity(result.getCity());
                        } else {
                            user.setCity("Не указано");
                        }
                        if(result.getCountry()!=null) {
                            user.setCountry(result.getCountry());
                        } else {
                            user.setCountry("Не указано");
                        }
                        user.setComments(result.getComments());
                        user.setCreated(result.getCreated());
                        user.setFinished(result.getFinished());
                        user.setFirstName(result.getFirstname());
                        user.setLastName(result.getLastname());
                        if(result.getGender()!=null) {
                            user.setAbout(result.getGender());
                        } else {
                            user.setGender("Не указано");
                        }
                        user.setKarma(result.getKarma());
                        user.setTotalLikes(result.getLikes());
                        mView.setData(user);
                        List<Interest> interests = mDataBaseManager.getUserInterests();
                        mView.setInterests(interests);

                    } else {
                        mView.showSnackbar(R.string.connection_error);
                    }
                }, throwable -> {
                    mView.showSnackbar("Проблема получения данных о юзере!");
                    throwable.printStackTrace();
                });

    }

    public void exitFromUser() {
        mDataBaseManager.clearUsers();
        mDataBaseManager.deleteAllTemporaryLessons();
        mDataBaseManager.deleteAll();
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }
}
