package com.app.spacex.network;

/**
 * Created by sinansondas on 08/02/18.
 */

public interface RequestListener<T> {
    void onSucceed(T response);

    void onFailed(Throwable error);
}
