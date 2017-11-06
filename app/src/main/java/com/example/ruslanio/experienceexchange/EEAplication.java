package com.example.ruslanio.experienceexchange;

import android.support.multidex.MultiDexApplication;

import com.example.ruslanio.experienceexchange.utils.rxbus.RxBus;

/**
 * Created by Ruslanio on 06.11.2017.
 */

public class EEAplication extends MultiDexApplication {
    private RxBus mRxBus;

    public RxBus getEventBus() {
        return mRxBus;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mRxBus == null)
            mRxBus = new RxBus();
    }

}
