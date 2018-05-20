package com.example.a74099.wanandroid.model.classify;

import android.view.View;

import com.example.a74099.wanandroid.base.BaseFragment;

public class ClassifyFragment extends BaseFragment<ClassifyPresenter> implements ClassifyContract.View{
    @Override
    protected ClassifyPresenter createPresenter() {
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
    public void getClassifySuccess() {

    }

    @Override
    public void showError(String msg) {

    }
}
