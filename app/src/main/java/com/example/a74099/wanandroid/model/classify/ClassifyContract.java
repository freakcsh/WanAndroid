package com.example.a74099.wanandroid.model.classify;

import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

public interface ClassifyContract {
    interface View extends BaseView {
        void getClassifySuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getClassify(String curPage);
    }
}
