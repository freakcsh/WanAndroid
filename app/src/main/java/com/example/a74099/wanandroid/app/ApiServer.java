package com.example.a74099.wanandroid.app;


import com.example.a74099.wanandroid.bean.ArticleListBean;
import com.example.a74099.wanandroid.bean.BannerBean;
import com.example.a74099.wanandroid.bean.ClassifyBean;
import com.example.a74099.wanandroid.bean.ClassifyTitleBean;
import com.example.a74099.wanandroid.bean.CollectBean;
import com.example.a74099.wanandroid.bean.FrequentlyBean;
import com.example.a74099.wanandroid.bean.LoginBean;
import com.example.a74099.wanandroid.bean.LoginOutBean;
import com.example.a74099.wanandroid.bean.NavigationBean;
import com.example.a74099.wanandroid.bean.SearchDetailBean;
import com.example.a74099.wanandroid.bean.SystemClassifyBean;
import com.example.a74099.wanandroid.bean.SystemDetailBean;
import com.example.a74099.wanandroid.bean.TopSearchData;
import com.example.a74099.wanandroid.net.HttpResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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

    /**
     * 搜索热词
     *
     * @return
     */
    @GET("/hotkey/json")
    Observable<HttpResult<List<TopSearchData>>> doSearchHot();

    /**
     * 搜索
     *
     * @param page
     * @return
     */
    @POST("article/query/{page}/json")
    Observable<HttpResult<SearchDetailBean>> doSearch(@Path("page") int page, @Query("k") String k);

    /**
     * 常用网址
     */
    @GET("friend/json")
    Observable<HttpResult<List<FrequentlyBean>>> getFrequently();

    /**
     * 收藏文章
     *
     * @param id
     * @return
     */
    @POST("lg/collect/{id}/json")
    Observable<HttpResult<String>> doCollect(@Path("id") int id);

    /**
     * 文章列表中取消收藏
     *
     * @param id
     * @return
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<HttpResult<String>> doCancelCollect(@Path("id") int id);

    /**
     * 收藏列表中取消收藏
     *
     * @param id
     * @param originId
     * @return
     */
    @POST("lg/uncollect/{id}/json")
    Observable<HttpResult<String>> doCollectCancel(@Path("id") int id, @Query("originId") int originId);

    @GET("banner/json")
    Observable<HttpResult<List<BannerBean>>> getBanner();

    @GET("user/logout/json")
    Observable<HttpResult<LoginOutBean>> doLoginOut();

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @POST("user/login")
    Observable<HttpResult<LoginBean>> doLogin(@Query("username") String username, @Query("password") String password);

    /**
     * 注册
     *
     * @param username
     * @param password
     * @return
     */
    @POST("user/register")
    Observable<HttpResult<LoginBean>> doRegister(@Query("username") String username, @Query("password") String password, @Query("repassword") String repassword);

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
