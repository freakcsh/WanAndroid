package com.example.a74099.wanandroid.model.frequently;

import com.example.a74099.wanandroid.bean.FrequentlyBean;
import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

import java.util.List;

/**
 * Created by 74099 on 2018/9/6.
 */

public interface FrequentlyContract {
    interface View extends BaseView {
        void getFrequentlySuccess(List<FrequentlyBean> frequentlyBeanList);

        void getFrequentlyError(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void getFrequently();
    }
}
