package com.example.ruslanio.experienceexchange.views;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.adapters.PopularThemesAdapter;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.database.model.User;
import com.example.ruslanio.experienceexchange.interfaces.presenter.ProfilePresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.ProfileViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseFragment;
import com.example.ruslanio.experienceexchange.presenters.ProfilePresenter;
import com.example.ruslanio.experienceexchange.utils.views.EntitledTextView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Ruslanio on 18.11.2017.
 */

public class ProfileView extends BaseFragment<ProfilePresenterInterface> implements ProfileViewInterface{

    @BindView(R.id.tv_profile_age)
    EntitledTextView mAge;
    @BindView(R.id.tv_profile_country)
    EntitledTextView mCountry;
    @BindView(R.id.tv_profile_city)
    EntitledTextView mCity;
    @BindView(R.id.tv_profile_about_me)
    TextView mAbout;
    @BindView(R.id.tv_profile_karma)
    EntitledTextView mKarma;
    @BindView(R.id.tv_profile_comments)
    EntitledTextView mCommentsCount;
    @BindView(R.id.tv_profile_created_courses)
    EntitledTextView mCreatedCourses;
    @BindView(R.id.tv_profile_finished_courses)
    EntitledTextView mFinishedCourses;
    @BindView(R.id.tv_profile_gender)
    EntitledTextView mGender;
    @BindView(R.id.tv_profile_birthday)
    EntitledTextView mBirthDay;
    @BindView(R.id.tv_profile_Total_likes)
    EntitledTextView mTotalLikes;
    @BindView(R.id.tv_profile_first_name)
    TextView mFirstName;
    @BindView(R.id.tv_profile_last_name)
    TextView mLastName;
    @BindView(R.id.rv_profile_popular_themes)
    RecyclerView mUserInterests;

    private PopularThemesAdapter mAdapter;

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

    @Override
    protected void onInit() {
        super.onInit();

        mAdapter = new PopularThemesAdapter();

        mUserInterests.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        mUserInterests.setAdapter(mAdapter);
    }

    @Override
    public void setData(User user){
       mFirstName.setText(user.getFirstName());
       mLastName.setText(user.getLastName());
       mAbout.setText(user.getAbout());
//       mBirthDay.setText(user.getBirthday());
       mCity.setText(mCity.getTitle() + user.getCity());
       mCountry.setText(mCountry.getTitle() + user.getCountry());
       mTotalLikes.setText(mTotalLikes.getTitle() + user.getTotalLikes() + "");
       mAge.setText(mAge.getTitle() + user.getAge() + "");
       mCreatedCourses.setText(mCreatedCourses.getTitle() + user.getCreated() + "");
       mGender.setText(mGender.getTitle() + user.getGender());
       mKarma.setText(mKarma.getTitle() + user.getKarma() + "");
       mCommentsCount.setText(mCommentsCount.getTitle() + user.getComments() + "");
       mFinishedCourses.setText(mFinishedCourses.getTitle() + user.getFinished() + "");
    }

    @Override
    public void setInterests(List<Interest> interests) {
        mAdapter.setThemes(interests);
    }
}
