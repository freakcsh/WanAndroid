package com.example.a74099.wanandroid.model.myself;

import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.bean.LoginOutBean;
import com.example.a74099.wanandroid.net.ApiCallback;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.HttpResultFunc;
import com.example.a74099.wanandroid.net.RxPresenter;
import com.example.a74099.wanandroid.net.SubscriberCallBack;

import rx.Observable;

public class MyselfPresenter extends RxPresenter<MyselfContract.View> implements MyselfContract.Presenter {
    ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    @Override
    public void getMyself() {

    }

}
