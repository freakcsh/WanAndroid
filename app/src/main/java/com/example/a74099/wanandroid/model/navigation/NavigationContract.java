package com.example.a74099.wanandroid.model.navigation;

import com.example.a74099.wanandroid.bean.NavigationBean;
import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

import java.util.List;

public interface NavigationContract {
    interface View extends BaseView {
        void getNavigationSuccess(List<NavigationBean> navigationBeanList);
    }

    interface Presenter extends BasePresenter<View> {
        void getNavigation();
    }
}
