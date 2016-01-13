package com.testphase.materialtest;

import android.app.Application;
import android.content.Context;

/**
 * Created by deea on 07/01/16.
 */
public class MyApplication extends Application {

    private static MyApplication sInstance;
    public static final String API_KEY_RT = "54wzfswsa4qmjg8hjwa64d4c";

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
}
