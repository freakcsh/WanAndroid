package com.example.a74099.wanandroid.model.myself.activity.collect;

import android.util.Log;

import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.bean.CollectBean;
import com.example.a74099.wanandroid.net.ApiCallback;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.HttpResultFunc;
import com.example.a74099.wanandroid.net.RxPresenter;
import com.example.a74099.wanandroid.net.SubscriberCallBack;
import com.example.a74099.wanandroid.util.ToolUtils;

import rx.Observable;

/**
 * Created by 74099 on 2018/8/29.
 */

public class CollectPresenter extends RxPresenter<CollectContract.View> implements CollectContract.Presenter {
    ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    @Override
    public void getCollectList(String curPage) {
        String page = ToolUtils.subtract(curPage, "1");
        Observable observable = apiServer.getCollect("lg/collect/list/" + page + "/json").map(new HttpResultFunc<CollectBean>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<CollectBean>() {
            @Override
            public void onSuccess(CollectBean model) {
                mView.getCollectListSuccess(model);
                Log.e("freak", model.toString());
            }

            @Override
            public void onFailure(String msg) {
                mView.getCollectError(msg);
            }
        }));
    }

    @Override
    public void doCollectCancel(int id, int originId) {
        Observable observable = apiServer.doCollectCancel(id,originId).map(new HttpResultFunc<String>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<String>() {
            @Override
            public void onSuccess(String model) {
                mView.doCollectCancelSuccess();
            }

            @Override
            public void onFailure(String msg) {

            }
        }));
    }
}
