package com.example.a74099.wanandroid.model.system;

import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.RxPresenter;

public class SystemPresenter extends RxPresenter<SystemContract.View> implements SystemContract.Presenter {
    ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    @Override
    public void getSystem() {

    }
}
