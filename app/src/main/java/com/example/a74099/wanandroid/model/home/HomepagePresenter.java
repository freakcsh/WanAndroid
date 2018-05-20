package com.example.a74099.wanandroid.model.home;

import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.RxPresenter;

public class HomepagePresenter extends RxPresenter<HomePageContract.View> implements HomePageContract.Presenter {
    ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    @Override
    public void getHomepage() {

    }
}
