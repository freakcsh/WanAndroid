package com.example.a74099.wanandroid.app;


import com.example.a74099.wanandroid.bean.ArticleListBean;
import com.example.a74099.wanandroid.bean.BannerBean;
import com.example.a74099.wanandroid.net.HttpResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiServer {

    //    @GET("Digital")
//    Observable<HttpResult<TextBean>> getCoin(@Header("Usertoken") String Usertoken,
//                                             @Query("s") String s,
//                                             @Query("offset") String offset,
//                                             @Query("limit") String limit,
//                                             @Query("coin_name") String coin_name,
//                                             @Query("status") String status);
//



    @GET("toutiao/index")
    Observable<HttpResult<List<String>>> getNews(@Query("type") String type,
                                                 @Query("key") String key);

    @GET("banner/json")
    Observable<HttpResult<List<BannerBean>>> getBanner();

    @GET
    Observable<HttpResult<ArticleListBean>> getArticle(@Url String url);
}
