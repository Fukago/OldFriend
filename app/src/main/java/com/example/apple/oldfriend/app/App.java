package com.example.apple.oldfriend.app;

import android.app.Application;

/**
 * Created by apple on 16/4/20.
 */
public class App extends Application{
    private static App instance;

    public static App getIntance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
