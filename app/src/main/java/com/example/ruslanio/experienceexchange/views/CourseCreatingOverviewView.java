package com.example.ruslanio.experienceexchange.views;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.adapters.PopularThemesAdapter;
import com.example.ruslanio.experienceexchange.adapters.TempLessonAdapter;
import com.example.ruslanio.experienceexchange.database.model.Interest;
import com.example.ruslanio.experienceexchange.database.model.temporary.TempLesson;
import com.example.ruslanio.experienceexchange.interfaces.presenter.CourseCreatingOverviewPresenterInterface;
import com.example.ruslanio.experienceexchange.interfaces.view.CourseCreatingOverviewViewInterface;
import com.example.ruslanio.experienceexchange.mvp.BaseFragment;
import com.example.ruslanio.experienceexchange.presenters.CourseCreatingOverviewPresenter;
import com.example.ruslanio.experienceexchange.utils.Util;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Ruslanio on 16.12.2017.
 */

public class CourseCreatingOverviewView extends BaseFragment<CourseCreatingOverviewPresenterInterface>
        implements CourseCreatingOverviewViewInterface {

    private static final String KEY_COURSE_NAME = "key_course_name";
    private static final String KEY_COURSE_DESCRIPTION = "key_course_description";
    private static final String KEY_COURSE_COVER = "key_course_cover";


    @BindView(R.id.btn_course_add_lesson)
    Button mAddLesson;

    @BindView(R.id.btn_course_add_image)
    Button mAddImage;

    @BindView(R.id.et_course_name)
    TextInputEditText mName;

    @BindView(R.id.et_course_description)
    TextInputEditText mDescription;

    @BindView(R.id.iv_course_cover)
    ImageView mCourseCover;

    private TempLessonAdapter mAdapter;

    @BindView(R.id.rv_course_lessons)
    RecyclerView mLessons;

    @BindView(R.id.btn_course_done)
    Button mDone;

    @BindView(R.id.rv_course_creating_interest_chose)
    RecyclerView mInterestChoice;

    @BindView(R.id.t_course_creating_chosen_interest)
    TextView mChosenInterest;

    private PopularThemesAdapter mInterestAdapter;

    public CourseCreatingOverviewView() {
        mAdapter = new TempLessonAdapter();
        mInterestAdapter = new PopularThemesAdapter();
    }

    private void setLessons(List<TempLesson> lessons) {
        mAdapter.setLessons(lessons);
    }

    @Override
    protected void onInit(Bundle savedInstanceState) {

        mInterestChoice.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        mInterestChoice.setAdapter(mInterestAdapter);

        mInterestAdapter.setOnInterestClickListener(interest -> {
            mChosenInterest.setText(interest.getInterestName());
            mChosenInterest.setVisibility(View.VISIBLE);
            mInterestChoice.setVisibility(View.GONE);

        });

        mChosenInterest.setOnClickListener(view -> {
            mChosenInterest.setVisibility(View.GONE);
            mInterestChoice.setVisibility(View.VISIBLE);
        });

        mDone.setOnClickListener(btn -> {
            String name = mName.getText().toString();
            String description = mDescription.getText().toString();
            String image = null;
            if(mCourseCover.getContentDescription()!= null)
            image = mCourseCover.getContentDescription().toString();
            String interestName= mChosenInterest.getText().toString();

            mPresenter.buildCourse(name, description, image, interestName);
            Util.CourseInfoHolder.vipeOut();

        });

        mAddImage.setOnClickListener(btn -> {
            Intent pickImageIntent = new Intent();
            pickImageIntent.setType("image/*");
            pickImageIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(pickImageIntent, PICK_IMAGE_REQUEST_CODE);
        });

        mAdapter.setOnTempLessonClickListener(tempLesson -> {
            mPresenter.onTempLessonClosed(tempLesson);
        });
        String name = null;
        String description = null;
        String image = null;
        String interest = null;

        name = Util.CourseInfoHolder.name;
        description = Util.CourseInfoHolder.description;
        image = Util.CourseInfoHolder.image;
        interest = Util.CourseInfoHolder.interest;

        if (interest != null && !interest.equals("")) {
            mChosenInterest.setVisibility(View.VISIBLE);
            mInterestChoice.setVisibility(View.GONE);
            mChosenInterest.setText(interest);
        } else {
            mChosenInterest.setVisibility(View.GONE);
            mInterestChoice.setVisibility(View.VISIBLE);
        }

        if (image != null && !image.equals("")) {
            Picasso.with(getContext())
                    .load(Uri.parse(image))
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .noFade()
                    .into(mCourseCover);
            mCourseCover.setContentDescription(image);
        }

        if (name != null && !name.equals("")) {
            mName.setText(name);
        }
        if (description != null && !description.equals("")) {
            mDescription.setText(description);
        }

        mLessons.setLayoutManager(new LinearLayoutManager(getContext()));
        mLessons.setAdapter(mAdapter);
        mLessons.setNestedScrollingEnabled(false);

        mAddLesson.setOnClickListener(btn -> {
            int lessonCount = mAdapter.getItemCount();
            mPresenter.onAddLesson(lessonCount);
        });
    }

    @Override
    public void setInterests(List<Interest> interests){
        mInterestAdapter.setThemes(interests);
    }

    @Override
    protected CourseCreatingOverviewPresenterInterface getPresenter() {
        return new CourseCreatingOverviewPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_course_creating_overview;
    }

    public static CourseCreatingOverviewView getInstance(List<TempLesson> lessons) {
        CourseCreatingOverviewView overviewView = new CourseCreatingOverviewView();
        overviewView.setArguments(new Bundle());
        overviewView.setLessons(lessons);
        return overviewView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Util.CourseInfoHolder.name = mName.getText().toString();
        Util.CourseInfoHolder.description = mDescription.getText().toString();
        if (mCourseCover.getContentDescription() != null)
            Util.CourseInfoHolder.image = mCourseCover.getContentDescription().toString();
        Util.CourseInfoHolder.interest = mChosenInterest.getText().toString();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST_CODE && data != null) {
                Uri uri = data.getData();
                Picasso.with(getContext())
                        .load(uri)
                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                        .networkPolicy(NetworkPolicy.NO_CACHE)
                        .noFade()
                        .into(mCourseCover);
                mCourseCover.setContentDescription(uri.toString());

            } else {
                showSnackbar(R.string.image_was_not_chosen);
            }
        } else {
            showSnackbar(R.string.please_try_again);
        }
    }
}
