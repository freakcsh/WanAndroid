package com.example.a74099.wanandroid.model.myself;

import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

public interface MyselfContract {
    interface View extends BaseView {
        void getMyselfSuccess();


    }

    interface Presenter extends BasePresenter<View> {
        void getMyself();


    }
}
