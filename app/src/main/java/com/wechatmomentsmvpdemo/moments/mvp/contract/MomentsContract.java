package com.wechatmomentsmvpdemo.moments.mvp.contract;

import com.mylibrary.mvp.BasePresenter;
import com.mylibrary.mvp.BaseView;
import com.wechatmomentsmvpdemo.moments.bean.Moment;

import java.util.List;

/**
 * Created by kson on 2017/6/25.
 */

public interface MomentsContract {

    interface view extends BaseView{
        void loadData(int loadType,List<Moment> datas);
    }

    interface Presenter extends BasePresenter{
        void loadData(int loadType);
    }

}
