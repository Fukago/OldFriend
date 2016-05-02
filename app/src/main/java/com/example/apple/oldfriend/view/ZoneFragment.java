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
import android.widget.Toast;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.cofing.IGetArticleAndAuthor;
import com.example.apple.oldfriend.model.bean.Article;
import com.example.apple.oldfriend.presenter.FriendArticlePresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZoneFragment extends Fragment implements IGetArticleAndAuthor {
    private List<Article> mList = new ArrayList<>();
    private ZoneAdapter mAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private int lastVisibleItem = 4;
    private int totalItemCount = 6;
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
        presenter = new FriendArticlePresenter(getContext());
        presenter.getArticleAndAuthor(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zone, container, false);
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.fragment_zone_swipe_refresh_widget);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_zone);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new ZoneAdapter(mList, getContext(), this);
        recyclerView.setAdapter(mAdapter);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorGreen_32CD32, R.color.colorAccent_FF4081,
                R.color.colorOrange_E65100, R.color.colorPrimary_3F51B5);
        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getArticleAndAuthor(ZoneFragment.this);
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
                    presenter.getArticleAndAuthor(ZoneFragment.this);
                }
            }
        });
        return view;
    }



    @Override
    public void onGetArticleAndAuthor(List<Article> articleList) {
        Collections.reverse(articleList);
        mList.clear();
        mList.addAll(articleList);
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void onGetArticleAndAuthorError(String s) {
        Toast.makeText(getContext(), "数据加载失败,请检查您的网络环境~", Toast.LENGTH_SHORT).show();
        mSwipeRefreshWidget.setRefreshing(false);
    }
}
