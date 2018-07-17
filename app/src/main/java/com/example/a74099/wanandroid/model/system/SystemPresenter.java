package com.example.a74099.wanandroid.model.system;

import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.bean.SystemClassifyBean;
import com.example.a74099.wanandroid.net.ApiCallback;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.HttpResultFunc;
import com.example.a74099.wanandroid.net.RxPresenter;
import com.example.a74099.wanandroid.net.SubscriberCallBack;

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
}
