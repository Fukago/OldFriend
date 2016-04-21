package com.example.apple.oldfriend.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        NewsViewHolder viewHolder = (NewsViewHolder) holder;
        viewHolder.tv_item_news_tittle.setText(""+mList.get(position).getTitle());
        viewHolder.tv_item_news_context.setText(""+mList.get(position).getMessage());
        viewHolder.tv_item_news_keywords.setText(""+mList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_item_news_tittle;
        private TextView tv_item_news_context;
        private TextView tv_item_news_keywords;
        private ImageView im_item_news_image;
        public NewsViewHolder(View view) {
            super(view);
            tv_item_news_tittle= (TextView) view.findViewById(R.id.tv_item_news_tittle);
            tv_item_news_context= (TextView) view.findViewById(R.id.tv_item_news_context);
            tv_item_news_keywords= (TextView) view.findViewById(R.id.tv_item_news_keywords);
            im_item_news_image= (ImageView) view.findViewById(R.id.im_item_news_image);
        }

    }
}
