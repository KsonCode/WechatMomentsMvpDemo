package com.wechatmomentsmvpdemo.moments.mvp.model;

import com.mylibrary.mvp.BaseModel;
import com.mylibrary.network.CallbackListener;
import com.wechatmomentsmvpdemo.moments.bean.Moment;
import com.wechatmomentsmvpdemo.utils.DatasUtil;

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


        List<Moment> datas = DatasUtil.createMomentDatas();
        if(datas!=null){
           listener.onSuccess(datas);
        }else{
            listener.onFailure("1","失败了");
        }




    }
}
