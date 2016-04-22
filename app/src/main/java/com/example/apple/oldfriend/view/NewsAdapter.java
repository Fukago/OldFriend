package com.example.apple.oldfriend.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.model.bean.NewsInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by apple on 16/4/21.
 */
public class NewsAdapter extends RecyclerView.Adapter {
    private List<NewsInfo> mList;
    private Context context;

    public NewsAdapter(List<NewsInfo> list, Context context) {
        mList = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_fragment_list, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        NewsViewHolder viewHolder = (NewsViewHolder) holder;
        viewHolder.tv_item_news_tittle.setText("" + mList.get(position).getTitle());
        viewHolder.tv_item_news_context.setText("   " + Html.fromHtml(mList.get(position).getDescription()));
        viewHolder.tv_item_news_keywords.setText("关键字:" + mList.get(position).getKeywords());
        String url = "" + mList.get(position).getImg();
        Picasso.with(context)
                .load(url)
                .resize(100, 60)
                .placeholder(R.drawable.picasso_ic_loading)
                .error(R.drawable.picasso_ic_loadingerror)
                .into(viewHolder.im_item_news_image);
        viewHolder.cd_item_news_clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, NewsInfoActivity.class);
                it.putExtra("tittle", "" + mList.get(position).getTitle());
                it.putExtra("time",""+mList.get(position).getTime());
                it.putExtra("contant",""+mList.get(position).getMessage());
                context.startActivity(it);
            }
        });
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
        private CardView cd_item_news_clicked;

        public NewsViewHolder(View view) {
            super(view);
            tv_item_news_tittle = (TextView) view.findViewById(R.id.tv_item_news_tittle);
            tv_item_news_context = (TextView) view.findViewById(R.id.tv_item_news_context);
            tv_item_news_keywords = (TextView) view.findViewById(R.id.tv_item_news_keywords);
            im_item_news_image = (ImageView) view.findViewById(R.id.im_item_news_image);
            cd_item_news_clicked = (CardView) view.findViewById(R.id.cd_item_news_clicked);
        }

    }
}
