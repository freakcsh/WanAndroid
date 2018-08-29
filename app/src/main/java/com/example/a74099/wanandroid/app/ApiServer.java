package com.example.a74099.wanandroid.app;


import com.example.a74099.wanandroid.bean.ArticleListBean;
import com.example.a74099.wanandroid.bean.BannerBean;
import com.example.a74099.wanandroid.bean.ClassifyBean;
import com.example.a74099.wanandroid.bean.ClassifyTitleBean;
import com.example.a74099.wanandroid.bean.CollectBean;
import com.example.a74099.wanandroid.bean.NavigationBean;
import com.example.a74099.wanandroid.bean.SystemClassifyBean;
import com.example.a74099.wanandroid.bean.SystemDetailBean;
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

    @GET
    Observable<HttpResult<CollectBean>> getCollect(@Url String url);

    @GET
    Observable<HttpResult<ClassifyBean>> getClassifyDetail(@Url String url);

    @GET("tree/json")
    Observable<HttpResult<List<SystemClassifyBean>>> getSystemClassify();

    @GET
    Observable<HttpResult<SystemDetailBean>> getClassifyDetail(@Url String url, @Query("cid") String cid);

    @GET("navi/json")
    Observable<HttpResult<List<NavigationBean>>> getNavigation();

    @GET("project/tree/json")
    Observable<HttpResult<List<ClassifyTitleBean>>> getClassifyTitle();
}
