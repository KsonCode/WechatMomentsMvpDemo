package com.mylibrary.mvp;

import com.mylibrary.network.CallbackListener;

/**
 * Created by kson on 2017/6/25.
 */

public interface BaseModel<T,Data> {


    Data getLocalData(T params);//本地数据

    void getRemoteData(T params, CallbackListener<Data> listener);//网络请求
}
