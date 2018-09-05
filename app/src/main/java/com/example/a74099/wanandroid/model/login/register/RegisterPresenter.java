package com.example.a74099.wanandroid.model.login.register;

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

public class RegisterPresenter extends RxPresenter<RegisterContract.View> implements RegisterContract.Presenter {
    ApiServer apiServer = HttpMethods.getInstance().create(ApiServer.class);

    @Override
    public void doRegister(String username, String password, String repassword) {
        Observable observable = apiServer.doRegister(username, password, repassword).map(new HttpResultFunc<LoginBean>());
        addSubscription(observable, new SubscriberCallBack(new ApiCallback<LoginBean>() {
            @Override
            public void onSuccess(LoginBean model) {
                mView.doRegisterSuccess();
            }

            @Override
            public void onFailure(String msg) {
mView.doRegisterError(msg);
            }
        }));
    }
}
