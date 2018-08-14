package com.example.a74099.wanandroid.model.classify.fragment;

import android.view.View;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;
import com.example.a74099.wanandroid.model.classify.ClassifyContract;
import com.example.a74099.wanandroid.model.classify.ClassifyPresenter;

public class DetailFragment extends BaseFragment<ClassifyPresenter> implements ClassifyContract.View {


    @Override
    protected ClassifyPresenter createPresenter() {
        return null;
    }
    public static DetailFragment newInstance() {
//        Bundle args = new Bundle();
//        args.putSerializable("marketTitleEntity", marketTitleEntity);
        DetailFragment fragment = new DetailFragment();
//        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.act_article_detail;
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
