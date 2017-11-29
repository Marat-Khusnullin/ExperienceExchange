package com.example.ruslanio.experienceexchange.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.HomePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.HomeViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseActivity;
import com.example.ruslanio.experienceexchange.presenters.HomePresenter;
import com.roughike.bottombar.BottomBar;

import butterknife.BindView;

/**
 * Created by Ruslanio on 18.11.2017.
 */

public class HomeView extends BaseActivity<HomePresenterInterface>
        implements HomeViewInterface {

    private static final String TAG_PROFILE = "tag_profile";

    @BindView(R.id.home_bottom_bar)
    BottomBar mBottomBar;

    @Override
    protected HomePresenterInterface getPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void onInit() {
        mBottomBar.setOnTabSelectListener(tabId -> mPresenter.onTabClicked(tabId));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void showProfile() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment profile = fragmentManager.findFragmentByTag(TAG_PROFILE);

        if (profile == null)
            profile = ProfileView.getInstance();
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_container,profile,TAG_PROFILE)
                .commit();
    }
}
