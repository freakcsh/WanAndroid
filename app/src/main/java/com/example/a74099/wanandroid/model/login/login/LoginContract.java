package com.example.a74099.wanandroid.model.login.login;

import com.example.a74099.wanandroid.bean.LoginBean;
import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

/**
 * Created by 74099 on 2018/8/29.
 */

public interface LoginContract {
    interface View extends BaseView {
        void doLoginSuccess(LoginBean loginBean);

        void doLoginError(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void doLogin(String username,String password);
    }
}
