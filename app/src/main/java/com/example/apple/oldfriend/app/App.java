package com.example.apple.oldfriend.app;

import android.app.Application;
import android.util.Log;

import cn.bmob.v3.Bmob;

/**
 * Created by apple on 16/4/20.
 */
public class App extends Application {
    private static App instance;

    public static App getIntance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "171a10fa5deb6f5752db8feeb39b8f3a");
    }
}
