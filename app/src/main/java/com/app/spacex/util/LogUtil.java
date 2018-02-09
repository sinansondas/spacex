package com.app.spacex.util;

import android.util.Log;

/**
 * Created by sinansondas on 08/02/18.
 */

public class LogUtil {
    public static void log(String message) {
        Log.i(LogUtil.class.getSimpleName(), message);
    }
}
