package com.example.a74099.wanandroid.model.search.detail;

import com.example.a74099.wanandroid.bean.SearchDetailBean;
import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

/**
 * Created by 74099 on 2018/9/6.
 */

public interface SearchDetailContract {
    interface Viev extends BaseView {
        void getSearchDetailSuccess(SearchDetailBean searchDetailBeanList);

        void getSearchDetailError(String msg);

        void doCancelCollectSuccess();

        void doCollectError();

        void doCancelCollectError();

        void doCollectSuccess();
    }

    interface Presenter extends BasePresenter<Viev> {
        void getSearchDetail(int page,String k);

        void doCancelCollect(int id);

        void doCollect(int id);
    }
}
