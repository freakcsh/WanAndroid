package com.example.a74099.wanandroid.model.login;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.SimpleActivity;
import com.example.a74099.wanandroid.model.login.login.LoginFragment;
import com.example.a74099.wanandroid.model.login.register.RegisterFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 74099 on 2018/8/29.
 */

public class LoginActivity extends SimpleActivity {
    private TabLayout login_tab_layout;
    private ViewPager login_view_pager;
    private List<String> mList;
    private List<Fragment> mFragmentList;
    @Override
    protected int getLayout() {
        return R.layout.act_login;
    }
    public static void startAction(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initEventAndData() {
        initView();
    }

    private void initView() {
        mList = new ArrayList<>();
        mFragmentList = new ArrayList<>();

        mList.add(getString(R.string.login_login));
        mList.add(getString(R.string.login_register));

        mFragmentList.add(LoginFragment.newInstance());
        mFragmentList.add(RegisterFragment.newInstance());

        login_tab_layout = (TabLayout) findViewById(R.id.login_tab_layout);
        login_view_pager = (ViewPager) findViewById(R.id.login_view_pager);
        login_view_pager.setAdapter(new LoginActivity.MyAdapter(getSupportFragmentManager(), mList));
        login_tab_layout.setupWithViewPager(login_view_pager);
    }

    public void doIntent() {
        login_view_pager.setCurrentItem(0);
    }
    public class MyAdapter extends FragmentPagerAdapter {
        private List<String> list;

        public MyAdapter(FragmentManager fm, List<String> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
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
