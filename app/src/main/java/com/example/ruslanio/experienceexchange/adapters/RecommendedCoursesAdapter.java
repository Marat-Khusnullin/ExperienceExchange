package com.example.ruslanio.experienceexchange.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ruslanio on 29.11.2017.
 */

public class RecommendedCoursesAdapter extends RecyclerView.Adapter<RecommendedCoursesAdapter.CourseViewHolder> {

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {
        public CourseViewHolder(View itemView) {
            super(itemView);
        }
    }
}
