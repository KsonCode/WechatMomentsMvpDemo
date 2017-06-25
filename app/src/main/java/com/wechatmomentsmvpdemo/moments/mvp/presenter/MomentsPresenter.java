package com.wechatmomentsmvpdemo.moments.mvp.presenter;

import com.mylibrary.network.CallbackListener;
import com.mylibrary.utils.AppUtils;
import com.mylibrary.utils.NetworkUtils;
import com.wechatmomentsmvpdemo.moments.bean.Moment;
import com.wechatmomentsmvpdemo.moments.mvp.contract.MomentsContract;
import com.wechatmomentsmvpdemo.moments.mvp.model.MomentsModel;

import java.io.File;
import java.util.List;

/**
 * Created by kson on 2017/6/25.
 */

public class MomentsPresenter implements MomentsContract.Presenter {
    private MomentsModel momentsModel;
    private MomentsContract.view view;

    public MomentsPresenter(MomentsContract.view view) {
        this.view = view;
        momentsModel = new MomentsModel();
    }

    @Override
    public void loadData(final int loadType) {
        if (!NetworkUtils.isAvailable(AppUtils.getAppContext())) {//网络不可用
            view.noNetWork();
            List<Moment> list = momentsModel.getLocalData(null);//加载本地数据
            if (list != null) {
                view.loadData(loadType, list);
            }
        } else {
            momentsModel.getRemoteData(null, new CallbackListener<List<Moment>>() {
                @Override
                public void onStart() {//加载本地操作

                    List<Moment> list = momentsModel.getLocalData(null);//加载本地数据
                    if (list != null) {
                        view.loadData(loadType, list);
                    }

                }

                @Override
                public void onSuccess(List<Moment> moments) {

                    view.loadData(loadType, moments);

                }


                @Override
                public void onFailure(String code, String msg) {
                    view.onFailure(code, msg);

                }

            });
        }
    }

}
