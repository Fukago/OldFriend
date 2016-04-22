package com.example.apple.oldfriend.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.model.bean.Article;

import java.util.List;

/**
 * Created by apple on 16/4/22.
 */
public class ZoneAdapter extends RecyclerView.Adapter {

    private List<Article> mList;
    private Context context;

    public ZoneAdapter(List<Article> list, Context context) {
        mList = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zone_fragment_list, parent, false);
        return new ZoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ZoneViewHolder viewHolder= (ZoneViewHolder) holder;
        // todo 缺少用户头像上传
        mList.get(position).getArticlePic().loadImage(context,viewHolder.im_item_zone_picture);
        viewHolder.tv_item_zone_userName.setText(""+mList.get(position).getAuthor().getMyOldState().getName());
        viewHolder.tv_item_zone_description.setText(""+mList.get(position).getContent());
        viewHolder.tv_item_zone_message.setText(""+mList.get(position).getReadTimes());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class ZoneViewHolder extends RecyclerView.ViewHolder {
        private ImageView im_item_zone_face;
        private TextView tv_item_zone_userName;
        private TextView tv_item_zone_description;
        private ImageView im_item_zone_picture;
        private TextView tv_item_zone_message;
        private ImageView im_item_zone_good;
        private ImageView im_item_zone_send;
        public ZoneViewHolder(View view) {
            super(view);
            im_item_zone_face= (ImageView) view.findViewById(R.id.im_item_zone_face);
            tv_item_zone_userName = (TextView) view.findViewById(R.id.tv_item_zone_userName);
            tv_item_zone_description = (TextView) view.findViewById(R.id.tv_item_zone_description);
            im_item_zone_picture = (ImageView) view.findViewById(R.id.im_item_zone_picture);
            tv_item_zone_message = (TextView) view.findViewById(R.id.tv_item_zone_message);
            im_item_zone_good = (ImageView) view.findViewById(R.id.im_item_zone_good);
            im_item_zone_send= (ImageView) view.findViewById(R.id.im_item_zone_send);
        }
    }
}
