package com.example.apple.oldfriend.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.model.bean.NewsInfo;

import java.util.List;

/**
 * Created by apple on 16/4/21.
 */
public class NewsAdapter extends RecyclerView.Adapter {
    private List<NewsInfo> mList;
    private NewsFragment mFragment;

    public NewsAdapter(List<NewsInfo> list, NewsFragment mFragment) {
        mList = list;
        this.mFragment = mFragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_fragment_list, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class NewsViewHolder extends RecyclerView.ViewHolder {

        public NewsViewHolder(View view) {
            super(view);
        }

    }
}
