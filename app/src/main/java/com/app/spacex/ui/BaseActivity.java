package com.app.spacex.ui;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.spacex.App;
import com.app.spacex.network.NetworkController;

/**
 * Created by sinansondas on 08/02/18.
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    protected NetworkController networkController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        networkController = getNetworkController();
    }

    private NetworkController getNetworkController() {
        Application application = getApplication();
        if (application != null && application instanceof App) {
            App app = (App) application;

            return app.getNetworkController();
        }
        return null;
    }
}