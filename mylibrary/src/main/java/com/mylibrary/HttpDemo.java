package com.mylibrary;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by kson on 2017/6/28.
 */

public class HttpDemo {
    public static void main(String args []){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.baidu.com").build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
                System.out.println("response:"+response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
