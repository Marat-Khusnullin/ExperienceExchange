package com.example.ruslanio.experienceexchange.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.DataBaseManager;
import com.example.ruslanio.experienceexchange.database.model.Course;
import com.example.ruslanio.experienceexchange.network.ApiManager;
import com.example.ruslanio.experienceexchange.network.pojo.course.news.CourseResult;
import com.example.ruslanio.experienceexchange.utils.Util;
import com.example.ruslanio.experienceexchange.utils.views.EntitledTextView;
import com.example.ruslanio.experienceexchange.views.CourseViewActivity;
import com.like.LikeButton;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Ruslanio on 29.11.2017.
 */

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {

    private List<Course> mCourses = Collections.emptyList();
    private OnCourseClickListener mOnCourseClickListener;
    private Context mContext;
    private DataBaseManager mDataBaseManager;
    private ApiManager mApiManager;

    public void setCourses(List<Course> courses) {
        mCourses = courses;
        notifyDataSetChanged();
    }

    public void setOnCourseClickListener(OnCourseClickListener onCourseClickListener) {
        mOnCourseClickListener = onCourseClickListener;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.course_name)
        TextView mCourseName;
        @BindView(R.id.course_author)
        TextView mAuthor;
        @BindView(R.id.course_like_count)
        TextView mLikeCount;
        @BindView(R.id.course_short_description)
        TextView mDescription;
        @BindView(R.id.course_details)
        Button mDetails;
        @BindView(R.id.course_like_button)
        LikeButton mLikeButton;
        @BindView(R.id.course_to_process)
        ImageButton mToProcess;
        @BindView(R.id.course_author_avatar)
        ImageView mAuthorAvatar;
        @BindView(R.id.course_available_lessons)
        EntitledTextView mAvailableLessons;

        public CourseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            Course course = mCourses.get(position);

            mCourseName.setText(course.getCourseName());
            mAuthor.setText(course.getAuthor());
            mLikeCount.setText(String.valueOf(course.getLikesNumber()));
            mDescription.setText(course.getDescription());
            if (course.getLessons() != null)
                mAvailableLessons.setText(mAvailableLessons.getTitle() + course.getLessons().size());
            else
                mAvailableLessons.setText(mAvailableLessons.getTitle() + course.getAvailableLessons());

            boolean isLiked = course.isLiked();
            mLikeButton.setLiked(isLiked);

            mDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, CourseViewActivity.class);
                    intent.putExtra("courseId", course.getId());
                    mContext.startActivity(intent);

                }
            });
            mToProcess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mApiManager = ApiManager.getInstance();
                    mDataBaseManager = DataBaseManager.getInstance(mContext);
                    mApiManager.subscribe(mDataBaseManager.getCurrentToken(), mDataBaseManager.getCurrentUserId(), course.getId())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(subscribeResponse -> {
                                if (subscribeResponse.getStatus()==202){
                                    mToProcess.setImageResource(R.drawable.ic_lightbulb_full_yellow);
                                } else {
                                    //mView.showSnackbar(R.string.connection_error);
                                }
                            },throwable -> {
                                //mView.showSnackbar(R.string.server_error);
                                throwable.printStackTrace();
                            });

                }
            });
            if (mOnCourseClickListener != null) {


                mLikeButton.setOnClickListener(v -> {
                    mOnCourseClickListener.onLikeClick(course, isLiked);

                    Integer count = Integer.valueOf(mLikeCount.getText().toString());
                    count++;
                    mLikeCount.setText(String.valueOf(count));
                });

            }

            if (course.isMy()) {
                mToProcess.setVisibility(View.INVISIBLE);
            }

        }
    }


    public void setContext(Context context) {
        this.mContext = context;
    }

    public interface OnCourseClickListener {
        void onDetailsClick(Course course);

        void onLikeClick(Course course, boolean isLiked);

        void onToProcessClick(Course course);
    }


}
