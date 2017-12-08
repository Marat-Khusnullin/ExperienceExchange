package com.example.ruslanio.experienceexchange.presenters;

import android.os.Bundle;

import com.example.ruslanio.experienceexchange.interfaces.presenter.HomePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.HomeViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslanio on 29.11.2017.
 */

public class HomePresenter extends BasePresenter<HomeViewInterface>  implements HomePresenterInterface {
    public HomePresenter(HomeViewInterface view) {
        super(view);
    }

    @Override
    public void onInit(Bundle saveInstanceState) {
        super.onInit(saveInstanceState);

        List<String> themes = new ArrayList<>();
        themes.add("IT");
        themes.add("Cocking");
        themes.add("Driving");
        themes.add("Sport");

        List<String> authors = new ArrayList<>();
        authors.add("John Kennedy");
        authors.add("Victor Reznov");
        authors.add("Guy Richy");

        mView.setAuthors(authors);
        mView.setThemes(themes);


    }
}
