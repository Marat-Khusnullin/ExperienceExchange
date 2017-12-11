package com.example.ruslanio.experienceexchange.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruslanio.experienceexchange.R;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ruslanio on 29.11.2017.
 */

public class PopularThemesAdapter extends RecyclerView.Adapter<PopularThemesAdapter.ThemeViewHolder> {

    private List<String> mThemes = Collections.emptyList();

    public void setThemes(List<String> themes) {
        mThemes = themes;
        notifyDataSetChanged();
    }

    @Override
    public ThemeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_theme,parent,false);
        return new ThemeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ThemeViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mThemes.size();
    }

    class ThemeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_popular_theme)
        TextView mThemeName;

        public ThemeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(int position){
            mThemeName.setText(mThemes.get(position));
        }
    }
}
