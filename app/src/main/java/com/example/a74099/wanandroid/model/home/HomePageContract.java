package com.example.a74099.wanandroid.model.home;

import com.example.a74099.wanandroid.bean.ArticleListBean;
import com.example.a74099.wanandroid.bean.BannerBean;
import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

import java.util.List;

public interface HomePageContract {
    interface View extends BaseView {
        void getHomepageSuccess();

        void getBannerSuccess(List<BannerBean> list);

        void getBannerError(String msg);

        void getArticleSuccess(ArticleListBean articleListBean);

        void getArticleError(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void getHomepage();
    }
}
