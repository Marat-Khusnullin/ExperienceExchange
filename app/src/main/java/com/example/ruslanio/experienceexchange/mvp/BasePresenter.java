package com.example.ruslanio.experienceexchange.mvp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.ruslanio.experienceexchange.EEAplication;
import com.example.ruslanio.experienceexchange.interfaces.presenter.BasePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.BaseViewInterface;
import com.example.ruslanio.experienceexchange.utils.rxbus.RxBus;


/**
 * Created by Ruslanio on 06.11.2017.
 */

public abstract class BasePresenter<T extends BaseViewInterface> implements BasePresenterInterface, BaseConstants{


    private RxBus mRxBus;
    protected T mView;
    protected Context mContext;

    public BasePresenter(T view) {
        mView = view;
        mContext = view.getContext().getApplicationContext();
        mRxBus = ((EEAplication) mContext).getEventBus();
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {

    }


    protected void publish(String tag,Object data) {
        mRxBus.publish(tag, data);
    }

    protected void publish(String tag){
        mRxBus.publish(tag);
    }



    protected SharedPreferences getDefaultPreferences() {
        return mContext.getSharedPreferences(MAIN_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public void onInit(Bundle saveInstanceState) {

    }


    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {

    }

    @Override
    public void onRestoreInstanceState(Bundle saveInstanceState) {

    }

    @Override
    public void onStart() {
        mRxBus.subscribe(this);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {
        mRxBus.unsubscribe(this);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

}
