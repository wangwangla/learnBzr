package kw.learn.androidbezier.application;

import android.app.Application;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/5 7:44
 */
public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = null;
        instance = this;
    }
}
