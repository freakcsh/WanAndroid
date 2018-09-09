package com.example.a74099.wanandroid.model.myself.activity.collect.detail;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.SimpleActivity;
import com.example.a74099.wanandroid.model.home.activity.ArticleDetailAct;
import com.example.a74099.wanandroid.net.util.NetworkType;
import com.example.a74099.wanandroid.util.ToolUtils;
import com.example.a74099.wanandroid.util.WebViewUtil;

/**
 * Created by 74099 on 2018/8/31.
 */

public class CollectDetailAct extends SimpleActivity {
    private LinearLayout ll_article_detail;
    private WebView mWebView;
    private String url;
    private String title;
    private ProgressBar mProgressBar;
    private LinearLayout ll_web_error;

    @Override
    protected int getLayout() {
        return R.layout.act_article_detail;
    }

    public static void startAction(Context context, String title, String link) {
        Intent intent = new Intent(context, ArticleDetailAct.class);
        intent.putExtra("url", link);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 断网重连
     *
     * @param networkType
     */
    @Override
    public void onNetConnected(NetworkType networkType) {
        super.onNetConnected(networkType);
        ll_web_error.setVisibility(View.GONE);
        ll_article_detail.setVisibility(View.VISIBLE);
        WebViewUtil.getInstance().initWebView(mWebView, this, url, mProgressBar);
    }

    @Override
    protected void initEventAndData() {
        setBackPress();
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        setTitleTx(title);
        ll_article_detail = findViewById(R.id.ll_article_detail);
        mProgressBar = findViewById(R.id.progressBar1);
        mWebView = new WebView(getApplicationContext());
        mWebView.setBackgroundColor(0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ll_web_error = findViewById(R.id.ll_web_error);
        mWebView.setLayoutParams(params);
        ll_article_detail.addView(mWebView);
        if (!ToolUtils.isConnected(this)) {
            showDisConnectedView();
            ll_web_error.setVisibility(View.VISIBLE);
            ll_article_detail.setVisibility(View.GONE);
        } else {
            ll_web_error.setVisibility(View.GONE);
            ll_article_detail.setVisibility(View.VISIBLE);
            WebViewUtil.getInstance().initWebView(mWebView, this, url, mProgressBar);
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
        mWebView = null;
        super.onDestroy();
    }
}
