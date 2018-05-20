package com.example.a74099.wanandroid.model.navigation;

import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

public interface NavigationContract {
    interface View extends BaseView {
        void getNavigationSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getNavigation();
    }
}
