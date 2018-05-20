package com.example.a74099.wanandroid.model.system;

import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

public interface SystemContract {
    interface View extends BaseView {
        void getSystemSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getSystem();
    }
}
