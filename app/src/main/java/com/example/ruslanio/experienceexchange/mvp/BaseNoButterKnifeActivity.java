package com.example.ruslanio.experienceexchange.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.ruslanio.experienceexchange.interfaces.presenter.BasePresenterInterface;

/**
 * Created by Ruslanio on 29.11.2017.
 */

public abstract class BaseNoButterKnifeActivity<T extends BasePresenterInterface> extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        if (mPresenter == null)
            mPresenter = getPresenter();
        mPresenter.onCreate(savedInstanceState);
        onInit();
        mPresenter.onInit(savedInstanceState);
    }
}
