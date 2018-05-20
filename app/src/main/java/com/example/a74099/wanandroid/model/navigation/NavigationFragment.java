package com.example.a74099.wanandroid.model.navigation;

import android.view.View;

import com.example.a74099.wanandroid.base.BaseFragment;

public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View{
    @Override
    protected NavigationPresenter createPresenter() {
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
    public void getNavigationSuccess() {

    }

    @Override
    public void showError(String msg) {

    }
}
