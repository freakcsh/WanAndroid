package com.example.a74099.wanandroid.model.myself;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;

/**
 * 我的
 */
public class MyselfFragment extends BaseFragment<MyselfPresenter> implements MyselfContract.View {
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private LinearLayout headLayout;
    private Toolbar toolbar;

    @Override
    protected MyselfPresenter createPresenter() {
        return new MyselfPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myself;
    }

    @Override
    protected void initEventAndData(View view) {
        initView(view);
        setToolBarReplaceActionBar();
        setTitleToCollapsingToolbarLayout();
    }

    private void initView(View view) {
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar_layout);
        appBarLayout = view.findViewById(R.id.app_bar);
        headLayout = view.findViewById(R.id.head_layout);
        toolbar = view.findViewById(R.id.toolbar);

    }

    /**
     * 用toolBar替换ActionBar
     */
    private void setToolBarReplaceActionBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // onBackPressed();//结束程序
            }
        });
    }


    /**
     * 使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，
     * 设置到Toolbar上则不会显示
     */
    private void setTitleToCollapsingToolbarLayout() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -headLayout.getHeight() ) {
                    collapsingToolbarLayout.setTitle("黄晓果");
                    toolbar.setVisibility(View.VISIBLE);
                    toolbar.setTitle("sjafjs");
                    //使用下面两个CollapsingToolbarLayout的方法设置展开透明->折叠时你想要的颜色
                    collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    collapsingToolbarLayout.setTitle("");
                    toolbar.setVisibility(View.GONE);

                }
            }
        });
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {

    }

    @Override
    public void getMyselfSuccess() {

    }

    @Override
    public void showError(String msg) {

    }
}
