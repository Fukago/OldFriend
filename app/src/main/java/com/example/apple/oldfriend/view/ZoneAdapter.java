package com.example.apple.oldfriend.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.model.bean.Article;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.io.InputStream;
import java.util.List;

/**
 * Created by apple on 16/4/22.
 */
public class ZoneAdapter extends RecyclerView.Adapter {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_CELL = 1;
    private List<Article> mList;
    private Context context;
    private ZoneFragment mFragment;

    public ZoneAdapter(List<Article> list, Context context, ZoneFragment mFragment) {
        mList = list;
        this.context = context;
        this.mFragment = mFragment;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_big_zone_frament_list, parent,
                        false);
                return new RollViewHolder(view);
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_small_zone_fragment_list,
                        parent, false);
                return new ZoneViewHolder(view);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER: {
                RollViewHolder viewHolder = (RollViewHolder) holder;
                viewHolder.mRollViewPager.setAdapter(new RollAdapter(mFragment));
                break;
            }
            case TYPE_CELL: {
                final ZoneViewHolder viewHolder = (ZoneViewHolder) holder;
                // todo 缺少用户头像上传
                if (mList.get(position - 1).getArticlePic() != null) {
                    mList.get(position - 1).getArticlePic().loadImage(context, viewHolder.im_item_zone_picture);
                }
                viewHolder.tv_item_zone_userName.setText("" + mList.get(position - 1).getAuthor().getMyOldState()
                        .getName());
                viewHolder.tv_item_zone_description.setText("" + mList.get(position - 1).getContent());
                viewHolder.tv_item_zone_message.setText("" + mList.get(position - 1).getReadTimes());
            }
        }

    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
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
            im_item_zone_face = (ImageView) view.findViewById(R.id.im_item_zone_face);
            tv_item_zone_userName = (TextView) view.findViewById(R.id.tv_item_zone_userName);
            tv_item_zone_description = (TextView) view.findViewById(R.id.tv_item_zone_description);
            im_item_zone_picture = (ImageView) view.findViewById(R.id.im_item_zone_picture);
            tv_item_zone_message = (TextView) view.findViewById(R.id.tv_item_zone_message);
            im_item_zone_good = (ImageView) view.findViewById(R.id.im_item_zone_good);
            im_item_zone_send = (ImageView) view.findViewById(R.id.im_item_zone_send);
        }
    }

    public static class RollViewHolder extends RecyclerView.ViewHolder {
        private RollPagerView mRollViewPager;

        public RollViewHolder(View view) {
            super(view);
            mRollViewPager = (RollPagerView) view.findViewById(R.id.item_big_zone_roll_viewpager);
        }
    }

    class RollAdapter extends StaticPagerAdapter {
        private ZoneFragment mFragment;

        public RollAdapter(ZoneFragment mFragment) {
            this.mFragment = mFragment;
        }

        private int[] imgs = {
                R.drawable.banner_item1,
                R.drawable.banner_item2,
                R.drawable.banner_item3,
                R.drawable.banner_item4,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            InputStream is = mFragment.getActivity().getResources().openRawResource(imgs[position]);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            Bitmap btp = BitmapFactory.decodeStream(is, null, options);
            view.setImageBitmap(btp);
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }
}

