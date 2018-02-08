package com.app.spacex;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.app.spacex.network.NetworkController;
import com.app.spacex.ui.BaseActivity;
import com.app.spacex.util.LogUtil;

/**
 * Created by sinansondas on 08/02/18.
 */

public class App extends Application {
    private final static String ERROR_MESSAGE_ILLEGAL_ACTIVITY_IMPLEMENTATION = "ALL ACTIVITIES MUST BE EXTENDED FROM BASE ACTIVITY";
    private NetworkController networkController;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeApplication();
    }

    private void initializeApplication() {
        networkController = new NetworkController(BuildConfig.BASE_URL, BuildConfig.ENABLE_NETWORK_LOGGING);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if (!activity.getClass().isAssignableFrom(BaseActivity.class))
                    LogUtil.log(ERROR_MESSAGE_ILLEGAL_ACTIVITY_IMPLEMENTATION);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    public NetworkController getNetworkController() {
        return networkController;
    }
}
