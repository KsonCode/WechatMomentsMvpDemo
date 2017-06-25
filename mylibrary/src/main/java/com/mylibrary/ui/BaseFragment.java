package com.mylibrary.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mylibrary.R;

import butterknife.ButterKnife;

/**
 *
 */
public abstract class BaseFragment extends Fragment {
    protected View mRoot;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        initArgs(getArguments());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRoot == null) {
            int layoutId = getContentLayoutId();
            mRoot = inflater.inflate(layoutId, container, false);
            initWidget(mRoot);
        } else {
            if (mRoot.getParent() != null) {
                //把当前控件从副控件中移除
                ((ViewGroup) (mRoot.getParent())).removeView(mRoot);
            }
        }

        return mRoot;


    }

    //初始化参数
    protected void initArgs(Bundle bundle) {

    }

    protected abstract int getContentLayoutId();

    protected void initWidget(View root) {

        ButterKnife.bind(this,root);

    }

    protected void initData() {

    }


    //拦截返回事件
    public boolean onBackPressed() {


        return false;
    }

}
