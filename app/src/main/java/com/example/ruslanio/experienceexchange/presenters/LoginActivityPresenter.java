package com.example.ruslanio.experienceexchange.presenters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.LoginActivityPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.LoginActivityViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.network.ApiManager;
import com.example.ruslanio.experienceexchange.network.body.LoginBody;
import com.example.ruslanio.experienceexchange.views.HomeActivity;
import com.example.ruslanio.experienceexchange.views.LoginActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public class LoginActivityPresenter extends BasePresenter<LoginActivityViewInterface> implements LoginActivityPresenterInterface {

    private ApiManager mApiManager;

    public LoginActivityPresenter(LoginActivityViewInterface view) {
        super(view);
        mApiManager = ApiManager.getInstance();
    }

    @Override
    public void onLogin(String login, String password) {
        LoginBody body = new LoginBody(login, password);
        mApiManager.login(body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponce -> {
                    if (loginResponce.getStatus() == 200) {
                        saveToken(loginResponce.getResult());
                        nextView();
                    }else
                        mView.showSnackbar(R.string.connection_error);
                }, error -> {
                    error.printStackTrace();
                    mView.showSnackbar(R.string.server_error);
                });
    }

    private void nextView() {
        Intent intent = new Intent(mView.getContext(), HomeActivity.class);
        mView.startActivity(intent);
    }

    private void saveToken(String result) {
        SharedPreferences preferences = mView.getContext()
                .getSharedPreferences(MAIN_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TOKEN_KEY,result).apply();
    }
}
