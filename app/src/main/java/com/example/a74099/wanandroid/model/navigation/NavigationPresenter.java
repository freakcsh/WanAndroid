package com.example.a74099.wanandroid.model.navigation;

import android.util.Log;

import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.bean.NavigationBean;
import com.example.a74099.wanandroid.net.ApiCallback;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.HttpResultFunc;
import com.example.a74099.wanandroid.net.RxPresenter;
import com.example.a74099.wanandroid.net.SubscriberCallBack;

import java.util.List;

import rx.Observable;

public class NavigationPresenter extends RxPresenter<NavigationContract.View> implements NavigationContract.Presenter {
    ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    @Override
    public void getNavigation() {
        Observable observable = apiServer.getNavigation().map(new HttpResultFunc<List<NavigationBean>>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<List<NavigationBean>>() {
            @Override
            public void onSuccess(List<NavigationBean> model) {
                mView.getNavigationSuccess(model);
                Log.e("freak", model.toString());
            }

            @Override
            public void onFailure(String msg) {

            }
        }));
    }
}
