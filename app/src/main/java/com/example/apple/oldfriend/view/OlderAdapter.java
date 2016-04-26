package com.example.apple.oldfriend.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.model.bean.OldState;

import java.util.List;

/**
 * Created by apple on 16/4/26.
 */
public class OlderAdapter extends RecyclerView.Adapter{
    private List<OldState> mList;
    private Context context;

    public OlderAdapter (List<OldState> list, Context context){
        mList = list;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_older_fragment_list, parent, false);
        return new OlderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OlderViewHolder viewHolder= (OlderViewHolder) holder;

    }

    @Override
    public int getItemCount() {
        return  mList.size();
    }

    private class OlderViewHolder extends RecyclerView.ViewHolder {
        private ImageView im_item_older_face;
        private TextView tv_item_odler_name;
        private TextView tv_item_older_situation;
        private TextView tv_item_older_context;
        public OlderViewHolder(View view) {
            super(view);
            im_item_older_face= (ImageView) view.findViewById(R.id.im_item_older_face);
            tv_item_odler_name= (TextView) view.findViewById(R.id.tv_item_odler_name);
            tv_item_older_situation= (TextView) view.findViewById(R.id.tv_item_older_situation);
            tv_item_older_context= (TextView) view.findViewById(R.id.tv_item_older_context);
        }
    }
}
