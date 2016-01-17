package com.testphase.materialtest;

import android.app.Application;
import android.content.Context;

import com.testphase.materialtest.database.ProductDatabase;

/**
 * Created by deea on 07/01/16.
 */
public class MyApplication extends Application {

    private static MyApplication sInstance;

    private static ProductDatabase mDatabase;

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

    public synchronized static ProductDatabase getWritableDatabase(){
        if(mDatabase == null){
            mDatabase = new ProductDatabase(getAppContext());
        }
        return mDatabase;
    }
}
