package com.example.a74099.wanandroid.model.navigation;

import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.RxPresenter;

public class NavigationPresenter extends RxPresenter<NavigationContract.View> implements NavigationContract.Presenter {
    ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    @Override
    public void getNavigation() {

    }
}
