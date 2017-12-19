package com.example.ruslanio.experienceexchange.presenters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.interfaces.presenter.MainPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.MainViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.network.ApiManager;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Ruslanio on 18.11.2017.
 */

public class MainPresenter extends BasePresenter<MainViewInterface> implements MainPresenterInterface {
    private ApiManager mApiManager;
    private DataBaseManager mDataBaseManager;

    public MainPresenter(MainViewInterface view) {
        super(view);
    }


    @Override
    public void onInit(Bundle saveInstanceState) {
        super.onInit(saveInstanceState);
        mApiManager = ApiManager.getInstance();
        mDataBaseManager = DataBaseManager.getInstance(mView.getContext());

        String token = mDataBaseManager.getCurrentToken();

        SharedPreferences sharedPreference = mContext.getSharedPreferences(MAIN_PREFERENCES, Context.MODE_PRIVATE);
        boolean isFromChoice = sharedPreference.getBoolean(KEY_IS_FROM_CHOICE, false);

        if (!isFromChoice)
            mApiManager.getAllInterests(token)
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(interestResponse -> Observable.fromIterable(interestResponse.getResult()))
                    .map(result -> {
                        Interest interest = new Interest();
                        interest.setId(result.getId());
                        interest.setInterestName(result.getName());
                        interest.setPercentage(result.getPercentage());
                        return interest;
                    }).collectInto(new ArrayList<Interest>(), ArrayList::add)
                    .subscribe(interests -> mDataBaseManager.setNewInterests(interests),
                            throwable -> mView.showSnackbar("Internet connection problem"));
    }

    @Override
    public void onTabClicked(int tabId) {
        switch (tabId) {
            case R.id.tab_home:
                openHome();
                break;
            case R.id.tab_in_progress:
                opeInProgress();
                break;
            case R.id.tab_my:
                openMyCourses();
                break;
            case R.id.tab_profile:
                openProfile();
                break;
        }
    }

    private void openMyCourses() {
        mView.showMyCourses();
    }

    private void opeInProgress() {
        mView.showInProgress();
    }

    private void openHome() {
        mView.showHome();
    }

    private void openProfile() {
        mView.showProfile();
    }
}
