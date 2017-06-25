package com.mylibrary.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 *
 * Created by kson on 2017/6/25.
 * 日志工具类，集中控制日志的打印
 * 后期考虑打印入file或者上传到服务器等策略
 */

public class LogUtil {

    /**
     * 默认的文库日志Tag标签
     */
    public final static String DEFAULT_TAG = "esonlib";

    /**
     * 此常量用于控制是否打日志到Logcat中
     * release版本中本变量应置为false
     */
    public static boolean LOGGABLE = true;

    /**
     * 打印debug级别的log
     *
     * @param tag tag标签
     * @param str 内容
     */
    public static void d(String tag, String str) {
        if (LOGGABLE && !TextUtils.isEmpty(str)) {
            Log.d(tag, str);
        }
    }

    /**
     * 打印debug级别的log
     *
     * @param str 内容
     */
    public static void d(String str) {
        if (LOGGABLE && !TextUtils.isEmpty(str)) {
            Log.d(DEFAULT_TAG, str);
        }
    }

    /**
     * 打印warning级别的log
     *
     * @param tag tag标签
     * @param str 内容
     * @param tr  An exception to log
     */
    public static void w(String tag, String str, Throwable tr) {
        if (LOGGABLE && !TextUtils.isEmpty(str)) {
            Log.w(tag, str, tr);
        }
    }

    /**
     * 打印warning级别的log
     *
     * @param tag tag标签
     * @param str 内容
     */
    public static void w(String tag, String str) {
        if (LOGGABLE && !TextUtils.isEmpty(str)) {
            Log.w(tag, str);
        }
    }

    /**
     * 打印warning级别的log
     *
     * @param str 内容
     */
    public static void w(String str) {
        if (LOGGABLE && !TextUtils.isEmpty(str)) {
            Log.w(DEFAULT_TAG, str);
        }
    }

    /**
     * 打印error级别的log
     *
     * @param tag tag标签
     * @param str 内容
     * @param tr  An exception to log
     */
    public static void e(String tag, String str, Throwable tr) {
        if (LOGGABLE && !TextUtils.isEmpty(str)) {
            Log.e(tag, str, tr);
        }
    }

    /**
     * 打印error级别的log
     *
     * @param tag tag标签
     * @param str 内容
     */
    public static void e(String tag, String str) {
        if (LOGGABLE && !TextUtils.isEmpty(str)) {
            Log.e(tag, str);
        }
    }

    /**
     * 打印error级别的log
     *
     * @param str 内容
     */
    public static void e(String str) {
        if (LOGGABLE && !TextUtils.isEmpty(str)) {
            Log.e(DEFAULT_TAG, str);
        }
    }

    /**
     * 打印info级别的log
     *
     * @param tag tag标签
     * @param str 内容
     */
    public static void i(String tag, String str) {
        if (LOGGABLE && !TextUtils.isEmpty(str)) {
            Log.i(tag, str);
        }
    }

    /**
     * 打印info级别的log
     *
     * @param str 内容
     */
    public static void i(String str) {
        if (LOGGABLE && !TextUtils.isEmpty(str)) {
            Log.i(DEFAULT_TAG, str);
        }
    }

    /**
     * 打印verbose级别的log
     *
     * @param tag tag标签
     * @param str 内容
     */
    public static void v(String tag, String str) {
        if (LOGGABLE && !TextUtils.isEmpty(str)) {
            Log.v(tag, str);
        }
    }

    /**
     * 打印verbose级别的log
     *
     * @param str 内容
     */
    public static void v(String str) {
        if (LOGGABLE && !TextUtils.isEmpty(str)) {
            Log.v(DEFAULT_TAG, str);
        }
    }

}
