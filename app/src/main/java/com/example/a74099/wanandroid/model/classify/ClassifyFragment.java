package com.example.a74099.wanandroid.model.classify;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;
import com.example.a74099.wanandroid.model.classify.fragment.DetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目分类
 */
public class ClassifyFragment extends BaseFragment<ClassifyPresenter> implements ClassifyContract.View{
    private TabLayout tab_layout;
    private ViewPager class_view_pager;
    private List<String> mList;
    private List<Fragment> mFragmentList;
    @Override
    protected ClassifyPresenter createPresenter() {
        return new ClassifyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initEventAndData(View view) {
        mList=new ArrayList<>();
        mList.add("我们");
        mList.add("我们");
        mList.add("我们");
        mList.add("我们");
        mList.add("我们");
        mList.add("我们");
        mList.add("我们");
        mList.add("我们");
        mList.add("我们");
        mList.add("我们");
        mList.add("我们");
        mFragmentList=new ArrayList<>();
        mFragmentList.add(new DetailFragment());
        mFragmentList.add(new DetailFragment());
        mFragmentList.add(new DetailFragment());
        mFragmentList.add(new DetailFragment());
        mFragmentList.add(new DetailFragment());
        mFragmentList.add(new DetailFragment());
        mFragmentList.add(new DetailFragment());
        mFragmentList.add(new DetailFragment());
        mFragmentList.add(new DetailFragment());
        mFragmentList.add(new DetailFragment());
        mFragmentList.add(new DetailFragment());

        tab_layout=view.findViewById(R.id.tab_layout);
        class_view_pager=view.findViewById(R.id.class_view_pager);
        class_view_pager.setAdapter(new BaseAdapter(getChildFragmentManager(),mList,mFragmentList));
        tab_layout.setupWithViewPager(class_view_pager);
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
     class BaseAdapter extends FragmentPagerAdapter {


        private List<String> list;
        private List<Fragment> fragmentList;

        public BaseAdapter(FragmentManager fm, List<String> list, List<Fragment> fragmentList) {
            super(fm);
            this.list = list;
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
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
