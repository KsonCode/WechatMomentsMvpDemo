package com.mylibrary.mvp;

/**
 * Created by kson on 2017/6/25.
 */

public interface BaseView {
    void noNetWork();//无网络

    //有网络
    void showLoading();
    void hideLoading();
    void onFailure(String code ,String msg);


}
