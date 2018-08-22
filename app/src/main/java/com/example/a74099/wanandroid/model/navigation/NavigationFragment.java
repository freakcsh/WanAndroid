package com.example.a74099.wanandroid.model.navigation;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;
import com.example.a74099.wanandroid.bean.NavigationBean;
import com.example.a74099.wanandroid.model.navigation.activity.NavigationActivity;
import com.example.a74099.wanandroid.model.navigation.adapter.NavigationLeftAdapter;
import com.example.a74099.wanandroid.model.navigation.adapter.NavigationRightAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View, NavigationLeftAdapter.onItemSelectedListener {
    private RecyclerView recycle_navigation_left;
    private RecyclerView recycle_navigation_right;
    private LinearLayout ll_navigation_no_data;
    private List<NavigationBean.Articles> articlesList;
    private NavigationLeftAdapter leftAdapter;
    private NavigationRightAdapter rightAdapter;
    private List<NavigationBean.Articles> list;
    private TextView headerView;
    private NavigationBean headMenu;
    private LinearLayout headerLayout;//右侧菜单栏最上面的菜单
    private boolean leftClickType = false;//左侧菜单点击引发的右侧联动
    private List<NavigationBean> dishMenuList;


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
        headerView = view.findViewById(R.id.right_menu_tv);
        headerLayout = view.findViewById(R.id.right_menu_item);
//        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return 0;
//            }
//        });
        recycle_navigation_right.setLayoutManager(new LinearLayoutManager(getActivity()));

        recycle_navigation_right.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(1) == false) {//无法下滑
                    showHeadView();
                    return;
                }
                View underView = null;
                if (dy > 0)
                    underView = recycle_navigation_right.findChildViewUnder(headerLayout.getX(), headerLayout.getMeasuredHeight() + 1);
                else
                    underView = recycle_navigation_right.findChildViewUnder(headerLayout.getX(), 0);
                if (underView != null && underView.getContentDescription() != null) {
                    int position = Integer.parseInt(underView.getContentDescription().toString());
                    NavigationBean menu = rightAdapter.getMenuOfMenuByPosition(position);

                    if (leftClickType || !menu.getName().equals(headMenu.getName())) {
                        if (dy > 0 && headerLayout.getTranslationY() <= 1 && headerLayout.getTranslationY() >= -1 * headerLayout.getMeasuredHeight() * 4 / 5 && !leftClickType) {// underView.getTop()>9
                            int dealtY = underView.getTop() - headerLayout.getMeasuredHeight();
                            headerLayout.setTranslationY(dealtY);
//                            Log.e(TAG, "onScrolled: "+headerLayout.getTranslationY()+"   "+headerLayout.getBottom()+"  -  "+headerLayout.getMeasuredHeight() );
                        } else if (dy < 0 && headerLayout.getTranslationY() <= 0 && !leftClickType) {
                            headerView.setText(menu.getName());
                            int dealtY = underView.getBottom() - headerLayout.getMeasuredHeight();
                            headerLayout.setTranslationY(dealtY);
//                            Log.e(TAG, "onScrolled: "+headerLayout.getTranslationY()+"   "+headerLayout.getBottom()+"  -  "+headerLayout.getMeasuredHeight() );
                        } else {
                            headerLayout.setTranslationY(0);
                            headMenu = menu;
                            headerView.setText(headMenu.getName());
                            for (int i = 0; i < dishMenuList.size(); i++) {
                                if (dishMenuList.get(i) == headMenu) {
                                    leftAdapter.setSelectedNum(i);
                                    break;
                                }
                            }
                            if (leftClickType) leftClickType = false;
                            Log.e("TAG", "onScrolled: " + menu.getName());
                        }
                    }
                }
            }
        });
    }

    private void showHeadView() {
        headerLayout.setTranslationY(0);
        View underView = recycle_navigation_right.findChildViewUnder(headerView.getX(), 0);
        if (underView != null && underView.getContentDescription() != null) {
            int position = Integer.parseInt(underView.getContentDescription().toString());
            NavigationBean menu = rightAdapter.getMenuOfMenuByPosition(position + 1);
            headMenu = menu;
            headerView.setText(headMenu.getName());
            for (int i = 0; i < dishMenuList.size(); i++) {
                if (dishMenuList.get(i) == headMenu) {
                    leftAdapter.setSelectedNum(i);
                    break;
                }
            }
        }
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
    public void onDestroy() {
        super.onDestroy();
        leftAdapter.removeItemSelectedListener(this);
    }

    @Override
    public void getNavigationSuccess(List<NavigationBean> navigationBeanList) {
        dishMenuList = navigationBeanList;
//        if (navigationBeanList != null && navigationBeanList.size() != 0) {
//            for (int i = 0; i < navigationBeanList.size(); i++) {
//                articlesList.addAll(navigationBeanList.get(i).getArticles());
//            }
        if (leftAdapter == null) {
            leftAdapter = new NavigationLeftAdapter(getActivity(), navigationBeanList);
            recycle_navigation_left.setAdapter(leftAdapter);
        } else {
            leftAdapter.setData(navigationBeanList);
            leftAdapter.notifyDataSetChanged();
        }
//                ll_navigation_no_data.setVisibility(View.GONE);
//                recycle_navigation_right.setVisibility(View.VISIBLE);
        if (rightAdapter == null) {
            rightAdapter = new NavigationRightAdapter(getActivity(), navigationBeanList);
            recycle_navigation_right.setAdapter(rightAdapter);
            rightAdapter.setSelectInterface(new NavigationRightAdapter.SelectInterface() {
                @Override
                public void doSelect(NavigationBean.Articles articles) {
                    Intent intent = new Intent(getActivity(), NavigationActivity.class);
                    intent.putExtra("url", articles.getLink());
                    intent.putExtra("title", articles.getTitle());
                    startActivity(intent);
                }
            });
        } else {
            rightAdapter.setData(navigationBeanList);
            rightAdapter.notifyDataSetChanged();
        }
        leftAdapter.addItemSelectedListener(this);
        initHeadView();
//            }

    }

    private void initHeadView() {
        headMenu = rightAdapter.getMenuOfMenuByPosition(0);
        headerLayout.setContentDescription("0");
        headerView.setText(headMenu.getName());
    }

    @Override
    public void showError(String msg) {

    }

    /**
     * 左边菜单RecycleView接口
     *
     * @param postion
     * @param menu
     */
    @Override
    public void onLeftItemSelected(int postion, NavigationBean menu) {
        int sum = 0;
        for (int i = 0; i < postion; i++) {
            sum += dishMenuList.get(i).getArticles().size() + 1;
        }
        LinearLayoutManager layoutManager = (LinearLayoutManager) recycle_navigation_right.getLayoutManager();
        layoutManager.scrollToPositionWithOffset(sum, 0);
        leftClickType = true;
    }
}
