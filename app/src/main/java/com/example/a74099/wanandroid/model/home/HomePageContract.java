package com.example.a74099.wanandroid.model.home;

import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

public interface HomePageContract {
    interface View extends BaseView {
        void getHomepageSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getHomepage();
    }
}
