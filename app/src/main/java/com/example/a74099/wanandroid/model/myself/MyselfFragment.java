package com.example.a74099.wanandroid.model.myself;

import android.view.View;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;

/**
 * 我的
 */
public class MyselfFragment extends BaseFragment<MyselfPresenter> implements MyselfContract.View {
    @Override
    protected MyselfPresenter createPresenter() {
        return new MyselfPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myself;
    }

    @Override
    protected void initEventAndData(View view) {

    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {

    }

    @Override
    public void getMyselfSuccess() {

    }

    @Override
    public void showError(String msg) {

    }
}
