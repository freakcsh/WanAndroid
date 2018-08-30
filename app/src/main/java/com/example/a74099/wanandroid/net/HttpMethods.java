package com.example.a74099.wanandroid.net;


import com.example.a74099.wanandroid.app.App;
import com.example.a74099.wanandroid.app.Constants;
import com.example.a74099.wanandroid.net.interceptor.AddCookiesInterceptor;
import com.example.a74099.wanandroid.net.interceptor.SaveCookiesInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * okHttp请求类封装
 */
public class HttpMethods {
    //这是连接网络的时间
    private static final int DEFAULT_TIMEOUT = 10;

    private Retrofit retrofit;

    public HttpMethods() {
        /**
         * 创建okHttpClient
         */
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        /**
         * 设置网络超时时间 时间按秒计算
         */
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //设置cookie拦截器
        setCookiesInterceptor(builder);

        /**
         * addConverterFactory 添加格式转换器工程  GsonConverterFactory
         * addCallAdapterFactory 添加调用适配器工程 RxJavaCallAdapterFactory
         * baseUrl 基础地址
         */
        retrofit = new Retrofit.Builder().client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();
    }

    /**
     * 使用拦截器的方式保存cookie
     *
     * @param builder
     */
    public void setCookiesInterceptor(OkHttpClient.Builder builder) {
        builder.addInterceptor(new AddCookiesInterceptor(App.getInstance().getApplicationContext()));
        builder.addInterceptor(new SaveCookiesInterceptor(App.getInstance().getApplicationContext()));
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final Retrofit INSTANCE = new HttpMethods().getServer();
    }

    /**
     * 获取单例
     *
     * @return 返回一个Retrofit对象
     */
    public static Retrofit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private Retrofit getServer() {
        return retrofit;
    }
}
