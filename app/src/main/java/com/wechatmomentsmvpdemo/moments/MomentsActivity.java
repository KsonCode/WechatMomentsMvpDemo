package com.wechatmomentsmvpdemo.moments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mylibrary.ui.BaseActivity;
import com.wechatmomentsmvpdemo.R;
import com.wechatmomentsmvpdemo.moments.adapter.MomentAdapter;
import com.wechatmomentsmvpdemo.moments.bean.Moment;
import com.wechatmomentsmvpdemo.moments.mvp.contract.MomentsContract;
import com.wechatmomentsmvpdemo.moments.mvp.presenter.MomentsPresenter;
import com.wechatmomentsmvpdemo.widgets.TitleBar;

import java.util.List;

import butterknife.BindView;

public class MomentsActivity extends BaseActivity implements MomentsContract.view {
    private MomentsPresenter momentsPresenter;
    private MomentAdapter momentAdapter;

    @BindView(R.id.main_title_bar)
    TitleBar titleBar;
    @BindView(R.id.rclv)
    RecyclerView recyclerView;

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
    protected void initWidget() {
        super.initWidget();
        titleBar.setTitle("朋友圈");
        titleBar.setTitleColor(getResources().getColor(R.color.white));
        titleBar.setBackgroundColor(getResources().getColor(R.color.title_bg));

        TextView textView = (TextView) titleBar.addAction(new TitleBar.TextAction("发布") {
            @Override
            public void performAction(View view) {

                showToast("发布");

            }
        });
        textView.setTextColor(getResources().getColor(R.color.white));
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

        momentAdapter = new MomentAdapter(this);
        momentAdapter.setMomentsPresenter(momentsPresenter);
        momentAdapter.setDatas(datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(momentAdapter);
    }
}
