package com.example.ruslanio.experienceexchange.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.model.Lesson;
import com.example.ruslanio.experienceexchange.database.model.LessonTrue;
import com.example.ruslanio.experienceexchange.database.model.temporary.TempLesson;
import com.example.ruslanio.experienceexchange.utils.views.EntitledTextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Марат on 22.05.2018.
 */

public class LessonsAdapter extends RecyclerView.Adapter<LessonsAdapter.LessonViewHolder> {

    private List<LessonTrue> mLessons = Collections.emptyList();

    public void setLessons(List<LessonTrue> lessons) {
        mLessons = lessons;
        notifyDataSetChanged();
    }

    @Override
    public LessonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_lesson,parent,false);
        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LessonViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mLessons.size();
    }

    class LessonViewHolder extends RecyclerView.ViewHolder {
        EntitledTextView mName;


        public LessonViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tv_lesson_name);
        }


        void bind(int position){
            LessonTrue lesson = mLessons.get(position);
            mName.setText(lesson.getName());

        }
    }


}
