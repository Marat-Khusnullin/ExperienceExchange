package com.example.ruslanio.experienceexchange.presenters;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.HomePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.HomeViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;

/**
 * Created by Ruslanio on 18.11.2017.
 */

public class HomePresenter extends BasePresenter<HomeViewInterface> implements HomePresenterInterface {

    public HomePresenter(HomeViewInterface view) {
        super(view);
    }

    @Override
    public void onTabClicked(int tabId) {
        switch (tabId){
            case R.id.tab_home:
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

    private void openProfile() {
        mView.showProfile();
    }
}
