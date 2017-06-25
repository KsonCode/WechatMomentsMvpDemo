package com.mylibrary.network;

/**
 * Created by kson on 2017/6/25.
 */

public interface CallbackListener<T> {
    void success(T t);
    void failure(String code,String msg);
}
