package xyz.yhsj.yhui.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by LOVE on 2015/6/14.
 */
public class App_Application extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

    }


    public static Context getContext() {
        return context;
    }
}
