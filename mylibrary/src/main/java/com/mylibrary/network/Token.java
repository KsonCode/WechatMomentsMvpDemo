package com.mylibrary.network;

import android.text.TextUtils;

import com.mylibrary.ui.BaseApplication;
import com.mylibrary.utils.AppUtils;
import com.mylibrary.utils.Utils;


/**
 * Created by kson on 17/6/25.
 */
public class Token {
    private static final String TOKEN_NAME = "_token_name_";
    private static String _token;

    public static String getToken() {
        if (TextUtils.isEmpty(_token)) {
            Object obj = Utils.getOjbFromFile(AppUtils.getAppContext(), TOKEN_NAME);
            if (obj != null) {
                _token = (String) obj;
            }
        }
        return _token;
    }

    public static void saveToken(String token) {
        _token = token;
        Utils.saveOjbToFile(AppUtils.getAppContext(), TOKEN_NAME, token);
    }
}
