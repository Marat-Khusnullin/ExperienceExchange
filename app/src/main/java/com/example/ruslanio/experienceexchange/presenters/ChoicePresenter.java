package com.example.ruslanio.experienceexchange.presenters;

import android.os.Bundle;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.interfaces.presenter.ChoicePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.ChoiceViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;
import com.example.ruslanio.experienceexchange.network.ApiManager;
import com.example.ruslanio.experienceexchange.network.pojo.interest.Result;
import com.example.ruslanio.experienceexchange.utils.Util;

import java.util.ArrayList;
import java.util.List;

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
        if (mInterestIds.size() != 0){
            mView.nextView(mInterestIds);
            return true;
        } else
            return false;
    }


}
