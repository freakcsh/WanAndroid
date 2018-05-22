package com.example.a74099.wanandroid.model.system;

import android.view.View;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;

public class SystemFragment extends BaseFragment<SystemPresenter> implements SystemContract.View {
    @Override
    protected SystemPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_system;
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
    public void getSystemSuccess() {

    }

    @Override
    public void showError(String msg) {

    }
}
