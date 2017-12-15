package com.example.ruslanio.experienceexchange.views;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.StartPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.StartViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseActivity;
import com.example.ruslanio.experienceexchange.presenters.StartPresenter;

/**
 * Created by Ruslanio on 14.12.2017.
 */

public class StartView extends BaseActivity<StartPresenterInterface> implements StartViewInterface {
    @Override
    protected StartPresenterInterface getPresenter() {
        return new StartPresenter(this);
    }

    @Override
    protected void onInit() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_start;
    }
}
