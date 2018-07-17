package com.example.a74099.wanandroid.model.home.activity;

import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.SimpleActivity;
import com.example.a74099.wanandroid.util.WebViewUtil;

/**
 * Created by 74099 on 2018/7/17.
 */

public class BannerDetailAct extends SimpleActivity {
    private LinearLayout ll_banner_detail;
    private WebView mWebView;
    private String url;
    private String title;

    @Override
    protected int getLayout() {
        return R.layout.act_banner_detail;
    }

    @Override
    protected void initEventAndData() {
        setBackPress();
        ll_banner_detail=findViewById(R.id.ll_banner_detail);
        url=getIntent().getStringExtra("url");
        title=getIntent().getStringExtra("title");
        setTitleTx(title);
        ll_banner_detail = findViewById(R.id.ll_banner_detail);
        mWebView=new WebView(getApplicationContext());
        mWebView.setBackgroundColor(0);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(params);
        ll_banner_detail.addView(mWebView);
        WebViewUtil.getInstance().initWebView(mWebView,this,url);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (WebViewUtil.onBackPressed(mWebView)) {
            super.onBackPressed();
        }
    }


    @Override
    protected void onDestroy() {
        WebViewUtil.cleanWebView(mWebView);
        mWebView=null;
        super.onDestroy();
    }
}