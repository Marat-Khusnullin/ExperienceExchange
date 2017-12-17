package com.example.ruslanio.experienceexchange.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ruslanio.experienceexchange.R;
import com.example.ruslanio.experienceexchange.database.model.Lesson;
import com.example.ruslanio.experienceexchange.utils.views.EntitledTextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ruslanio on 17.12.2017.
 */

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private List<Lesson> mLessons = Collections.emptyList();

    public void setLessons(List<Lesson> lessons) {
        mLessons = lessons;
        notifyDataSetChanged();
    }

    @Override
    public LessonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson,parent,false);
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

        @BindView(R.id.ibtn_lesson_item_close)
        ImageButton mClose;
        @BindView(R.id.tv_lesson_item_name)
        EntitledTextView mName;
        @BindView(R.id.tv_lesson_item_number)
        TextView mLessonCount;

        public LessonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


        void bind(int position){
            Lesson lesson = mLessons.get(position);
            mName.setText(lesson.getName());
            mLessonCount.setText(mLessonCount.getText().toString() + (position + 1));
            mClose.setOnClickListener(btn -> {
                mLessons.remove(position);
                notifyDataSetChanged();
            });

        }
    }
}
