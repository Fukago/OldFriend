package com.example.apple.oldfriend.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.util.TimeUtil;

public class NewsInfoActivity extends AppCompatActivity {
    private TextView tv_tittle_newsInfo_activity;
    private TextView tv_time_newsInfo_activity;
    private TextView tv_contant_newsInfo_activity;
    private String tittle;
    private String time;
    private String contant;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        initData();
        initToolbar();
        initView();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.news_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        ImageView im_Userface = (ImageView) findViewById(R.id.im_back_toobar);
        im_Userface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        Intent it =getIntent();
        tittle=it.getStringExtra("tittle");
        time=it.getStringExtra("time");
        contant=it.getStringExtra("contant");
    }

    private void initView() {
        tv_contant_newsInfo_activity = (TextView) findViewById(R.id.tv_contant_newsInfo_activity);
        tv_tittle_newsInfo_activity= (TextView) findViewById(R.id.tv_tittle_newsInnfo_activity);
        tv_tittle_newsInfo_activity.setText(tittle);
        tv_time_newsInfo_activity= (TextView) findViewById(R.id.tv_time_newsInfo_activity);
        tv_contant_newsInfo_activity.setText("      "+Html.fromHtml(contant));
        tv_time_newsInfo_activity.setText(TimeUtil.getDateToString(Long.decode(time)));
    }
}
