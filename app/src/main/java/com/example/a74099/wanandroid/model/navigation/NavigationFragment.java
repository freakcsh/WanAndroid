package com.example.a74099.wanandroid.model.navigation;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;
import com.example.a74099.wanandroid.bean.NavigationBean;
import com.example.a74099.wanandroid.model.navigation.adapter.NavigationLeftAdapter;
import com.example.a74099.wanandroid.model.navigation.adapter.NavigationRightAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {
    private RecyclerView recycle_navigation_left;
    private XRecyclerView recycle_navigation_right;
    private LinearLayout ll_navigation_no_data;
    private List<NavigationBean.Articles> articlesList;
    private NavigationLeftAdapter leftAdapter;
    private NavigationRightAdapter rightAdapter;
    private List<NavigationBean.Articles> list;

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
        list = new ArrayList<>();
        articlesList = new ArrayList<>();
        recycle_navigation_left = view.findViewById(R.id.recycle_navigation_left);
        recycle_navigation_left.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycle_navigation_right = view.findViewById(R.id.recycle_navigation_right);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return 0;
//            }
//        });
        recycle_navigation_right.setLayoutManager(manager);
        ll_navigation_no_data = view.findViewById(R.id.ll_navigation_no_data);
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getNavigation();
    }

    @Override
    public void getNavigationSuccess(List<NavigationBean> navigationBeanList) {
        if (navigationBeanList != null && navigationBeanList.size() != 0) {
            for (int i = 0; i < navigationBeanList.size(); i++) {
                articlesList.addAll(navigationBeanList.get(i).getArticles());
            }
                if (leftAdapter == null) {
                    leftAdapter = new NavigationLeftAdapter(getActivity(), navigationBeanList);
                    recycle_navigation_left.setAdapter(leftAdapter);
                } else {
                    leftAdapter.setData(navigationBeanList);
                    leftAdapter.notifyDataSetChanged();
                }
                ll_navigation_no_data.setVisibility(View.GONE);
                recycle_navigation_right.setVisibility(View.VISIBLE);
                if (rightAdapter == null) {
                    rightAdapter = new NavigationRightAdapter(getActivity(), articlesList);
                    recycle_navigation_right.setAdapter(rightAdapter);
                } else {
                    rightAdapter.setData(articlesList);
                    rightAdapter.notifyDataSetChanged();
                }
            }

    }

    @Override
    public void showError(String msg) {

    }
}
