package com.wechatmomentsmvpdemo.moments.mvp.model;

import com.mylibrary.mvp.BaseModel;
import com.mylibrary.network.CallbackListener;
import com.wechatmomentsmvpdemo.moments.bean.Moment;

import java.util.List;

/**
 * @author kson
 * @date 2017/6/25.
 * @Description:
 */

public class MomentsModel implements BaseModel<Object,List<Moment>> {


    @Override
    public List<Moment> getLocalData(Object params) {
        return null;
    }

    @Override
    public void getRemoteData(Object params, CallbackListener<List<Moment>> listener) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




    }
}
