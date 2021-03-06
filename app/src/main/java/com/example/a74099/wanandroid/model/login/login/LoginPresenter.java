package com.example.a74099.wanandroid.model.login.login;

import android.util.Log;

import com.example.a74099.wanandroid.app.ApiServer;
import com.example.a74099.wanandroid.bean.LoginBean;
import com.example.a74099.wanandroid.net.ApiCallback;
import com.example.a74099.wanandroid.net.HttpMethods;
import com.example.a74099.wanandroid.net.HttpResultFunc;
import com.example.a74099.wanandroid.net.RxPresenter;
import com.example.a74099.wanandroid.net.SubscriberCallBack;

import rx.Observable;

/**
 * Created by 74099 on 2018/8/29.
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {
    ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);
    @Override
    public void doLogin(String username,String password) {
        Observable observable = apiServer.doLogin(username,password).map(new HttpResultFunc<LoginBean>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<LoginBean>() {
            @Override
            public void onSuccess(LoginBean model) {
                mView.doLoginSuccess(model);
                Log.e("freak",model.toString());
            }

            @Override
            public void onFailure(String msg) {
                mView.doLoginError(msg);
            }
        }));
    }
}
