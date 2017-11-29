package com.example.ruslanio.experienceexchange.presenters;

import com.example.ruslanio.experienceexchange.interfaces.presenter.ProfilePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.ProfileViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;

/**
 * Created by Ruslanio on 18.11.2017.
 */

public class ProfilePresenter extends BasePresenter<ProfileViewInterface> implements ProfilePresenterInterface {

    public ProfilePresenter(ProfileViewInterface view) {
        super(view);
    }
}
