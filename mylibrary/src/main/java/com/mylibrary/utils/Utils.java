package com.mylibrary.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kson on 2017/6/10.
 * 通用工具类
 */

public class Utils {

    public final static int TITLE_HEIGHT = 25;

    public static String mUUID = "";

    private static String uuid;

    private final static String TAG = "Utils";

    public static String SD_CARD_PATH;
    public static String SD_CARD_IMAGE_PATH;

    //初始化字段
    public static String IMEI;
    public static String OS_VERSION;
    public static String APP_VERSION;
    public static final String OS = "android";

    /**
     * 初始化一些字段
     * 放到application中执行
     */
    public static void init(Context context) {
        IMEI = getIMEI(context);
        OS_VERSION = getSystemVersionName(context);
        APP_VERSION = getVersionName(context);
        SD_CARD_PATH = Environment.getExternalStorageDirectory() + "/Android/data/" + context.getPackageName();
        SD_CARD_IMAGE_PATH = SD_CARD_PATH + "/image";
        isFolderExistsOrCreate(SD_CARD_IMAGE_PATH);
    }

    /**
     * 获取本机IMEI
     *
     * @param context 上下文
     * @return imei号
     */
    public static final String getIMEI(final Context context) {
        TelephonyManager manager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getDeviceId();
    }


    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUUID(Context context) {
        String uuidFileName = ".uuid";
        String sdFilePath = Environment.getExternalStorageDirectory() + "/library/" + uuidFileName;

        if (isVoid(mUUID)) {
            synchronized (mUUID) {
                if (isVoid(mUUID)) {
                    mUUID = readPrivateFileData(context, uuidFileName); //从私有文件中获取
                    File sdFile = new File(sdFilePath);
                    if (!isVoid(mUUID) && !sdFile.exists()) {
                        writeSDFile(sdFilePath, mUUID);
                    }
                }
                if (isVoid(mUUID)) {                                    //从SD卡的文件中获取
                    mUUID = readSDFileData(sdFilePath);
                    if (!isVoid(mUUID)) {
                        writePrivateFile(context, uuidFileName, mUUID);
                    }
                }
                if (isVoid(mUUID)) {                                    //都没有，则重新生成一个，并同时保存在私有目录和SD中
                    mUUID = UUID.randomUUID().toString();
                    writePrivateFile(context, uuidFileName, mUUID);
                    writeSDFile(sdFilePath, mUUID);
                }
            }
        }
        LogUtil.d(TAG, "mUUID: " + mUUID);
        return mUUID;
    }

    public static String readAssetsFile(Context context, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param context
     * @param filename 文件名
     * @param content  文件内容
     * @return
     */
    public static boolean writePrivateFile(Context context, String filename, String content) {
        return writePrivateFile(context, filename, content, "UTF-8");
    }


    /**
     * @param context
     * @param filename 文件名
     * @param content  文件内容
     * @param charset  字符编码
     * @return
     */
    public static boolean writePrivateFile(Context context, String filename, String content, String charset) {
        if (content == null) {
            return false;
        }
        try {
            FileOutputStream fout = context.openFileOutput(filename, Context.MODE_PRIVATE);//获得FileOutputStream
            byte[] bytes = content.getBytes("UTF-8");
            fout.write(bytes);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param context
     * @param fileName 文件名
     * @return
     */
    public static String readPrivateFileData(Context context, String fileName) {
        return readPrivateFileData(context, fileName, "UTF-8");
    }

    /**
     * @param context
     * @param fileName 文件名
     * @param charset  字符编码
     * @return
     */
    public static String readPrivateFileData(Context context, String fileName, String charset) {
        String content = "";
        try {
            FileInputStream fin = context.openFileInput(fileName);
            int length = fin.available();
            byte[] buffer = new byte[length];
            fin.read(buffer);
            content = new String(buffer, charset);
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtil.d(TAG, "readPrivateFileData: " + content);
        return content;
    }

    /**
     * @param filePath 文件路径
     * @param content  文件内容
     * @return
     */
    public static boolean writeSDFile(String filePath, String content) {
        return writeSDFile(filePath, content, "UTF-8");
    }

    /**
     * @param filePath 文件路径
     * @param content  文件内容
     * @param charset  字符编码
     * @return
     */
    public static boolean writeSDFile(String filePath, String content, String charset) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) || content == null) {
            return false;
        }
        try {
            File file = new File(filePath);
            if (file.isDirectory()) {
                deleteDir(file);
            }
            if (isFolderExistsOrCreate(file.getParent())) {
                FileOutputStream outs = new FileOutputStream(file);
                outs.write(content.getBytes(charset));
                outs.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param filePath 文件路劲
     * @return
     */
    public static String readSDFileData(String filePath) {
        return readSDFileData(filePath, "UTF-8");
    }

    /**
     * @param filePath 文件路径
     * @param charset  字符编码
     * @return
     */
    public static String readSDFileData(String filePath, String charset) {
        String content = "";
        File file = new File(filePath);
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
                || !file.exists() || file.isDirectory()) {
            return content;
        }
        try {
            FileInputStream fin = new FileInputStream(file);
            int length = fin.available();
            byte[] buffer = new byte[length];
            fin.read(buffer);
            content = new String(buffer, charset);
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtil.d(TAG, "sdFilePath: " + filePath);
        LogUtil.d(TAG, "readSDFileData: " + content);
        return content;
    }

    public static boolean deleteDir(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    return deleteDir(file);
                } else {
                    file.delete();
                }
            }
        }
        dir.delete();
        return true;
    }


    /**
     * md5 加密
     *
     * @param str
     * @return
     */
    public static String md5Encode(String str) {
        StringBuffer buf = new StringBuffer();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte bytes[] = md5.digest();
            for (int i = 0; i < bytes.length; i++) {
                String s = Integer.toHexString(bytes[i] & 0xff);
                if (s.length() == 1) {
                    buf.append("0");
                }
                buf.append(s);
            }

        } catch (Exception ex) {
        }
        return buf.toString();
    }

    /**
     * 字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isVoid(String str) {
        return (str == null || str.length() == 0);
    }

    public static String toGbk(String string) {
        String gbk = null;
        try {
            gbk = URLEncoder.encode(string, "GBK");
        } catch (UnsupportedEncodingException e) {
            //LogUtil.d(SapiConstants.LOG_TAG, e.toString());
        }
        return gbk;
    }

    public static String utf8Togb2312(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case '+':
                    sb.append(' ');
                    break;
                case '%':
                    try {
                        sb.append((char) Integer.parseInt(
                                str.substring(i + 1, i + 3), 16));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException();
                    }
                    i += 2;
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        // Undo conversion to external encoding
        String result = sb.toString();
        String res = null;
        try {
            byte[] inputBytes = result.getBytes("8859_1");
            res = new String(inputBytes, "UTF-8");
        } catch (Exception e) {
        }
        return res;
    }


    /**
     * 获取设备标识，惟一机器码
     *
     * @param context 上下文
     * @return 机器码
     */
    public static String getUniqueCode(Context context) {
        if (TextUtils.isEmpty(uuid)) {
            if (context == null) return null;
            String imei = getIMEI(context);
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            uuid = Utils.md5Encode(imei + "_" + info.getMacAddress() + "_" + getUUID(context));
        }
        LogUtil.d(TAG, "mUniqueCode: " + uuid);
        return uuid;
    }

    public static boolean isWifi(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static boolean isConnectNetWork(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }

        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null) {
            return activeNetInfo.isAvailable() && activeNetInfo.isConnected();
        }
        return false;
    }

    public static java.net.Proxy getDefaultProxy() {
        if (Proxy.getDefaultHost() != null) {
            return new java.net.Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(
                    Proxy.getDefaultHost(), Proxy.getDefaultPort()));
        }
        return null;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }

    public static int getSystemVersion(Context context) {
        return Build.VERSION.SDK_INT;
    }

    public static String getSystemVersionName(Context context) {
        return Build.VERSION.RELEASE;
    }

    public static String getModel(Context context) {
        return Build.MODEL != null ? Build.MODEL.replace(" ", "") : "unknown";
    }

    /**
     * 设置是否显示标题
     *
     * @param hasTitle 是否有标题
     */
    public static void setHasTitle(Activity activity, boolean hasTitle) {
        if (hasTitle) {
            activity.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        } else {
            activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
    }

    /**
     * 获取是否全屏
     *
     * @return 是否全屏
     */
    public static boolean getIsFullScreen(Activity activity) {
        WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
        return ((attrs.flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0);
    }

    /**
     * 设置是否全屏
     *
     * @param activity     上下文
     * @param isFullScreen 是否全屏
     */
    public static void setFullScreen(Activity activity, boolean isFullScreen) {
        if (isFullScreen) {
            // go full screen
            WindowManager.LayoutParams attrs = activity.getWindow()
                    .getAttributes();
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            activity.getWindow().setAttributes(attrs);
            //			activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            // go non-full screen
            WindowManager.LayoutParams attrs = activity.getWindow()
                    .getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getWindow().setAttributes(attrs);
            //			activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    /**
     * 伸展通知栏
     *
     * @throws Exception invoke方法报的异常
     */
    public static void expendNotification(Activity activity) throws Exception {
        Object service = activity.getSystemService("statusbar");
        if (service != null) {
            Method expand = service.getClass().getMethod("expand");
            expand.invoke(service);
        }
    }

    /**
     * 设置当前屏幕是否自动锁屏
     *
     * @param activity 上下文
     * @param isLock   是否锁屏
     */
    public static void setScreenAutoLock(final Activity activity, final boolean isLock) {
        if (activity == null) {
            return;
        }

        Window window = activity.getWindow();
        if (isLock) {
            window.setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            window.setFlags(0, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    public static void blurWindow(final Activity activity, final boolean isBlur) {
        Window window = activity.getWindow();
        if (isBlur) {
            window.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                    WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        } else {
            window.setFlags(0, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        }
    }

    /**
     * 获取是否自动锁屏
     *
     * @param activity
     * @return
     */
    public static boolean getScreenAutoLock(final Activity activity) {
        Window window = activity.getWindow();
        return (window.getAttributes().flags & WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) > 0;
    }

    /**
     * 设置当前屏的方向设置
     *
     * @param activity   上下文
     * @param orietation 方向值
     */
    public static void setRequestedOrientation(final Activity activity, final int orietation) {
        if (activity == null) {
            return;
        }
        activity.setRequestedOrientation(orietation);
    }

    public static int getRequestedOrientation(final Activity activity) {
        return activity.getRequestedOrientation();
    }

    /**
     * 获取当前手机屏幕方向
     *
     * @param activity 活动
     * @return ActivityInfo的常量值
     */
    public static int getScreenOrientation(Activity activity) {
        return activity.getResources().getConfiguration().orientation;
    }

    /**
     * 设置当前屏的亮度
     *
     * @param activity
     * @param brightness 亮度值，0-1小数
     */
    public static void setScreenBrightness(final Activity activity,
                                           final float brightness) {
        if (activity == null) {
            return;
        }

        final Window window = activity.getWindow();
        window.getAttributes().screenBrightness = brightness;
        window.setAttributes(window.getAttributes());
    }

    /**
     * 获取当前屏的亮度
     *
     * @param activity
     * @return 亮度值
     */
    public static float getScreenBrightness(final Activity activity) {
        if (activity == null) {
            return 1.0f;
        }

        return activity.getWindow().getAttributes().screenBrightness;
    }

    /**
     * 获取当前屏的粒度
     *
     * @param activity
     * @return
     */
    public static float getDensity(final Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.density;
    }

    /**
     * dip/dp转像素
     *
     * @param context  context
     * @param dipValue dip或 dp大小
     * @return 像素值
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static boolean isMediaMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static void setLayoutFlag(final Activity activity, final int flag) {
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        activity.getWindow().addFlags(flag);
        //		activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR);
    }

    public static String getVersionName(Context context) {
        try {
            PackageInfo pinfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return pinfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return "";
    }

    public static int getVersionCode(Context context) {
        try {
            PackageInfo pinfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            return pinfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return 1;
    }

    public static void openAppInMarket(final Context context) {
        if (context == null) {
            return;
        }

        boolean success = true;
        final String packageName = context.getPackageName();
        final String marketAppUrl = "market://details?id=" + packageName;
        final String marketHttpUrl = "http://market.android.com/details?id=" + packageName;
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(marketAppUrl));
            context.startActivity(intent);
        } catch (Exception e) {
            success = false;
        }

        if (!success) {
            try {
                // 本机上没有电子市场，则打开浏览器
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(marketHttpUrl));
                context.startActivity(intent);
            } catch (Exception e) {
                // do nothing
            }
        }
    }

    public static void showInputMethod(final Activity activity) {
        if (activity == null) return;
        InputMethodManager inputMethodManager = ((InputMethodManager) activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE));
        if (activity.getCurrentFocus() != null) {
            inputMethodManager.showSoftInput(activity.getCurrentFocus(), 0);
        }
    }

    public static void hideInputMethod(final Activity activity) {
        if (activity == null) return;
        InputMethodManager inputMethodManager = ((InputMethodManager) activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE));
        if (activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static boolean isShowInputMethod(final Activity activity) {
        if (activity == null) return false;
        InputMethodManager inputMethodManager = ((InputMethodManager) activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE));
        return inputMethodManager.isActive();
    }

    /**
     * 通过给定url打开本地浏览器
     *
     * @param url
     * @param context
     * @return
     * @return: boolean
     * @date: 2011-8-5
     */
    public static boolean startWap(String url, Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void copyFile(File fromFile, File toFile, boolean rewrite) {
        if (!fromFile.exists()) return;
        if (!fromFile.isFile()) return;
        if (!fromFile.canRead()) return;
        if (!toFile.getParentFile().exists()) toFile.getParentFile().mkdirs();

        if (toFile.exists() && rewrite) toFile.delete();
        try {
            FileInputStream fosfrom = new FileInputStream(fromFile);
            FileOutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c); //将内容写到新文件当中
            }
            fosfrom.close();
            fosto.close();
        } catch (Exception ex) {
        }
    }

    public static void copyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (; ; ) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception ex) {
        }
    }


    public static void disableOverScroll(View listView) {
        try {
            //			Method setOverScrollMode = recyclerView.getClass().getMethod("setOverScrollMode", int.class);
            //			setOverScrollMode.invoke(recyclerView, 2);
        } catch (Exception e) {
        }
    }

    public static void createShortcut(Activity context, int icon, String appname) {
        if (context == null || Utils.isVoid(appname)) return;
        try {
            Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            // 快捷方式的名称
            shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, appname);
            shortcut.putExtra("duplicate", false); // 不允许重复创建
            // 指定当前的Activity为快捷方式启动的对象
            Intent intent2 = new Intent(Intent.ACTION_MAIN);
            intent2.addCategory(Intent.CATEGORY_LAUNCHER);
            // 删除的应用程序的ComponentName，即应用程序包名+activity的名字
            intent2.setComponent(new ComponentName(context.getPackageName(), context.getClass().getName()));
            //			Intent intent = new Intent(Intent.ACTION_MAIN);
            //			intent.addCategory(Intent.CATEGORY_DEFAULT);
            ComponentName comp = new ComponentName(context.getPackageName(), context.getClass().getName());
            shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, /*new Intent(Intent.ACTION_MAIN).setComponent(comp)*/intent2);
            // 快捷方式的图标
            Intent.ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(context, icon);
            shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
            context.sendBroadcast(shortcut);
        } catch (Exception e) {

        }
    }

    public static String restrictDecimal(float num) {
        return new DecimalFormat(",###,##0.##").format(num);
    }

    public static String restrictDecimal(String num) {
        if (TextUtils.isEmpty(num)) {
            return "";
        } else {
            try {
                return new DecimalFormat("#,###.##").format(Double.parseDouble(num.replaceAll(",", "")));
            } catch (Exception e) {
                return "";
            }
        }
    }

    public static String formatDecimal(float num) {
        return new DecimalFormat(",###,##0.00").format(num);
    }

    public static String formatDecimal(String num) {
        if (TextUtils.isEmpty(num)) {
            return "";
        } else {
            try {
                return new DecimalFormat("#,##0.00").format(Double.parseDouble(num.replaceAll(",", "")));
            } catch (Exception e) {
                return "";
            }
        }
    }

    public static String formatDecimalShort(String num) {
        if (TextUtils.isEmpty(num)) {
            return "";
        } else {
            try {
                return new DecimalFormat("#,###.##").format(Double.parseDouble(num.replaceAll(",", "")));
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static CharSequence formatDotSmall(CharSequence sequence) {
        return formatDotSmall(sequence, null);
    }

    public static CharSequence formatDotSmall(CharSequence sequence, String color) {
        String s = sequence.toString();
        s = Utils.formatDecimal(s);
        int pos = s.indexOf('.');
        if (pos != -1) {
            if (pos == 0) {
                s = "0" + s;
                ++pos;
            }
            String dot = s.substring(pos, s.length());

            if (!TextUtils.isEmpty(color)) {
                dot = "<font color=\"" + color + "\">" + dot + "</font>";
            }
            s = s.substring(0, pos) + "<small>" + dot + "</small>";
        }
        return Html.fromHtml(s + "<small>元</small>");
    }

    public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);

            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }

            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            }

            json.put("device_id", device_id);

            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMacId(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String mac = wifi.getConnectionInfo().getMacAddress();
        return mac;
    }

    public static String getDeviceId(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();

            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }

            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            }

            return device_id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UUID.randomUUID().toString();
    }

    public static boolean saveOjbToFile(Context context, String fileName, Object items) {
        ObjectOutputStream oos = null;
        boolean result = false;
        try {
            if (items != null) {
                oos = new ObjectOutputStream(context.openFileOutput(fileName, Context.MODE_PRIVATE));
                oos.writeObject(items);
            } else {
                context.deleteFile(fileName);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static Object getOjbFromFile(Context context, String fileName) {
        ObjectInputStream ois = null;
        Object obj = null;
        try {
            ois = new ObjectInputStream(context.openFileInput(fileName));
            obj = ois.readObject();
        } catch (FileNotFoundException e) {
            LogUtil.d("文件不存在");
        } catch (Exception e) {
            e.printStackTrace();
            obj = null;
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    public static String getString(String str, String defaultValue) {
        return TextUtils.isEmpty(str) ? defaultValue : str;
    }

    public static boolean isFolderExistsOrCreate(String strFolder) {
        File file = new File(strFolder);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;

    }

    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^((13)|(15)|(17)|(18))\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean checkCaptcha(String captcha) {
        Pattern p = Pattern.compile("^\\d{6}$");
        Matcher m = p.matcher(captcha);
        return m.matches();
    }

    public static boolean checkNickName(String nickName) {
        if (TextUtils.isEmpty(nickName)) {
            return false;
        }
        String regEx = "[\\u4e00-\\u9fa5]";
        String term = nickName.replaceAll(regEx, "aa");
        if (term.length() < 4 || term.length() > 20) {
            return false;
        }
        Pattern numPattern = Pattern.compile("^[0-9]+$");
        Matcher numMatcher = numPattern.matcher(nickName);
        if (numMatcher.matches()) {
            return false;
        }
        Pattern p = Pattern.compile("^[a-zA-Z0-9\\u4e00-\\u9fa5]+$");
        Matcher m = p.matcher(nickName);
        return m.matches();
    }

    public static String maskMobile(String mobiles) {
        if (!TextUtils.isEmpty(mobiles) && isMobile(mobiles)) {
            return mobiles.substring(0, 3) + "****" + mobiles.substring(7, 11);
        }
        return "";
    }

    public static String getHeadPath() {
        return Environment.getExternalStorageDirectory() + "/clip_temp.jpg";
    }

    public static String getDecimalFormat(double value) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(value);
    }

    public static String getQuotaFormat(double value) {
        DecimalFormat df = new DecimalFormat("######0.##");

        return df.format(value);
    }


    //分转成元
    public static String changeF2Y(String amount) {
        try {
            DecimalFormat df = new DecimalFormat("######0.00");
            return formatDecimal(BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).toString());
        } catch (Exception e) {
            return "";
        }
    }

    //分转成元
    public static String changeF2Y(long amount) {
        try {
            DecimalFormat df = new DecimalFormat("######0.00");
            return formatDecimal(BigDecimal.valueOf(amount).divide(new BigDecimal(100)).toString());
        } catch (Exception e) {
            return "";
        }
    }

    //分转成元
    public static String changeF2Y(float amount) {
        try {
            DecimalFormat df = new DecimalFormat("######0.00");
            return formatDecimal(BigDecimal.valueOf(amount).divide(new BigDecimal(100)).toString());
        } catch (Exception e) {
            return "";
        }
    }

    public static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }

    public static boolean isNullList(List list) {
        return null == list || list.size() == 0;
    }

    public static ArrayList<String> splitDate(String date) {
        ArrayList<String> arrayList = new ArrayList<>();
        int index = date.indexOf("个");
        if (index > 0) {
            arrayList.add(date.substring(0, index));
            arrayList.add(date.substring(index, date.length()));
        } else {
            arrayList.add(date);
            arrayList.add("");

        }
        return arrayList;
    }

    /**
     * 秒数转化成 时:分:秒
     *
     * @param second 毫秒
     * @return
     */
    public static String formatTime(long secondCount) {
        int ss = 1;        //秒
        int mm = ss * 60;    //分
        int hh = mm * 60;    //时

        long hour = secondCount / hh;
        long minute = (secondCount - hour * hh) / mm;
        long second = (secondCount - hour * hh - minute * mm) / ss;

        String strH = hour < 10 ? "0" + hour : "" + hour;
        String strM = minute < 10 ? "0" + minute : "" + minute;
        String strS = second < 10 ? "0" + second : "" + second;

        return strH + ":" + strM + ":" + strS;
    }

    /**
     * 格式化银行卡号 **** **** **** 1234
     *
     * @param cardNo 卡号
     * @return string
     */
    public static String formatCardNo(String cardNo) {
        if (TextUtils.isEmpty(cardNo)) {
            return "";
        }
        if (cardNo.length() <= 4) {
            return cardNo;
        }
        StringBuilder sb = new StringBuilder(cardNo);
        sb.replace(0, cardNo.length() - 4, "****   ****   ****   ");
        return sb.toString();
    }

    /**
     * 格式化时间戳
     *
     * @param time 时间戳
     * @return 2016/06/28
     */
    public static String formatDate(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA);
        Date date = new Date(time * 1000);
        return format.format(date);
    }

    /**
     * 时间戳格式化成想要的形式
     *
     * @param milliseconds 时间戳要转化成13位的
     * @param template     想要转化成的形式
     * @return
     */
    public static String formatDate(long milliseconds, String template) {
        SimpleDateFormat format = new SimpleDateFormat(template, Locale.CHINA);
        Date date = new Date(milliseconds * 1000);
        return format.format(date);
    }

    /**
     * 跳转到拨号界面
     *
     * @param context
     * @param tel
     */
    public static void toTellAction(Context context, String tel) {
        if (isMobile(tel)) {
            Uri uri = Uri.parse("tel:" + tel);
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            context.startActivity(intent);
        }
    }

    /**
     * 验证字符串是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.equals("null") || str.equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
}
