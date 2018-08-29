package com.example.a74099.wanandroid.model.login.login;

import android.view.View;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;

/**
 * Created by 74099 on 2018/8/29.
 */

public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginContract.View {
    @Override
    public void showError(String msg) {

    }
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }
    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
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
