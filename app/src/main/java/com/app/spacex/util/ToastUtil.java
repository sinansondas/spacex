package com.app.spacex.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by sinansondas on 08/02/18.
 */

public class ToastUtil {
    public static void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
