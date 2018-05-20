package com.example.a74099.wanandroid.model.myself;

import android.view.View;

import com.example.a74099.wanandroid.base.BaseFragment;

public class MyselfFragment extends BaseFragment<MyselfPresenter> implements MyselfContract.View {
    @Override
    protected MyselfPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return 0;
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
