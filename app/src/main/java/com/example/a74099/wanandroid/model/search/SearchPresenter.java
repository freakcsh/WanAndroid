package com.example.a74099.wanandroid.model.search;

import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.bean.HistoryData;
import com.example.a74099.wanandroid.bean.TopSearchData;
import com.example.a74099.wanandroid.net.ApiCallback;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.HttpResultFunc;
import com.example.a74099.wanandroid.net.RxPresenter;
import com.example.a74099.wanandroid.net.SubscriberCallBack;

import java.util.List;

import rx.Observable;

/**
 * Created by 74099 on 2018/9/5.
 */

public class SearchPresenter extends RxPresenter<SearchContract.VIew> implements SearchContract.Presenter {
    ApiServer mApiServer = HttpMethods.getInstance().create(ApiServer.class);

    @Override
    public List<HistoryData> loadAllHistoryData() {
        return null;
    }

    @Override
    public void addHistoryData(String data) {
        mView.judgeToTheSearchListActivity();
    }
    @Override
    public void clearHistoryData() {

    }
    @Override
    public void getTopSearchData() {
        Observable observable=mApiServer.doSearchHot().map(new HttpResultFunc<List<TopSearchData>>());
        addSubscription(observable,new SubscriberCallBack(new ApiCallback<List<TopSearchData>>() {
            @Override
            public void onSuccess(List<TopSearchData> model) {
                mView.showTopSearchData(model);
            }

            @Override
            public void onFailure(String msg) {

            }
        }));
    }


}
