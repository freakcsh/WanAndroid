package com.example.a74099.wanandroid.model.classify;

import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.RxPresenter;
import com.example.a74099.wanandroid.util.ToolUtils;

public class ClassifyPresenter extends RxPresenter<ClassifyContract.View> implements ClassifyContract.Presenter {
    ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    @Override
    public void getClassify(String curPage) {
        String page= ToolUtils.subtract(curPage,"1");
    }
}
