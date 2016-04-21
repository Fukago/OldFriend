package com.example.apple.oldfriend.cofing.app;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by apple on 16/4/20.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化 Bmob SDK
        // 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
        Bmob.initialize(this, "171a10fa5deb6f5752db8feeb39b8f3a");
    }
}
