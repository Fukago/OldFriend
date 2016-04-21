package com.example.apple.oldfriend.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.app.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initDrawer();
        iniView();
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        ImageView im_Userface = (ImageView) findViewById(R.id.im_userFace);
        im_Userface.setOnClickListener(this);
        TextView im_Tittle = (TextView) findViewById(R.id.tv_tittle);
    }

    private void initDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        ImageView drawer_im_userface = (ImageView) findViewById(R.id.drawer_im_userFace);
        drawer_im_userface.setOnClickListener(this);
        TextView drawer_tv_userName = (TextView) findViewById(R.id.drawer_tv_userName);
        LinearLayout ll_message = (LinearLayout) findViewById(R.id.drawer_ll_message);
        ll_message.setOnClickListener(this);
        LinearLayout ll_sitiation = (LinearLayout) findViewById(R.id.drawer_ll_situation);
        ll_sitiation.setOnClickListener(this);
        LinearLayout ll_aboutas = (LinearLayout) findViewById(R.id.drawer_ll_aboutAs);
        ll_aboutas.setOnClickListener(this);
        LinearLayout ll_exit = (LinearLayout) findViewById(R.id.drawer_ll_exit);
        ll_exit.setOnClickListener(this);
    }

    private void iniView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        final SampleFragmentPagerAdapter pagerAdapter =
                new SampleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(pagerAdapter.getTabView(i));
            }
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("Tabposition",""+tab.getPosition());
                tab.setCustomView(null);
                tab.setCustomView(pagerAdapter.getClickedTabView(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
                tab.setCustomView(pagerAdapter.getTabView(tab.getPosition()));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.setCurrentItem(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_userFace: {
                mDrawerLayout.openDrawer(Gravity.START);
                break;
            }
            case R.id.drawer_im_userFace: {
                Toast.makeText(MainActivity.this, "userFace", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.drawer_ll_message: {
                Toast.makeText(MainActivity.this, "message", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.drawer_ll_situation: {
                Toast.makeText(MainActivity.this, "situation", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.drawer_ll_aboutAs: {
                Toast.makeText(MainActivity.this, "aboutAs", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.drawer_ll_exit: {
                Toast.makeText(MainActivity.this, "exit", Toast.LENGTH_SHORT).show();
                break;
            }
            default: {
                Toast.makeText(MainActivity.this, "点击事件有误", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 3;
        private String tabTitles[] = new String[]{"信息库", "老友圈", "资讯栏"};
        private int imageResId[] = new int[]{
                R.drawable.tab_ic_message_unclick,
                R.drawable.tab_ic_friend_unclick,
                R.drawable.tab_ic_news_unclick
        };
        private int imageResIdClicked[] = new int[]{
                R.drawable.tab_ic_message_click,
                R.drawable.tab_ic_friend_click,
                R.drawable.tab_ic_news_click
        };
        private Context context;

        public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }


        public View getTabView(int position) {
            View v = LayoutInflater.from(context).inflate(R.layout.view_tablayout, null);
            TextView tv = (TextView) v.findViewById(R.id.tv_tab);
            tv.setText(tabTitles[position]);
            ImageView img = (ImageView) v.findViewById(R.id.im_tab);
            img.setImageResource(imageResId[position]);
            return v;
        }

        public View getClickedTabView(int position) {
            View v = LayoutInflater.from(context).inflate(R.layout.view_tablayout, null);
            TextView tv = (TextView) v.findViewById(R.id.tv_tab);
            tv.setText(tabTitles[position]);
            tv.setTextColor(R.color.colorPink_ff4081);
            ImageView img = (ImageView) v.findViewById(R.id.im_tab);
            img.setImageResource(imageResIdClicked[position]);
            return v;
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: {
                    return MessageFragment.newInstance("MessageFragment", "MessageFragment");
                }
                case 1: {
                    return MessageFragment.newInstance("MessageFragment", "MessageFragment");
                }
                case 2: {
                    return NewsFragment.newInstance("NewsFragment", "NewsFragment");
                }
                default: {
                    return MessageFragment.newInstance("MessageFragment", "MessageFragment");
                }
            }
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

}

