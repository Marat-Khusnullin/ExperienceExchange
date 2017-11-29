package com.example.ruslanio.experienceexchange.views;

import android.support.v4.app.Fragment;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.ProfilePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.ProfileViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseFragment;
import com.example.ruslanio.experienceexchange.presenters.ProfilePresenter;

/**
 * Created by Ruslanio on 18.11.2017.
 */

public class ProfileView extends BaseFragment<ProfilePresenterInterface> implements ProfileViewInterface{
    @Override
    protected ProfilePresenterInterface getPresenter() {
        return new ProfilePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_profile;
    }

    public static ProfileView getInstance() {
        return new ProfileView();
    }
}
