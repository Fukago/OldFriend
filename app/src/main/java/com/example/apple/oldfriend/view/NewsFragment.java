package com.example.apple.oldfriend.view;

import android.content.Context;
import android.net.Uri;
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

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.model.IGetHealthNews;
import com.example.apple.oldfriend.model.bean.NewsInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class NewsFragment extends Fragment implements IGetHealthNews{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<NewsInfo> mList = new ArrayList<>();
    private NewsAdapter mAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private String mParam1;
    private String mParam2;
    private int lastVisibleItem;
    private int totalItemCount;
    private OnFragmentInteractionListener mListener;

    public NewsFragment() {

    }


    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.fragment_news_swipe_refresh_widget);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_news);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new NewsAdapter(mList, this);
        recyclerView.setAdapter(mAdapter);
        initData();
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

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onHealthNewsSuccess(List<NewsInfo> tngou) {
        mList.clear();
        mList.addAll(tngou);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHealthNewsError(Throwable e) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
