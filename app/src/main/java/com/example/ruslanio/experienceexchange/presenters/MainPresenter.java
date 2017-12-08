package com.example.ruslanio.experienceexchange.presenters;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.MainPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.MainViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;

/**
 * Created by Ruslanio on 18.11.2017.
 */

public class MainPresenter extends BasePresenter<MainViewInterface> implements MainPresenterInterface {

    public MainPresenter(MainViewInterface view) {
        super(view);
    }

    @Override
    public void onTabClicked(int tabId) {
        switch (tabId){
            case R.id.tab_home:
                openHome();
                break;
            case R.id.tab_in_progress:
                break;
            case R.id.tab_my:
                break;
            case R.id.tab_profile:
                openProfile();
                break;
        }
    }

    private void openHome() {
        mView.showHome();
    }

    private void openProfile() {
        mView.showProfile();
    }
}
