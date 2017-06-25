package com.mylibrary;

import com.mylibrary.utils.AppUtils;
import com.mylibrary.utils.FileUtils;

/**
 * Created by kson on 2017/6/25.
 */

public class Constant {

    public static final String IMG_BASE_URL = "http://statics.zhuishushenqi.com";

    public static final String API_BASE_URL = "http://api.zhuishushenqi.com";

    public static String PATH_DATA = FileUtils.createRootPath(AppUtils.getAppContext()) + "/cache";


    public static String PATH_MOMENTS = PATH_DATA + "/moments/";


    public static String BASE_PATH = AppUtils.getAppContext().getCacheDir().getPath();
    public static final String SUFFIX_ZIP = ".zip";
}
