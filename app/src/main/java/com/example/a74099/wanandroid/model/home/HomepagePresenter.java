package com.example.a74099.wanandroid.model.home;

import android.util.Log;

import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.bean.ArticleListBean;
import com.example.a74099.wanandroid.bean.BannerBean;
import com.example.a74099.wanandroid.net.ApiCallback;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.HttpResultFunc;
import com.example.a74099.wanandroid.net.RxPresenter;
import com.example.a74099.wanandroid.net.SubscriberCallBack;
import com.example.a74099.wanandroid.util.ToolUtils;

import java.util.List;

import rx.Observable;

public class HomepagePresenter extends RxPresenter<HomePageContract.View> implements HomePageContract.Presenter {
    ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    /**
     * 收藏文章
     */
    @Override
    public void doCollect(int id) {
        Observable observable = apiServer.doCollect(id).map(new HttpResultFunc<String>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<String>() {
            @Override
            public void onSuccess(String model) {
                mView.doCollectSuccess();
            }

            @Override
            public void onFailure(String msg) {
                mView.doCollectError();
            }
        }));
    }

    /**
     * 获取轮播图
     */
    public void getBanner() {
        Observable observable = apiServer.getBanner().map(new HttpResultFunc<List<BannerBean>>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<List<BannerBean>>() {
            @Override
            public void onSuccess(List<BannerBean> model) {
                mView.getBannerSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
                mView.getBannerError(msg);
            }
        }));
    }

    /**
     * 获取最新文章
     *
     * @param curPage
     */
    public void getArticle(String curPage) {
        String page = ToolUtils.subtract(curPage, "1");
        Observable observable = apiServer.getArticle("article/list/" + page + "/json").map(new HttpResultFunc<ArticleListBean>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<ArticleListBean>() {
            @Override
            public void onSuccess(ArticleListBean model) {
                mView.getArticleSuccess(model);
                Log.e("freak", model.toString());
            }

            @Override
            public void onFailure(String msg) {

            }
        }));
    }

    /**
     * 文章列表中取消收藏
     *
     * @param id
     */
    @Override
    public void doCancelCollect(int id) {
        Observable observable = apiServer.doCancelCollect(id).map(new HttpResultFunc<String>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<String>() {
            @Override
            public void onSuccess(String model) {
                mView.doCancelCollectSuccess();
            }

            @Override
            public void onFailure(String msg) {
                mView.doCancelCollectError();
            }
        }));
    }
}
