package com.example.a74099.wanandroid.model.system;

import com.example.a74099.wanandroid.bean.SystemClassifyBean;
import com.example.a74099.wanandroid.bean.SystemDetailBean;
import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

import java.util.List;

public interface SystemContract {
    interface View extends BaseView {
        void getSystemSuccess(List<SystemClassifyBean> systemClassifyBeanList);
        void getClassifyDetailSuccess(SystemDetailBean systemDetailBean);
    }

    interface Presenter extends BasePresenter<View> {
        void getSystem();
        void getClassifyDetail(String curPage, String  cid );
    }
}
