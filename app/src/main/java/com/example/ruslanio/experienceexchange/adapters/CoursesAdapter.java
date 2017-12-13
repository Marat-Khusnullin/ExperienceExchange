package com.example.ruslanio.experienceexchange.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.model.Course;
import com.like.LikeButton;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ruslanio on 29.11.2017.
 */

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {

    private List<Course> mCourses = Collections.emptyList();
    private OnCourseClickListener mOnCourseClickListener;

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
        TextView mAvailableLessons;

        public CourseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(int position) {
            Course course = mCourses.get(position);

            mCourseName.setText(course.getCourseName());
            mAuthor.setText(course.getAuthor());
            mLikeCount.setText(String.valueOf(course.getLikesNumber()));
            mDescription.setText(course.getDescription());

            boolean isLiked = course.isLiked();
            mLikeButton.setLiked(isLiked);

            if (mOnCourseClickListener != null) {
                mToProcess.setOnClickListener(v -> mOnCourseClickListener.onToProcessClick(course));
                mDetails.setOnClickListener(v -> mOnCourseClickListener.onDetailsClick(course));
                mLikeButton.setOnClickListener(v -> {
                    mOnCourseClickListener.onLikeClick(course, isLiked);

                    Integer count = Integer.valueOf(mLikeCount.getText().toString());
                    count++;
                    mLikeCount.setText(String.valueOf(count));
                });

            }

        }
    }


    public interface OnCourseClickListener {
        void onDetailsClick(Course course);

        void onLikeClick(Course course, boolean isLiked);

        void onToProcessClick(Course course);
    }
}
