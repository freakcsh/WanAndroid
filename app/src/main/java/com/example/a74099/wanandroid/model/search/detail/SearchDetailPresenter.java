package com.example.a74099.wanandroid.model.search.detail;

import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.bean.SearchDetailBean;
import com.example.a74099.wanandroid.net.ApiCallback;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.HttpResultFunc;
import com.example.a74099.wanandroid.net.RxPresenter;
import com.example.a74099.wanandroid.net.SubscriberCallBack;

import rx.Observable;

/**
 * Created by 74099 on 2018/9/6.
 */

public class SearchDetailPresenter extends RxPresenter<SearchDetailContract.Viev> implements SearchDetailContract.Presenter {
    ApiServer mApiServer = HttpMethods.getInstance().create(ApiServer.class);

    @Override
    public void getSearchDetail(int page,String k) {
        Observable observable=mApiServer.doSearch(page,k).map(new HttpResultFunc<SearchDetailBean>());
        addSubscription(observable,new SubscriberCallBack(new ApiCallback<SearchDetailBean>() {
            @Override
            public void onSuccess(SearchDetailBean model) {
                mView.getSearchDetailSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
                mView.getSearchDetailError(msg);
            }
        }));
    }


    /**
     * 收藏文章
     */
    @Override
    public void doCollect(int id) {
        Observable observable = mApiServer.doCollect(id).map(new HttpResultFunc<String>());
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
     * 文章列表中取消收藏
     *
     * @param id
     */
    @Override
    public void doCancelCollect(int id) {
        Observable observable = mApiServer.doCancelCollect(id).map(new HttpResultFunc<String>());
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
