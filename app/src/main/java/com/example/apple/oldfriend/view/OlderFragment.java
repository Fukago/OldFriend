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
import com.example.apple.oldfriend.cofing.IGetBothMessage;
import com.example.apple.oldfriend.model.bean.User;
import com.example.apple.oldfriend.presenter.BothMessagePresenter;

import java.util.ArrayList;
import java.util.List;

public class OlderFragment extends Fragment implements IGetBothMessage {

    private List<User> oldList = new ArrayList<>();
    private OlderAdapter mAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private int lastVisibleItem = 4;
    private int totalItemCount = 6;
    private BothMessagePresenter presenter;

    public OlderFragment() {

    }

    public static OlderFragment newInstance(String param1, String param2) {
        OlderFragment fragment = new OlderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new BothMessagePresenter(getContext());
        presenter.getAllOldMessage(OlderFragment.this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_older, container, false);
        mSwipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.fragment_olders_swipe_refresh_widget);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_olders);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new OlderAdapter(oldList, getContext());
        recyclerView.setAdapter(mAdapter);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorGreen_32CD32, R.color.colorAccent_FF4081,
                R.color.colorOrange_E65100, R.color.colorPrimary_3F51B5);
        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getAllOldMessage(OlderFragment.this);
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
                    presenter.getAllOldMessage(OlderFragment.this);

                }
            }
        });
        return view;

    }


    @Override
    public void getNurseMessage(User nurse) {

    }

    @Override
    public void getAllNurseMessage(List<User> allNurseList) {

    }

    @Override
    public void getOldMessage(List<User> oldList) {

    }

    @Override
    public void getAllOldMessage(List<User> allOldList) {
        oldList.clear();
        oldList.addAll(allOldList);
        mAdapter.notifyDataSetChanged();
    }
}
