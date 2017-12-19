package com.example.ruslanio.experienceexchange.presenters;

import android.os.Bundle;

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
                    if (Util.checkCode(userProfileResponce.getStatus())){
                        Result result = userProfileResponce.getResult();
                        user.setId(result.getId());
                        user.setAge(result.getAge());
                        user.setAbout(result.getAbout());
                        user.setCity(result.getCity());
                        user.setCountry(result.getCountry());
                        user.setComments(result.getComments());
                        user.setCreated(result.getCreated());
                        user.setFinished(result.getFinished());
                        user.setFirstName(result.getFirstname());
                        user.setLastName(result.getLastname());
                        user.setGender(result.getGender());
                        user.setKarma(result.getKarma());
                        user.setTotalLikes(result.getLikes());
                        mDataBaseManager.updateCurrentUser(user);
                        mView.setData(user);
                        List<Interest> interests = mDataBaseManager.getUserInterests();
                        mView.setInterests(interests);

                    } else {
                        mView.showSnackbar(R.string.connection_error);
                    }
                }, throwable -> {
                    mView.showSnackbar(R.string.server_error);
                    throwable.printStackTrace();
                });

    }
}
