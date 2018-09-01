package com.example.a74099.wanandroid.model.home;

import com.example.a74099.wanandroid.bean.ArticleListBean;
import com.example.a74099.wanandroid.bean.BannerBean;
import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

import java.util.List;

public interface HomePageContract {
    interface View extends BaseView {
        void doCollectSuccess();

        void getBannerSuccess(List<BannerBean> list);

        void getBannerError(String msg);

        void getArticleSuccess(ArticleListBean articleListBean);

        void getArticleError(String msg);

        void doCancelCollectSuccess();

        void doCollectError();

        void doCancelCollectError();
    }

    interface Presenter extends BasePresenter<View> {
        void doCollect(int id);

        void getBanner();

        void getArticle(String curPage);

        void doCancelCollect(int id);
    }
}
