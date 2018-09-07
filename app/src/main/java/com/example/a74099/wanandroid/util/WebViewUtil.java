package com.example.a74099.wanandroid.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.app.App;

import java.util.List;



public class WebViewUtil {
    private static final WebViewUtil u = new WebViewUtil();
    private Integer code = 0;

    //	// 获取实体
    public static WebViewUtil getInstance() {
        return u;
    }


    /**
     * 设置软键盘
     */
    public static void setSoftKeyboard(Activity activity) {
        //设置窗口格式为半透明
        activity.getWindow().setFormat(PixelFormat.TRANSLUCENT);// （这个对宿主没什么影响，建议声明）
        //软键盘设置   整个Layout重新编排,重新分配多余空间 |  隐藏软键盘
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                        | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    /**
     * 初始化webview  加载网页并返回webview
     */

    public WebView initWebView1(final WebView webView, final Activity activity, String url, final TextView textView, final ImageView imageView) {
        code = 0;
        String userToken;
        String cookie;
        final Boolean bol = true;
        //获取cookie和userToken
//        try {
//            FToken fToken = ToolUtils.getToken(activity, false);
//            userToken = fToken.getSession_id();
//            cookie = fToken.getSession_id();
//        } catch (NeedLoginException e) {
//            userToken = null;
//            cookie = null;
//        }
        setSoftKeyboard(activity);
        WebSettings webSettings = webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        webSettings.setDatabaseEnabled(true);   //开启 database storage API 功能
        webSettings.setAppCacheEnabled(true);//开启 Application Caches 功能


        // 特别注意：5.1以上默认禁止了https和http混用，以下方式是开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

//        if (!ToolUtils.isNull(cookie)) {
//            List<String> cookies = new ArrayList<>();
//            //键值对类型  用等号（"="）连接    具体根据后台给定
//            cookies.add(cookie);//根据后台协商而定
//            syncCookieToWebView(url, cookies, activity);
//        }
        //加载url
        //注入头部信息
//        final Map<String, String> map = new HashMap<>();
//        if (!ToolUtils.isNull(userToken)) {
//            map.put("Usertoken", userToken);
//            map.put("Isapp", "true");
//        }
//        webView.loadUrl(url, map);
        webView.loadUrl(url);
        webView.addJavascriptInterface(new JavaScriptInterface(activity), "szqg");
        // 复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        webView.setWebViewClient(new WebViewClient() {
            //处理各种通知 & 请求事件
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) return false;
                try {
                    if (url.startsWith("http:") || url.startsWith("https:")) {
//                        view.loadUrl(url, map);
                        view.loadUrl(url);
                        return true;
                    } else {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        activity.startActivity(intent);
                        return true;
                    }
                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return false;
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //设定加载开始的操作
                LoadingUtils.getLoadingUtils().showLoadingView(activity);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //设定加载结束的操作
                LoadingUtils.getLoadingUtils().hideLoadingView(activity);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                //设定加载资源的操作
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                //加载页面的服务器出现错误时（如404）调用
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //加载页面的时候网络断了处理
                code = 1;
                //webView.loadUrl("file:///android_res/raw/error.html");
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //webView默认是不处理https请求的，页面显示空白，需要进行如下设置：
                handler.proceed();    //表示等待证书响应
                // handler.cancel();      //表示挂起连接，为默认方式
                // handler.handleMessage(null);    //可做其他处理
            }
        });
        //辅助 WebView 处理 Javascript 的对话框,网站图标,网站标题等等
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //获得网页的加载进度并显示
                if (newProgress == 100) {
                    if (code == 0) {
                        webView.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.GONE);
                        textView.setVisibility(View.GONE);
                    } else {
                        webView.setVisibility(View.GONE);
                        imageView.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                //获取Web页中的标题
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                //支持javascript的警告框
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                //支持javascript的确认框
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                //支持javascript输入框
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });
        return webView;
    }

    /**
     * 初始化webview  加载网页
     */
    public void initWebView(final WebView webView, final Activity activity, String url, final ProgressBar progressBar) {
        code = 0;
        String userToken;
        String cookie;
        final Boolean bol = true;
        //获取cookie和userToken
//        try {
//            FToken fToken = ToolUtils.getToken(activity, false);
//            userToken = fToken.getSession_id();
//            cookie = fToken.getSession_id();
//        } catch (NeedLoginException e) {
//            userToken = null;
//            cookie = null;
//        }
        setSoftKeyboard(activity);
        WebSettings webSettings = webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        webSettings.setDatabaseEnabled(true);   //开启 database storage API 功能
        webSettings.setAppCacheEnabled(true);//开启 Application Caches 功能


        // 特别注意：5.1以上默认禁止了https和http混用，以下方式是开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

//        if (!ToolUtils.isNull(cookie)) {
//            List<String> cookies = new ArrayList<>();
//            //键值对类型  用等号（"="）连接    具体根据后台给定
//            cookies.add(cookie);//根据后台协商而定
//            syncCookieToWebView(url, cookies, activity);
//        }
        //加载url
        //注入头部信息
//        final Map<String, String> map = new HashMap<>();
//        if (!ToolUtils.isNull(userToken)) {
//            map.put("Usertoken", userToken);
//            map.put("Isapp", "true");
//        }
//        webView.loadUrl(url, map);
        webView.loadUrl(url);
        webView.addJavascriptInterface(new JavaScriptInterface(activity), "szqg");
        // 复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        webView.setWebViewClient(new WebViewClient() {
            //处理各种通知 & 请求事件
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) return false;
                try {
                    if (url.startsWith("http:") || url.startsWith("https:")) {
//                        view.loadUrl(url, map);
                        view.loadUrl(url);
                        return true;
                    } else {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        activity.startActivity(intent);
                        return true;
                    }
                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return false;
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //设定加载开始的操作
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //设定加载结束的操作
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                //设定加载资源的操作
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                //加载页面的服务器出现错误时（如404）调用
//                showErrorPage(view);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //加载页面的时候网络断了处理
                code = 1;
                //webView.loadUrl("file:///android_res/raw/error.html");
                super.onReceivedError(view, errorCode, description, failingUrl);

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //webView默认是不处理https请求的，页面显示空白，需要进行如下设置：
                handler.proceed();    //表示等待证书响应
                // handler.cancel();      //表示挂起连接，为默认方式
                // handler.handleMessage(null);    //可做其他处理
            }
        });
        //辅助 WebView 处理 Javascript 的对话框,网站图标,网站标题等等
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //获得网页的加载进度并显示
                if (newProgress == 100) {
//                    if (code == 0) {
//                        webView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);//加载完网页进度条消失
//                        imageView.setVisibility(View.GONE);
//                        textView.setVisibility(View.GONE);
                    } else {
                        progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                        progressBar.setProgress(newProgress);//设置进度值
//                        webView.setVisibility(View.GONE);
//                        imageView.setVisibility(View.VISIBLE);
//                        textView.setVisibility(View.VISIBLE);
                    }

//                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                //获取Web页中的标题
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                //支持javascript的警告框
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                //支持javascript的确认框
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                //支持javascript输入框
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });
    }
    boolean mIsErrorPage;
    private View mErrorView;
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
            mErrorView = View.inflate(App.getInstance(), R.layout.web_error, null);
            RelativeLayout layout = (RelativeLayout) mErrorView.findViewById(R.id.online_error_btn_retry);
            layout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    webview.reload();
                }
            });
            mErrorView.setOnClickListener(null);
        }
    }
        /****
         * 把系统自身请求失败时的网页隐藏
         */
//    protected void hideErrorPage() {
//        LinearLayout webParentView = (LinearLayout)webview.getParent();
//        mIsErrorPage = false;
//        while (webParentView.getChildCount() > 1) {
//            webParentView.removeViewAt(0);
//        }
//    }
    private final class JavaScriptInterface {
        public Activity activity;

        public JavaScriptInterface(Activity activity) {
            this.activity = activity;
        }

        @JavascriptInterface
        public void exitToAndroid() {
            activity.finish();
        }

        @JavascriptInterface
        public void LoginToAndroid() {
//            ToolUtils.logout(activity);
//            Intent intent = new Intent(activity, LoginAct.class);
//            activity.startActivity(intent);
        }

        @JavascriptInterface
        public void ShowToAndroid(String s) {
//            T.showShort(activity, s);
            activity.finish();
        }

    }

    //注入cookie信息
    private static void syncCookieToWebView(String url, List<String> cookies, Activity activity) {
        CookieSyncManager.createInstance(activity);
        CookieManager cm = CookieManager.getInstance();
        cm.setAcceptCookie(true);
        cm.removeSessionCookie();//移除
        if (cookies != null) {
            for (String cookie : cookies) {
                cm.setCookie(url, cookie);//注意端口号和域名，这种方式可以同步所有cookie，包括sessionid
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().flush();
        } else {
            CookieSyncManager.getInstance().sync();
        }
    }


    /**
     * 防止webView内存泄露
     */
    public static void cleanWebView(WebView webView) {
        //在 Activity 销毁（ WebView ）的时候，先让 WebView 加载null内容，然后移除 WebView，再销毁 WebView，最后置空   避免WebView内存泄露
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearCache(true);
            webView.clearHistory();
            webView.clearFormData();
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
        }
    }

    /**
     * webView返回事件
     */
    public static Boolean onBackPressed(WebView webView) {
        if (webView.canGoBack()) {
            webView.goBack();
            return false;
        }
        return true;
    }

}



