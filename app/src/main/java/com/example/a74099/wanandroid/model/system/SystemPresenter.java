package com.example.a74099.wanandroid.model.system;

import android.util.Log;

import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.bean.SystemClassifyBean;
import com.example.a74099.wanandroid.bean.SystemDetailBean;
import com.example.a74099.wanandroid.net.ApiCallback;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.HttpResultFunc;
import com.example.a74099.wanandroid.net.RxPresenter;
import com.example.a74099.wanandroid.net.SubscriberCallBack;
import com.example.a74099.wanandroid.util.ToolUtils;

import java.util.List;

import rx.Observable;

public class SystemPresenter extends RxPresenter<SystemContract.View> implements SystemContract.Presenter {
    ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    @Override
    public void getSystem() {
        Observable observable = apiServer.getSystemClassify().map(new HttpResultFunc<List<SystemClassifyBean>>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<List<SystemClassifyBean>>() {
            @Override
            public void onSuccess(List<SystemClassifyBean> model) {
                mView.getSystemSuccess(model);
            }

            @Override
            public void onFailure(String msg) {

            }
        }));
    }

    public void getClassifyDetail(String curPage, String cid) {
        String page = ToolUtils.subtract(curPage, "1");
        Observable observable = apiServer.getClassifyDetail("article/list/"+page+"/json",cid).map(new HttpResultFunc<SystemDetailBean>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<SystemDetailBean>() {
            @Override
            public void onSuccess(SystemDetailBean model) {
                mView.getClassifyDetailSuccess(model);
                Log.e("freak",model.toString());
            }

            @Override
            public void onFailure(String msg) {

            }
        }));
    }

    /**
     * 文章列表中取消收藏
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

            }
        }));
    }

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

            }
        }));
    }
}
