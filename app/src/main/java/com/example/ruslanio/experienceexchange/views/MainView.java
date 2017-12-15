package com.example.ruslanio.experienceexchange.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.interfaces.presenter.MainPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.MainViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseActivity;
import com.example.ruslanio.experienceexchange.presenters.MainPresenter;
import com.roughike.bottombar.BottomBar;

import butterknife.BindView;

/**
 * Created by Ruslanio on 18.11.2017.
 */

public class MainView extends BaseActivity<MainPresenterInterface>
        implements MainViewInterface {

    private static final String TAG_PROFILE = "tag_profile";
    private static final String TAG_HOME = "tag_home";
    private static final String TAG_IN_PROGRESS = "tag_in_progress";
    private static final String TAG_MY = "tag_my";


    @BindView(R.id.home_bottom_bar)
    BottomBar mBottomBar;

    @Override
    protected MainPresenterInterface getPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onInit() {
        mBottomBar.setOnTabSelectListener(tabId -> mPresenter.onTabClicked(tabId));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
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

    @Override
    public void showHome() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment home = fragmentManager.findFragmentByTag(TAG_HOME);

        if (home == null)
            home = HomeView.getInstance();
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_container,home,TAG_HOME)
                .commit();
    }

    @Override
    public void showInProgress() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment inProgress = fragmentManager.findFragmentByTag(TAG_IN_PROGRESS);

        if (inProgress == null)
            inProgress = CourseInProgressView.getInstance();
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_container,inProgress,TAG_IN_PROGRESS)
                .commit();
    }

    @Override
    public void showMyCourses() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment myCourses = fragmentManager.findFragmentByTag(TAG_MY);

        if (myCourses == null)
            myCourses = MyCoursesView.getInstance();
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_container,myCourses,TAG_MY)
                .commit();
    }
}
