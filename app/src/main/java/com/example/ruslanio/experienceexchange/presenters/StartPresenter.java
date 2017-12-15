package com.example.ruslanio.experienceexchange.presenters;

import com.example.ruslanio.experienceexchange.interfaces.presenter.StartPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.StartViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;

/**
 * Created by Ruslanio on 14.12.2017.
 */

public class StartPresenter extends BasePresenter<StartViewInterface> implements StartPresenterInterface{
    public StartPresenter(StartViewInterface view) {
        super(view);
    }
}
