package com.mylibrary.network;

/**
 * Created by kson on 2017/6/25.
 */

public interface CallbackListener<T> {
    void onStart();
    void onSuccess(T t);
    void onFailure(String code,String msg);
}
