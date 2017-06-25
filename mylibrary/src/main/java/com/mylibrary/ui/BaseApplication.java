package com.mylibrary.ui;

import android.app.Application;
import android.content.Context;

import com.mylibrary.utils.AppUtils;
import com.mylibrary.utils.Utils;

/**
 * Created by kson on 2017/6/25.
 */

public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(getApplicationContext());
        Utils.init(getApplicationContext());
    }


}
