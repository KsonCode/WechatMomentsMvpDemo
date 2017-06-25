package com.wechatmomentsmvpdemo;

import android.widget.Button;

import com.mylibrary.ui.BaseActivity;
import com.wechatmomentsmvpdemo.moments.MomentsActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.in_btn)
    Button inBtn;


    @OnClick(R.id.in_btn)
    public void goIn(Button button) {

        startActivity(MomentsActivity.class);

    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }
}
