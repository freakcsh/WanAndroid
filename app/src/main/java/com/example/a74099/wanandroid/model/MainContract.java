package com.example.a74099.wanandroid.model;

import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

public interface MainContract {
    interface VIew extends BaseView {
        void getSuccess();
    }

    interface Presenter extends BasePresenter<VIew> {
        void getMain();
    }
}
