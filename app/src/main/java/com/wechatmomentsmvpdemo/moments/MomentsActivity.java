package com.wechatmomentsmvpdemo.moments;

import com.mylibrary.ui.BaseActivity;
import com.wechatmomentsmvpdemo.R;
import com.wechatmomentsmvpdemo.moments.bean.Moment;
import com.wechatmomentsmvpdemo.moments.mvp.contract.MomentsContract;
import com.wechatmomentsmvpdemo.moments.mvp.presenter.MomentsPresenter;

import java.util.List;

public class MomentsActivity extends BaseActivity implements MomentsContract.view {
    private MomentsPresenter momentsPresenter;


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_moments;
    }

    @Override
    protected void initData() {
        super.initData();
        momentsPresenter = new MomentsPresenter(this);
        momentsPresenter.loadData(1);
    }

    @Override
    public void noNetWork() {

        showToast("网络状态不好，请稍后重试");

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onFailure(String code, String msg) {
        showToast(msg);

    }

    @Override
    public void loadData(int loadType, List<Moment> datas) {

    }
}
