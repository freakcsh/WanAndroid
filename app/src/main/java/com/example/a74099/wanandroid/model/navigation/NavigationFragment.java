package com.example.a74099.wanandroid.model.navigation;

import android.view.View;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;

/**
 * 导航
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View{
    @Override
    protected NavigationPresenter createPresenter() {
        return new NavigationPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
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
