package com.wechatmomentsmvpdemo.moments.adapter;

import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wechatmomentsmvpdemo.R;


/**
 * Created by kson on 2017/6/25.
 */
public class URLViewHolder extends MomentViewHolder{
    public LinearLayout urlBody;
    /** 链接的图片 */
    public ImageView urlImageIv;
    /** 链接的标题 */
    public TextView urlContentTv;

    public URLViewHolder(View itemView){
        super(itemView, TYPE_URL);
    }

    @Override
    public void initSubView(int viewType, ViewStub viewStub) {
        if(viewStub == null){
            throw new IllegalArgumentException("viewStub is null...");
        }

        viewStub.setLayoutResource(R.layout.viewstub_urlbody);
        View subViw  = viewStub.inflate();
        LinearLayout urlBodyView = (LinearLayout) subViw.findViewById(R.id.urlBody);
        if(urlBodyView != null){
            urlBody = urlBodyView;
            urlImageIv = (ImageView) subViw.findViewById(R.id.urlImageIv);
            urlContentTv = (TextView) subViw.findViewById(R.id.urlContentTv);
        }
    }
}
