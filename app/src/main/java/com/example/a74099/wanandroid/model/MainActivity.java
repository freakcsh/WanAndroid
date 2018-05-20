package com.example.a74099.wanandroid.model;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.VIew {

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void getSuccess() {

    }

    @Override
    public void showError(String msg) {

    }
}
