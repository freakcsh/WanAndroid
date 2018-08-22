package com.example.a74099.wanandroid.model.classify;

import com.example.a74099.wanandroid.bean.ClassifyBean;
import com.example.a74099.wanandroid.bean.ClassifyTitleBean;
import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

import java.util.List;

public interface ClassifyContract {
    interface View extends BaseView {
        void getClassifySuccess(ClassifyBean model);
        void getClassifyTitleSuccess(List<ClassifyTitleBean> model);
    }

    interface Presenter extends BasePresenter<View> {
        void getClassify(String curPage,String cid);
        void getClassifyTitle();
    }
}
