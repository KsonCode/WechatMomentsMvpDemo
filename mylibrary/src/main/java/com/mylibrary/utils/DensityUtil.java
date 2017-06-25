package com.mylibrary.utils;

import android.content.Context;

import com.mylibrary.ui.BaseApplication;


/**
 * Created by kson on 2017/6/25.
 */
public class DensityUtil {

    public static int dip2px(Context context, int dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static float getDensity() {
        return AppUtils.getAppContext().getResources().getDisplayMetrics().density;
    }

    public static int dip2px(float dipValue) {

        return (int) (dipValue * getDensity() + 0.5f);
    }

    public static int px2dip(float pxValue) {
        return (int) (pxValue / getDensity() + 0.5f);
    }

}
