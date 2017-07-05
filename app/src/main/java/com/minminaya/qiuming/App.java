package com.minminaya.qiuming;

import android.app.Application;

/**
 * Created by Niwa on 2017/7/5.
 */

public class App extends Application {
    private static App INSTANCE;

    public static App getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
