package com.example.poli_mobile.poli_mobile.utilidades;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

public class AppContext extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        AppContext.context = context;
    }

    public static ActivityManager getAppManager() {
        ActivityManager activityManager = (ActivityManager) getContext()
                .getSystemService(ACTIVITY_SERVICE);
        return activityManager;
    }

}
