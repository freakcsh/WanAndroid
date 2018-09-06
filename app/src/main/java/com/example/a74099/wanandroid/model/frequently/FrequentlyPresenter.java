package com.example.a74099.wanandroid.model.frequently;

import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.bean.FrequentlyBean;
import com.example.a74099.wanandroid.net.ApiCallback;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.HttpResultFunc;
import com.example.a74099.wanandroid.net.RxPresenter;
import com.example.a74099.wanandroid.net.SubscriberCallBack;

import java.util.List;

import rx.Observable;

/**
 * Created by 74099 on 2018/9/6.
 */

public class FrequentlyPresenter extends RxPresenter<FrequentlyContract.View> implements FrequentlyContract.Presenter {
    ApiServer mApiServer = HttpMethods.getInstance().create(ApiServer.class);

    @Override
    public void getFrequently() {
        Observable observable=mApiServer.getFrequently().map(new HttpResultFunc<List<FrequentlyBean>>());
        addSubscription(observable,new SubscriberCallBack(new ApiCallback<List<FrequentlyBean>>() {
            @Override
            public void onSuccess(List<FrequentlyBean> model) {
                mView.getFrequentlySuccess(model);
            }

            @Override
            public void onFailure(String msg) {

            }
        }));
    }
}
