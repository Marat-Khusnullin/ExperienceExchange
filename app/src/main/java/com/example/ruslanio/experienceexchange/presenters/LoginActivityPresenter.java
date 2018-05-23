package com.example.ruslanio.experienceexchange.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.database.model.User;
import com.example.ruslanio.experienceexchange.interfaces.presenter.LoginActivityPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.LoginActivityViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.network.ApiManager;
import com.example.ruslanio.experienceexchange.network.body.LoginBody;
import com.example.ruslanio.experienceexchange.network.pojo.login.Result;
import com.example.ruslanio.experienceexchange.utils.Util;
import com.example.ruslanio.experienceexchange.views.ChoiceView;
import com.example.ruslanio.experienceexchange.views.MainView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public class LoginActivityPresenter extends BasePresenter<LoginActivityViewInterface> implements LoginActivityPresenterInterface {

    private ApiManager mApiManager;
    private DataBaseManager mDataBaseManager;

    public LoginActivityPresenter(LoginActivityViewInterface view) {
        super(view);
    }

    @Override
    public void onInit(Bundle saveInstanceState) {
        super.onInit(saveInstanceState);
        mApiManager = ApiManager.getInstance();
        mDataBaseManager = DataBaseManager.getInstance(mView.getContext());
    }

    @Override
    public void onLogin(String login, String password) {
        LoginBody body = new LoginBody(login, password);
        mApiManager.login(body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponce -> {
                    if (loginResponce.getStatus() == 200) {
                        saveUser(loginResponce.getResult());
                        mApiManager.getUserInterests(mDataBaseManager.getCurrentToken(), mDataBaseManager.getCurrentUserId())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(interestResponse -> {
                                    if (Util.checkCode(interestResponse.getStatus())){
                                        List<com.example.ruslanio.experienceexchange.network.pojo.interest.Result> resultList = interestResponse.getResult();
                                        List<Interest> interests1 = new ArrayList<>();

                                        for (com.example.ruslanio.experienceexchange.network.pojo.interest.Result result: resultList){
                                            Interest interest = new Interest();
                                            interest.setPercentage(result.getPercentage());
                                            interest.setId(result.getId());
                                            interest.setInterestName(result.getName());
                                            interests1.add(interest);
                                        }
                                        if(interests1.size()!=0) {
                                            Toast.makeText(mContext, "" + interests1.size(), Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(mContext, MainView.class);
                                            mContext.startActivity(intent);
                                            mView.finishActivity();
                                        } else {
                                            nextView();
                                        }


                                    } else {
                                        mView.showSnackbar(interestResponse.getError());
                                    }
                                }, throwable -> {
                                    throwable.printStackTrace();
                                    mView.showSnackbar(R.string.server_error);
                                });
                        //nextView();
                    }else
                        mView.showSnackbar(loginResponce.getError());
                }, error -> {
                    error.printStackTrace();
                    mView.showSnackbar(R.string.server_error);
                });
    }

    private void nextView() {
        Intent intent = new Intent(mView.getContext(), ChoiceView.class);
        mView.startActivity(intent);
        mView.finishActivity();
    }

    private void saveUser(Result result) {
        User user = new User();
        user.setId(result.getId());
        user.setToken(result.getToken());
        mDataBaseManager.updateCurrentUser(user);
    }

    public void checkForLogin() {
        if(mDataBaseManager.getCurrentUser()!=null) {
            Intent intent = new Intent(mView.getContext(), MainView.class);
            mView.startActivity(intent);
            mView.finishActivity();
        }
    }
}
