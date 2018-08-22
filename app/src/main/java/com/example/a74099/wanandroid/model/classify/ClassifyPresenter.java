package com.example.a74099.wanandroid.model.classify;

import android.util.Log;

import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.bean.ClassifyBean;
import com.example.a74099.wanandroid.bean.ClassifyTitleBean;
import com.example.a74099.wanandroid.net.ApiCallback;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.HttpResultFunc;
import com.example.a74099.wanandroid.net.RxPresenter;
import com.example.a74099.wanandroid.net.SubscriberCallBack;
import com.example.a74099.wanandroid.util.ToolUtils;

import java.util.List;

import rx.Observable;

public class ClassifyPresenter extends RxPresenter<ClassifyContract.View> implements ClassifyContract.Presenter {
    ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    @Override
    public void getClassify(String curPage,String cid) {
        String page= ToolUtils.subtract(curPage,"1");
        Observable observable = apiServer.getClassifyDetail("project/list/" + page + "/json?cid="+cid).map(new HttpResultFunc<ClassifyBean>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<ClassifyBean>() {
            @Override
            public void onSuccess(ClassifyBean model) {
                mView.getClassifySuccess(model);
                Log.e("freak", model.toString());
            }

            @Override
            public void onFailure(String msg) {

            }
        }));
    }

    @Override
    public void getClassifyTitle() {
        Observable observable=apiServer.getClassifyTitle().map(new HttpResultFunc<List<ClassifyTitleBean>>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<List<ClassifyTitleBean>>() {
            @Override
            public void onSuccess(List<ClassifyTitleBean> model) {
                mView.getClassifyTitleSuccess(model);
                Log.e("freak", model.toString());
            }

            @Override
            public void onFailure(String msg) {

            }
        }));
    }
}
