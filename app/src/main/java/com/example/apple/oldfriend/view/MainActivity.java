package com.example.apple.oldfriend.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
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

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.app.BaseActivity;
import com.example.apple.oldfriend.presenter.UserManagePresenter;
import com.example.apple.oldfriend.util.CircleTransform;
import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;

public class MainActivity extends BaseActivity implements View.OnClickListener, OnImageSelectListener {
    private Toolbar toolbar;
    private ImageView im_more_toolbar;
    private DrawerLayout mDrawerLayout;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageProvider provider;
    private MaterialDialog dialog;
    private Bitmap bitmap;
    private ImageView drawer_im_userface;
    private ImageView im_userface_toolbar;
    private boolean isOld = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        provider = new ImageProvider(this);
        initData();
        initToolbar();
        initDrawer();
        iniView();
    }

    private void initData() {
        Intent it = getIntent();
        isOld = it.getBooleanExtra("isOld", isOld);
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        im_userface_toolbar = (ImageView) findViewById(R.id.im_userFace_toolbar);
        TextView im_tittle_toolbar = (TextView) findViewById(R.id.tv_tittle_toolbar);
        im_more_toolbar = (ImageView) findViewById(R.id.im_more_toolbar);
        im_userface_toolbar.setOnClickListener(this);
        im_more_toolbar.setOnClickListener(this);
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
        drawer_im_userface = (ImageView) findViewById(R.id.drawer_im_userFace);
        TextView drawer_tv_userName = (TextView) findViewById(R.id.drawer_tv_userName);
        LinearLayout ll_message = (LinearLayout) findViewById(R.id.drawer_ll_message);
        ll_message.setOnClickListener(this);
        LinearLayout ll_sitiation = (LinearLayout) findViewById(R.id.drawer_ll_situation);
        ll_sitiation.setOnClickListener(this);
        LinearLayout ll_aboutas = (LinearLayout) findViewById(R.id.drawer_ll_aboutAs);
        ll_aboutas.setOnClickListener(this);
        LinearLayout ll_exit = (LinearLayout) findViewById(R.id.drawer_ll_exit);
        ll_exit.setOnClickListener(this);
        drawer_im_userface.setOnClickListener(this);
    }

    private void iniView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        final SampleFragmentPagerAdapter pagerAdapter =
                new SampleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(pagerAdapter.getTabView(i));
            }
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setCustomView(null);
                tab.setCustomView(pagerAdapter.getClickedTabView(tab.getPosition()));
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0: {
                        im_more_toolbar.setVisibility(View.GONE);
                        break;
                    }
                    case 1: {
                        im_more_toolbar.setVisibility(View.VISIBLE);
                        break;
                    }
                    case 2: {
                        im_more_toolbar.setVisibility(View.GONE);
                        break;
                    }
                    default: {
                        im_more_toolbar.setVisibility(View.GONE);
                        break;
                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
                tab.setCustomView(pagerAdapter.getTabView(tab.getPosition()));

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
                tab.setCustomView(pagerAdapter.getTabView(tab.getPosition()));

            }
        });
        viewPager.setCurrentItem(2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_userFace_toolbar: {
                mDrawerLayout.openDrawer(Gravity.START);
                break;
            }
            case R.id.im_more_toolbar: {
                Intent it = new Intent(MainActivity.this, SendZoneActivity.class);
                startActivity(it);
                break;
            }
            case R.id.drawer_im_userFace: {
                Toast.makeText(MainActivity.this, "userFace", Toast.LENGTH_SHORT).show();
                new MaterialDialog.Builder(MainActivity.this)
                        .title("选择图片来源")
                        .items(new String[]{"相机", "相册", "网络"})
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                switch (which) {
                                    case 0: {
                                        provider.getImageFromCamera(MainActivity.this);
                                        break;
                                    }
                                    case 1: {
                                        provider.getImageFromAlbum(MainActivity.this);
                                        break;
                                    }
                                    case 2: {
                                        provider.getImageFromNet(MainActivity.this);
                                        break;
                                    }
                                }

                            }
                        }).show();
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
                UserManagePresenter managePresenter = new UserManagePresenter(MainActivity.this);
                managePresenter.exitLogin();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                Toast.makeText(MainActivity.this, "exit", Toast.LENGTH_SHORT).show();
                break;
            }
            default: {
                Toast.makeText(MainActivity.this, "点击事件有误", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        provider.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onImageSelect() {
        dialog = new MaterialDialog.Builder(MainActivity.this)
                .progress(true, 100)
                .title("加载中")
                .content("请稍候")
                .cancelable(false)
                .show();
    }

    @Override
    public void onImageLoaded(Uri uri) {
        dialog.dismiss();
        addImage(uri);
        provider.corpImage(uri, 200, 200, new OnImageSelectListener() {
            @Override
            public void onImageSelect() {

            }

            @Override
            public void onImageLoaded(Uri uric) {
                Log.d("changeImage", "" + uric);
                addImage(uric);
            }

            @Override
            public void onError() {
                Toast.makeText(MainActivity.this, "更改头像失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onError() {
        dialog.dismiss();
        Toast.makeText(MainActivity.this, "更改头像失败", Toast.LENGTH_SHORT).show();
    }


    public void addImage(Uri uri) {
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (bitmap != null) {
            Picasso.with(MainActivity.this)
                    .load(uri)
                    .resize(136, 136)
                    .centerCrop()
                    .transform(new CircleTransform())
                    .placeholder(R.drawable.user_ic_face)
                    .error(R.drawable.user_ic_face)
                    .into(drawer_im_userface);
            Picasso.with(MainActivity.this)
                    .load(uri)
                    .resize(136, 136)
                    .centerCrop()
                    .transform(new CircleTransform())
                    .into(im_userface_toolbar);
        }
    }

    private class SampleFragmentPagerAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {
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
                    if (!isOld) {
                        return OlderFragment.newInstance("OlderFragment", "OlderFragment");
                    } else {
                        return NurseFragment.newInstance("NurseFragment", "NurseFragment");
                    }
                }
                case 1: {
                    return ZoneFragment.newInstance("ZoneFragment", "ZoneFragment");
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

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0: {
                    im_more_toolbar.setVisibility(View.GONE);
                    break;
                }
                case 1: {
                    im_more_toolbar.setVisibility(View.VISIBLE);
                    break;
                }
                case 2: {
                    im_more_toolbar.setVisibility(View.GONE);
                    break;
                }
                default: {
                    im_more_toolbar.setVisibility(View.GONE);
                    break;
                }
            }
        }


        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}

