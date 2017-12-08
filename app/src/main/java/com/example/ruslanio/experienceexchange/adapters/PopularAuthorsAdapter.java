package com.example.ruslanio.experienceexchange.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
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

public class PopularAuthorsAdapter extends RecyclerView.Adapter<PopularAuthorsAdapter.AuthorViewHolder> {

    private List<String> mAuthors = Collections.emptyList();

    public void setAuthors(List<String> authors) {
        mAuthors = authors;
        notifyDataSetChanged();
    }

    @Override
    public AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_author,parent,false);
        return new AuthorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AuthorViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mAuthors.size();
    }

    class AuthorViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_author_name)
        TextView mAuthorName;

        public AuthorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        void  bind(int position){
            mAuthorName.setText(mAuthors.get(position));
        }
    }
}
