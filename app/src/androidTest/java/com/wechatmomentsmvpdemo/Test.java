package com.wechatmomentsmvpdemo;

import android.support.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;

/**
 * Created by kson on 2017/6/28.
 */

@RunWith(AndroidJUnit4.class)
public class Test {

    @org.junit.Test
    public void test(){
        tesss();
    }

    public void tesss(){
        System.out.println(1/0);
    }
}
