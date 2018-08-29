package com.example.a74099.wanandroid.model.login.register;

import android.view.View;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;

/**
 * Created by 74099 on 2018/8/29.
 */

public class RegisterFragment extends BaseFragment<RegisterPresenter> implements RegisterContract.View {
    @Override
    public void showError(String msg) {

    }
    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }
    @Override
    protected RegisterPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
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
}
