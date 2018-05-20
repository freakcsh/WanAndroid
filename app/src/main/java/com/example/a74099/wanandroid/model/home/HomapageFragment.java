package com.example.a74099.wanandroid.model.home;

import android.view.View;

import com.example.a74099.wanandroid.base.BaseFragment;

public class HomapageFragment extends BaseFragment<HomepagePresenter> implements HomePageContract.View {
    @Override
    protected HomepagePresenter createPresenter() {
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
    public void getHomepageSuccess() {

    }

    @Override
    public void showError(String msg) {

    }
}
