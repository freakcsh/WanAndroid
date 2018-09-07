package com.example.a74099.wanandroid.model.home.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.SimpleActivity;
import com.example.a74099.wanandroid.net.util.NetworkType;
import com.example.a74099.wanandroid.util.ToolUtils;
import com.example.a74099.wanandroid.util.WebViewUtil;

/**
 * Created by 74099 on 2018/7/17.
 */

public class ArticleDetailAct extends SimpleActivity {
    private LinearLayout ll_article_detail;
    private WebView mWebView;
    private String url;
    private String title;
    private ProgressBar mProgressBar;
    private View mErrorView;
    @Override
    protected int getLayout() {
        return R.layout.act_article_detail;
    }

    public static void startAction(Context context, String title,String link) {
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
        mErrorView = View.inflate(this, R.layout.web_error, null);
        mWebView.setLayoutParams(params);

        if (!ToolUtils.isConnected(this)) {
            showDisConnectedView();
            ll_article_detail.addView(mErrorView);
        }else {
            ll_article_detail.addView(mWebView);
            WebViewUtil.getInstance().initWebView(mWebView, this, url, mProgressBar);
        }

    }
    boolean mIsErrorPage;

    protected void showErrorPage(WebView webview) {
        LinearLayout webParentView = (LinearLayout)webview.getParent();
        initErrorPage(webview);//初始化自定义页面
        while (webParentView.getChildCount() > 1) {
            webParentView.removeViewAt(0);
        }
        @SuppressWarnings("deprecation")
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewPager.LayoutParams.FILL_PARENT, ViewPager.LayoutParams.FILL_PARENT);
        webParentView.addView(mErrorView, 0, lp);
        mIsErrorPage = true;
    }
    /***
     * 显示加载失败时自定义的网页
     */
    protected void initErrorPage(final WebView webview) {
        if (mErrorView == null) {

            RelativeLayout layout = (RelativeLayout) mErrorView.findViewById(R.id.online_error_btn_retry);
            layout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    webview.reload();
                }
            });
            mErrorView.setOnClickListener(null);
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
