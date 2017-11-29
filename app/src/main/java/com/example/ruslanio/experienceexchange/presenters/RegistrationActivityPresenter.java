package com.example.ruslanio.experienceexchange.presenters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.RegistrationActivityPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.RegistrationActivityViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.network.ApiManager;
import com.example.ruslanio.experienceexchange.network.body.RegistrationBody;
import com.example.ruslanio.experienceexchange.network.pojo.registration.Result;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public class RegistrationActivityPresenter extends BasePresenter<RegistrationActivityViewInterface> implements RegistrationActivityPresenterInterface {

    private ApiManager mApiManager;

    public RegistrationActivityPresenter(RegistrationActivityViewInterface view) {
        super(view);
        mApiManager = ApiManager.getInstance();
    }

    @Override
    public void onSubmit(String login, String email, String password, String firstName, String lastName) {
        RegistrationBody registrationBody = new RegistrationBody(login, email, password, firstName, lastName);
        mApiManager.register(registrationBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(registrationResponce -> {
                    if (registrationResponce.getStatus() == 201) {
                        Result result = registrationResponce.getResult();
                        saveData(result.getId(), result.getToken());
                        nextView();
                    } else
                        mView.showSnackbar(R.string.connection_error);
                }, error -> {
                    error.printStackTrace();
                    mView.showSnackbar(R.string.server_error);
                });
    }

    private void nextView() {
        Intent intent = new Intent(mView.getContext(), ChoiceView.class);
        mView.startActivity(intent);
    }

    private void saveData(Integer id, String token) {
        SharedPreferences preferences = mView.getContext().getSharedPreferences(MAIN_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(ID_KEY, id)
                .putString(TOKEN_KEY, token)
                .apply();
    }
}
