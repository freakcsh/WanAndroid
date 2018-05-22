package com.example.a74099.wanandroid.model.home;

import android.view.View;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;

public class HomepageFragment extends BaseFragment<HomepagePresenter> implements HomePageContract.View {
    @Override
    protected HomepagePresenter createPresenter() {
        return new HomepagePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
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
