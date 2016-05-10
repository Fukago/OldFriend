package com.example.apple.oldfriend.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.model.bean.User;
import com.example.apple.oldfriend.util.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by apple on 16/4/26.
 */
public class OlderAdapter extends RecyclerView.Adapter {
    private List<User> mList;
    private Context context;

    public OlderAdapter(List<User> list, Context context) {
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        OlderViewHolder viewHolder = (OlderViewHolder) holder;
        viewHolder.im_item_older_face.setImageResource(R.drawable.user_ic_face);
        if (mList.get(position).getHeadPic() != null) {
            String url = "" + mList.get(position).getHeadPic().getFileUrl(context);
            Picasso.with(context)
                    .load(url)
                    .resize(96, 96)
                    .transform(new CircleTransform())
                    .placeholder(R.drawable.picasso_ic_loading)
                    .error(R.drawable.picasso_ic_loadingerror)
                    .into(viewHolder.im_item_older_face);
        }

        viewHolder.tv_item_odler_name.setText(mList.get(position).getMyOldState().getName());
        viewHolder.tv_item_older_context.setText(mList.get(position).getMyOldState().getBriefState());
        viewHolder.ll_item_older.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, OlderActivity.class);
                it.putExtra("name(良好)", "" + mList.get(position).getMyOldState().getName());
                it.putExtra("context", "" + mList.get(position).getMyOldState().getBriefState());
                it.putExtra("age", "" + mList.get(position).getMyOldState().getAge());

                it.putExtra("old", mList.get(position));

                context.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class OlderViewHolder extends RecyclerView.ViewHolder {
        private ImageView im_item_older_face;
        private TextView tv_item_odler_name;
        private TextView tv_item_older_situation;
        private TextView tv_item_older_context;
        private LinearLayout ll_item_older;

        public OlderViewHolder(View view) {
            super(view);
            ll_item_older = (LinearLayout) view.findViewById(R.id.ll_item_older);
            im_item_older_face = (ImageView) view.findViewById(R.id.im_item_older_face);
            tv_item_odler_name = (TextView) view.findViewById(R.id.tv_item_odler_name);
            tv_item_older_situation = (TextView) view.findViewById(R.id.tv_item_older_situation);
            tv_item_older_context = (TextView) view.findViewById(R.id.tv_item_older_context);


        }
    }
}
