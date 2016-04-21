package com.example.apple.oldfriend.app;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.apple.oldfriend.R;

/**
 * Created by apple on 16/4/21.
 */
public class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView imageView;

    public Toolbar getToolbar() {
        return toolbar;
    }

    protected void setToolBar(boolean returnAble) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(returnAble);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setToolBar(false);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        setToolBar(false);
    }


}
