package com.example.a74099.wanandroid.model.classify;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;
import com.example.a74099.wanandroid.bean.ClassifyBean;
import com.example.a74099.wanandroid.bean.ClassifyTitleBean;
import com.example.a74099.wanandroid.model.classify.fragment.DetailFragment;
import com.example.a74099.wanandroid.net.util.NetworkType;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目分类
 */
public class ClassifyFragment extends BaseFragment<ClassifyPresenter> implements ClassifyContract.View {
    private TabLayout tab_layout;
    private ViewPager class_view_pager;
    private List<String> mList;
    private List<Fragment> mFragmentList;
    private SlidingTabLayout sliding_tab_layout;
    private List<ClassifyTitleBean> classifyBeanList;

    @Override
    protected ClassifyPresenter createPresenter() {
        return new ClassifyPresenter();
    }

    /**
     * 断网重连
     *
     * @param networkType
     */
    @Override
    public void onNetConnected(NetworkType networkType) {
        super.onNetConnected(networkType);
        if (classifyBeanList.size()==0) {
            mPresenter.getClassifyTitle();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initEventAndData(View view) {
        mList = new ArrayList<>();
        classifyBeanList = new ArrayList<>();
        mFragmentList = new ArrayList<>();
        mPresenter.getClassifyTitle();
        class_view_pager = view.findViewById(R.id.class_view_pager);
        sliding_tab_layout = view.findViewById(R.id.sliding_tab_layout);

    }


    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {

    }

    @Override
    public void getClassifySuccess(ClassifyBean model) {

    }

    @Override
    public void getClassifyTitleSuccess(List<ClassifyTitleBean> model) {
        classifyBeanList = model;
        for (ClassifyTitleBean classifyTitleBean : model) {
            mList.add(classifyTitleBean.getName());
        }
        class_view_pager.setAdapter(new BaseAdapter(getChildFragmentManager(), mList, mFragmentList));
        sliding_tab_layout.setViewPager(class_view_pager);
    }

    @Override
    public void doCollectSuccess() {

    }

    @Override
    public void doCancelCollectSuccess() {

    }

    @Override
    public void doCollectError() {
//        ToastUtil.showShort(getActivity(), "登录过期，请重新登录");
//        ToolUtils.logout(getActivity());
//        LoginActivity.startAction(getActivity());
    }

    @Override
    public void doCancelCollectError() {
//        ToastUtil.showShort(getActivity(), "登录过期，请重新登录");
//        ToolUtils.logout(getActivity());
//        LoginActivity.startAction(getActivity());
    }

    @Override
    public void showError(String msg) {

    }

    class BaseAdapter extends FragmentStatePagerAdapter {


        private List<String> list;
        private List<Fragment> fragmentList;

        public BaseAdapter(FragmentManager fm, List<String> list, List<Fragment> fragmentList) {
            super(fm);
            this.list = list;
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return new DetailFragment(classifyBeanList.get(position).getId());
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }

    }
}
