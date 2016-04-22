package com.example.apple.oldfriend.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.model.bean.Article;
import com.example.apple.oldfriend.presenter.FriendArticlePresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class ZoneFragment extends Fragment {
    private List<Article> mList=new ArrayList<>();
    private ZoneAdapter mAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private int lastVisibleItem;
    private int totalItemCount;
    private FriendArticlePresenter presenter;

    public ZoneFragment() {

    }

    public static ZoneFragment newInstance(String param1, String param2) {
        ZoneFragment fragment = new ZoneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new FriendArticlePresenter(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.fragment_news_swipe_refresh_widget);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_news);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ZoneAdapter(mList,getContext());
        recyclerView.setAdapter(mAdapter);
        //initData();
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorGreen_32CD32, R.color.colorAccent_FF4081,
                R.color.colorOrange_E65100, R.color.colorPrimary_3F51B5);
        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();

            }
        });
        mSwipeRefreshWidget.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    mSwipeRefreshWidget.setRefreshing(true);

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
          /*      lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                totalItemCount = layoutManager.getItemCount();
                if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
                }
         */
            }
        });
        return view;
    }

    private void initData() {
        BmobQuery<Article> query = new BmobQuery<>();
        query.include("author");
        query.findObjects(getActivity(), new FindListener<Article>() {
            @Override
            public void onSuccess(List<Article> list) {
                Collections.reverse(list);
                mList.clear();
                mList.addAll(list);
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshWidget.setRefreshing(false);
            }

            @Override
            public void onError(int i, String s) {
                mSwipeRefreshWidget.setRefreshing(false);
            }
        });
    }

}
