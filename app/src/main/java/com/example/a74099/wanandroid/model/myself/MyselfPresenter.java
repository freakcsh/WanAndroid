package com.example.a74099.wanandroid.model.myself;

import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.RxPresenter;

public class MyselfPresenter extends RxPresenter<MyselfContract.View> implements MyselfContract.Presenter {
    ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    @Override
    public void getMyself() {

    }
}
