package com.example.apple.oldfriend.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.apple.oldfriend.R;
import com.example.apple.oldfriend.model.bean.User;

import cn.bmob.v3.BmobUser;

/**
 * Created by apple on 16/4/21.
 */
public class WelcomeView extends AppCompatActivity {
    private final int SPLASH_DELAY_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                User userInfo = BmobUser.getCurrentUser(WelcomeView.this, User.class);
                if (userInfo != null) {
                    Intent intent = new Intent(WelcomeView.this, LoginActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isOld", userInfo.getOld());
                    intent.putExtras(bundle);
                    WelcomeView.this.startActivity(intent);
                    WelcomeView.this.finish();
                } else {
                    WelcomeView.this.startActivity(new Intent(WelcomeView.this, LoginActivity.class));
                    WelcomeView.this.finish();
                }

            }
        }, SPLASH_DELAY_TIME);
    }
}
