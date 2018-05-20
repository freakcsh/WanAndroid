package com.example.a74099.wanandroid.model;


import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.RxPresenter;

public class MainPresenter extends RxPresenter<MainContract.VIew> implements MainContract.Presenter {
    ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    @Override
    public void getMain() {

    }
}
