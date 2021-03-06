package com.example.a74099.wanandroid.model.home.activity;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.SimpleActivity;
import com.example.a74099.wanandroid.net.util.NetworkType;
import com.example.a74099.wanandroid.util.ToolUtils;
import com.example.a74099.wanandroid.util.WebViewUtil;

/**
 * Created by 74099 on 2018/7/17.
 */

public class BannerDetailAct extends SimpleActivity {
    private LinearLayout ll_banner_detail;
    private WebView mWebView;
    private String url;
    private String title;
    private ProgressBar mProgressBar;
    private LinearLayout ll_web_error;
    @Override
    protected int getLayout() {
        return R.layout.act_banner_detail;
    }

    /**
     * 断网重连
     * @param networkType
     */
    @Override
    public void onNetConnected(NetworkType networkType) {
        super.onNetConnected(networkType);
        ll_web_error.setVisibility(View.GONE);
        ll_banner_detail.setVisibility(View.VISIBLE);
        WebViewUtil.getInstance().initWebView(mWebView,this,url,mProgressBar);
    }

    @Override
    protected void initEventAndData() {
        setBackPress();
        ll_banner_detail=findViewById(R.id.ll_banner_detail);
        url=getIntent().getStringExtra("url");
        title=getIntent().getStringExtra("title");
        setTitleTx(title);
        ll_banner_detail = findViewById(R.id.ll_banner_detail);
        ll_web_error = findViewById(R.id.ll_web_error);
        mProgressBar=findViewById(R.id.progressBar1);
        mWebView=new WebView(getApplicationContext());
        mWebView.setBackgroundColor(0);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(params);
        ll_banner_detail.addView(mWebView);
        if (!ToolUtils.isConnected(this)){
            showDisConnectedView();
            ll_web_error.setVisibility(View.VISIBLE);
            ll_banner_detail.setVisibility(View.GONE);
        }else {
            ll_web_error.setVisibility(View.GONE);
            ll_banner_detail.setVisibility(View.VISIBLE);
            WebViewUtil.getInstance().initWebView(mWebView,this,url,mProgressBar);
        }

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
