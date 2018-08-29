package com.example.a74099.wanandroid.model.myself.activity.collect;

import com.example.a74099.wanandroid.bean.CollectBean;
import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

/**
 * Created by 74099 on 2018/8/29.
 */

public interface CollectContract {
    interface View extends BaseView {
        void getCollectListSuccess(CollectBean collectBean);

        void getCollectError(String mse);
    }

    interface Presenter extends BasePresenter<View> {
        void getCollectList(String curPage);
    }
}
