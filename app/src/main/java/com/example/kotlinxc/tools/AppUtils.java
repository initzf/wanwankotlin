package com.example.kotlinxc.tools;

import android.content.Context;

/**
 * Created by zf on 2018/2/8.
 */

public class AppUtils {

    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
