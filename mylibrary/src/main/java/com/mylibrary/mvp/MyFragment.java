package com.mylibrary.mvp;

import android.content.Intent;

import com.mylibrary.ui.BaseFragment;

/**
 * Created by kson on 2017/6/28.
 */

public class MyFragment extends BaseFragment {
    @Override
    protected int getContentLayoutId() {

        return 0;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
