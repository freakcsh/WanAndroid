package com.example.a74099.wanandroid.model.login.register;

import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

/**
 * Created by 74099 on 2018/8/29.
 */

public interface RegisterContract {
    interface View extends BaseView {
        void doRegisterSuccess();
        void doRegisterError(String msg);

    }

    interface Presenter extends BasePresenter<View> {
        void doRegister(String username,String password,String repassword);
    }
}
