package com.app.spacex.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.spacex.R;
import com.app.spacex.model.Launch;
import com.app.spacex.network.RequestListener;
import com.app.spacex.ui.adapter.LaunchListAdapter;
import com.app.spacex.ui.adapter.LaunchListListener;
import com.app.spacex.util.ToastUtil;

import java.util.List;

public class MainActivity extends BaseActivity implements LaunchListListener {
    private String refreshMessageLaunches;

    private SwipeRefreshLayout swipeRefreshLayoutLaunches;

    private LaunchListAdapter launchListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeActivity();
    }

    @Override
    protected void onStart() {
        super.onStart();
        sendLoadLaunchesRequest();
    }

    @Override
    public void onLaunchClicked(Launch launch) {
        Intent intentDetail = new Intent(this, DetailActivity.class);
        intentDetail.putExtra(DetailActivity.INTENT_EXTRA_KEY_LAUNCH, launch);
        startActivity(intentDetail);
    }

    //region INITIALIZATION
    private void initializeActivity() {
        initializeResources();
        initializeViews();

        swipeRefreshLayoutLaunches.setRefreshing(true);
    }

    private void initializeResources() {
        refreshMessageLaunches = getString(R.string.refresh_message_launches);
    }

    private void initializeViews() {
        swipeRefreshLayoutLaunches = findViewById(R.id.srl_launches);
        swipeRefreshLayoutLaunches.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sendLoadLaunchesRequest();
            }
        });

        launchListAdapter = new LaunchListAdapter(this);

        RecyclerView recyclerViewLaunches = findViewById(R.id.rv_launches);
        recyclerViewLaunches.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewLaunches.setItemAnimator(new DefaultItemAnimator());
        recyclerViewLaunches.setAdapter(launchListAdapter);
    }
    //endregion

    //region NETWORK
    private void sendLoadLaunchesRequest() {
        networkController.loadLaunches(new RequestListener<List<Launch>>() {
            @Override
            public void onSucceed(List<Launch> response) {
                processLoadLaunchesResponse(response);
                swipeRefreshLayoutLaunches.setRefreshing(false);
            }

            @Override
            public void onFailed(Throwable error) {
                ToastUtil.showMessage(MainActivity.this, error.getMessage());
            }
        });
    }

    private void processLoadLaunchesResponse(List<Launch> launches) {
        launchListAdapter.updateItems(launches);
        ToastUtil.showMessage(this, refreshMessageLaunches);
    }
    //endregion
}
