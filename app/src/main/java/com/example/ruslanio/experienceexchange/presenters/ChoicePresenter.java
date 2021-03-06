package com.example.ruslanio.experienceexchange.presenters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.interfaces.presenter.ChoicePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.ChoiceViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.network.ApiManager;
import com.example.ruslanio.experienceexchange.network.body.interest.InterestBody;
import com.example.ruslanio.experienceexchange.network.body.interest.InterestObject;
import com.example.ruslanio.experienceexchange.network.pojo.interest.Result;
import com.example.ruslanio.experienceexchange.utils.Util;
import com.example.ruslanio.experienceexchange.views.MainView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Ruslanio on 13.11.2017.
 */

public class ChoicePresenter extends BasePresenter<ChoiceViewInterface> implements ChoicePresenterInterface {
    private ApiManager mApiManager;
    private List<Integer> mInterestIds;
    private DataBaseManager mDataBaseManager;


    public ChoicePresenter(ChoiceViewInterface view) {
        super(view);
        mInterestIds = new ArrayList<>();
    }


    @Override
    public void onInit(Bundle saveInstanceState) {
        super.onInit(saveInstanceState);
        mApiManager = ApiManager.getInstance();
        mDataBaseManager = DataBaseManager.getInstance(mView.getContext());


        String token = mDataBaseManager.getCurrentToken();

        mApiManager.getAllInterests(token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(interestResponse -> {
                    if (Util.checkCode(interestResponse.getStatus())){
                        List<Result> resultList = interestResponse.getResult();
                        List<Interest> interests = new ArrayList<>();

                        for (Result result: resultList){
                            Interest interest = new Interest();
                            interest.setPercentage(result.getPercentage());
                            interest.setId(result.getId());
                            interest.setInterestName(result.getName());
                            interests.add(interest);
                        }

                        mView.showBubblePicker(interests);
                        mDataBaseManager.setNewInterests(interests);
                    } else {
                        mView.showSnackbar(interestResponse.getError());
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    mView.showSnackbar(R.string.server_error);
                });
    }


    @Override
    public boolean onReady(){
            SharedPreferences sharedPreferences = mView.getContext().getSharedPreferences(MAIN_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(KEY_IS_FROM_CHOICE,true);
            editor.apply();


        InterestBody body = new InterestBody();
        List<InterestObject> interestObjects = new ArrayList<>();

        for (Interest interest:mDataBaseManager.getUserInterests()){
            InterestObject interestObject = new InterestObject();
            interestObject.setId(interest.getId());
            interestObject.setName(interest.getInterestName());
            interestObject.setPercentage(interest.getPercentage());
            interestObjects.add(interestObject);
        }

        body.setInterests(interestObjects);
        String token = mDataBaseManager.getCurrentToken();
        int id = mDataBaseManager.getCurrentUserId();

            mApiManager.attachInterests(token, id, body)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(interestSendResponce -> {
                        if (Util.checkCode(interestSendResponce.getStatus())){
                            mView.showSnackbar("Interests are added!");
                            mView.nextView(mInterestIds);
                            mView.finishActivity();
                        } else {
                            mView.showSnackbar(R.string.connection_error);
                        }
                    }, throwable -> {
                        mView.showSnackbar(R.string.server_error);
                        throwable.printStackTrace();
                    });


            return true;
    }

    public void checkForInterests() {
           //updateInterests();

    }

    private void updateInterests() {
        mApiManager.getUserInterests(mDataBaseManager.getCurrentToken(), mDataBaseManager.getCurrentUserId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(interestResponse -> {
                    if (Util.checkCode(interestResponse.getStatus())){
                        List<Result> resultList = interestResponse.getResult();
                        List<Interest> interests1 = new ArrayList<>();

                        for (Result result: resultList){
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
                        }


                    } else {
                        mView.showSnackbar(interestResponse.getError());
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    mView.showSnackbar(R.string.server_error);
                });




        /*mApiManager.getUserInterests(mDataBaseManager.getCurrentToken(), mDataBaseManager.getCurrentUserId())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(interestResponse -> Observable.fromIterable(interestResponse.getResult()))
                .map(result -> {
                    Interest interest = new Interest();
                    interest.setId(result.getId());
                    interest.setInterestName(result.getName());
                    interest.setPercentage(result.getPercentage());
                    return interest;
                }).collectInto(new ArrayList<Interest>(), ArrayList::add)
                .subscribe(interests -> {
                            if(interests.size()!=0) {
                                Intent intent = new Intent(mContext, MainView.class);
                                mContext.startActivity(intent);
                            } else {

                            }
                        },
                        throwable -> mView.showSnackbar("Internet connection problem"));*/
    }




}
