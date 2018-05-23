package com.example.ruslanio.experienceexchange.views;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.adapters.PopularAuthorsAdapter;
import com.example.ruslanio.experienceexchange.adapters.PopularThemesAdapter;
import com.example.ruslanio.experienceexchange.adapters.CoursesAdapter;
import com.example.ruslanio.experienceexchange.database.model.Course;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.interfaces.presenter.HomePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.HomeViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseFragment;
import com.example.ruslanio.experienceexchange.presenters.HomePresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Ruslanio on 29.11.2017.
 */

public class HomeView extends BaseFragment<HomePresenterInterface> implements HomeViewInterface {


    @BindView(R.id.rv_home_popular_themes)
    RecyclerView mPopularThemes;
    @BindView(R.id.rv_home_recomended_courses)
    RecyclerView mRecommendedCourses;
    @BindView(R.id.home_toolbar)
    Toolbar toolbar;
    @BindView(R.id.home_edit)
    EditText editText;
    @BindView(R.id.home_course_search)
    ImageView searchImage;

    private PopularAuthorsAdapter mAuthorsAdapter;
    private PopularThemesAdapter mThemesAdapter;
    private CoursesAdapter mCoursesAdapter;

    @Override
    protected HomePresenterInterface getPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void onInit() {
        super.onInit();

        mAuthorsAdapter = new PopularAuthorsAdapter();
        mThemesAdapter = new PopularThemesAdapter();
        mCoursesAdapter = new CoursesAdapter();

        mCoursesAdapter.setContext(getContext());

        mPopularThemes.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        mRecommendedCourses.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecommendedCourses.setNestedScrollingEnabled(false);

        mPopularThemes.setAdapter(mThemesAdapter);
        mRecommendedCourses.setAdapter(mCoursesAdapter);

        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getVisibility()!=View.VISIBLE) {
                    editText.setVisibility(View.VISIBLE);
                    editText.setText("Введите тэги");
                } else {
                    mPresenter.showCoursesBySearch(editText.getText().toString());
                }
            }
        });


    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void setAuthors(List<String> authors) {
        mAuthorsAdapter.setAuthors(authors);
    }

    @Override
    public void setThemes(List<Interest> themes) {
        mThemesAdapter.setThemes(themes);
    }

    @Override
    public void setCourses(List<Course> courses) {
        mCoursesAdapter.setCourses(courses);
    }

    public static HomeView getInstance() {
        return new HomeView();
    }
}
