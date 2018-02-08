package com.app.spacex.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.app.spacex.R;
import com.app.spacex.model.Launch;
import com.app.spacex.util.ToastUtil;

import java.io.Serializable;

/**
 * Created by sinansondas on 09/02/18.
 */

public class DetailActivity extends BaseActivity {
    public final static String INTENT_EXTRA_KEY_LAUNCH = "LAUNCH";

    private Launch launch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle state = null;
        if (savedInstanceState != null)
            state = savedInstanceState;
        else if (getIntent() != null)
            state = getIntent().getExtras();

        if (state != null)
            initializeResources(state);

        if (launch == null) {
            ToastUtil.showMessage(this, "MODEL EMPTY");
            finish();
        } else {
            initializeViews();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(INTENT_EXTRA_KEY_LAUNCH, launch);
    }

    private void initializeResources(Bundle bundle) {
        Serializable serializable = bundle.getSerializable(INTENT_EXTRA_KEY_LAUNCH);
        if (serializable != null)
            launch = (Launch) serializable;
    }

    private void initializeViews() {
        TextView textViewDetail = findViewById(R.id.tv_launch_details);
        textViewDetail.setText(launch.getDetails());
    }
}
