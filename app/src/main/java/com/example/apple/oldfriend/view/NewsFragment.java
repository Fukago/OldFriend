package com.example.apple.oldfriend.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.model.IGetHealthNews;
import com.example.apple.oldfriend.model.bean.NewsInfo;
import com.example.apple.oldfriend.presenter.HealthNewsPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;


public class NewsFragment extends Fragment implements IGetHealthNews {

    private List<NewsInfo> mList = new ArrayList<>();
    private NewsAdapter mAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private int lastVisibleItem=4;
    private int totalItemCount=6;
    private HealthNewsPresenter presenter;

    public NewsFragment() {

    }


    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new HealthNewsPresenter(this);
        presenter.getNewsList(0,0,totalItemCount);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.fragment_news_swipe_refresh_widget);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_news);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new NewsAdapter(mList,getContext());
        recyclerView.setAdapter(mAdapter);
        //initData();
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorGreen_32CD32, R.color.colorAccent_FF4081,
                R.color.colorOrange_E65100, R.color.colorPrimary_3F51B5);
        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               /* initData();*/
                presenter.getNewsList(0,0,totalItemCount);
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
                lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                totalItemCount = layoutManager.getItemCount();
                if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
                    presenter.getNewsList(0,0,totalItemCount+8);
                }
            }
        });
        return view;
    }

    private void initData() {
        BmobQuery<NewsInfo> query = new BmobQuery<>();
        query.include("author");
        query.findObjects(getActivity(), new FindListener<NewsInfo>() {
            @Override
            public void onSuccess(List<NewsInfo> list) {
                Collections.reverse(list);
                mList.clear();
                mList.addAll(list);
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshWidget.setRefreshing(false);
                Log.d("TAG1", mList.get(1).getMessage());
            }

            @Override
            public void onError(int i, String s) {
                mSwipeRefreshWidget.setRefreshing(false);
            }
        });
    }


    @Override
    public void onHealthNewsSuccess(List<NewsInfo> tngou) {
        mList.clear();
        mList.addAll(tngou);
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void onHealthNewsError(Throwable e) {
        Toast.makeText(getContext(), "数据加载失败,请检查您的网络环境~", Toast.LENGTH_SHORT).show();
    }
}
