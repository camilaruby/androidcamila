package com.example.logonrm.androidcamila;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by camila on 09/09/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        Stetho.initializeWithDefaults(this);
    }
}
