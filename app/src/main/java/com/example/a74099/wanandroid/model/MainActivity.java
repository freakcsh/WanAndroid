package com.example.a74099.wanandroid.model;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.app.App;
import com.example.a74099.wanandroid.base.BaseFragment;
import com.example.a74099.wanandroid.base.SimpleActivity;
import com.example.a74099.wanandroid.model.classify.ClassifyFragment;
import com.example.a74099.wanandroid.model.frequently.FrequentlyActivity;
import com.example.a74099.wanandroid.model.home.HomepageFragment;
import com.example.a74099.wanandroid.model.myself.MyselfFragment;
import com.example.a74099.wanandroid.model.myself.fingerprint.FingerPrintCheckActivity;
import com.example.a74099.wanandroid.model.navigation.NavigationFragment;
import com.example.a74099.wanandroid.model.search.SearchActivity;
import com.example.a74099.wanandroid.model.system.SystemFragment;
import com.example.a74099.wanandroid.util.ToastUtil;
import com.example.a74099.wanandroid.util.ToolUtils;

public class MainActivity extends SimpleActivity implements View.OnClickListener {
    private HomepageFragment mHomapageFragment = null;
    private SystemFragment mSystemFragment = null;
    private NavigationFragment mNavigationFragment = null;
    private ClassifyFragment mClassifyFragment = null;
    private MyselfFragment myselfFragment = null;
    private LinearLayout ll_homepage, ll_system, ll_navigation, ll_classify, ll_myself;
    private TextView tv_myself, tv_classify, tv_navigation, tv_system, tv_homepage;
    private ImageView img_myself, img_classify, img_navigation, img_system, img_homepage;
    private BaseFragment baseFragment = null;
    private final long BACKPRESS_TIME = 2000;
    private long lastTimeMillis;

    private RelativeLayout search, often;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    protected void initEventAndData() {
        initView();
        initOnClick();
        if (!ToolUtils.isNull(ToolUtils.getFingerState(this))) {
         FingerPrintCheckActivity.startAction(this);
        }
    }


    private void initOnClick() {
        ll_homepage.setOnClickListener(this);
        ll_system.setOnClickListener(this);
        ll_navigation.setOnClickListener(this);
        ll_classify.setOnClickListener(this);
        ll_myself.setOnClickListener(this);
        often.setOnClickListener(this);
        search.setOnClickListener(this);
        ll_homepage.performClick();
    }

    private void initView() {
        ll_homepage = findViewById(R.id.ll_homepage);
        ll_system = findViewById(R.id.ll_system);
        ll_navigation = findViewById(R.id.ll_navigation);
        ll_classify = findViewById(R.id.ll_classify);
        ll_myself = findViewById(R.id.ll_myself);

        tv_homepage = findViewById(R.id.tv_homepage);
        tv_system = findViewById(R.id.tv_system);
        tv_navigation = findViewById(R.id.tv_navigation);
        tv_classify = findViewById(R.id.tv_classify);
        tv_myself = findViewById(R.id.tv_myself);

        img_homepage = findViewById(R.id.img_homepage);
        img_system = findViewById(R.id.img_system);
        img_navigation = findViewById(R.id.img_navigation);
        img_classify = findViewById(R.id.img_classify);
        img_myself = findViewById(R.id.img_myself);
        search = findViewById(R.id.search);
        often = findViewById(R.id.often);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        myselfFragment.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setButtonBg(int i) {
        if (img_homepage.getVisibility() != View.VISIBLE) {
            img_homepage.setVisibility(View.VISIBLE);
        }
        if (img_system.getVisibility() != View.VISIBLE) {
            img_system.setVisibility(View.VISIBLE);
        }
        if (img_navigation.getVisibility() != View.VISIBLE) {
            img_navigation.setVisibility(View.VISIBLE);
        }
        if (tv_classify.getVisibility() != View.VISIBLE) {
            img_system.setVisibility(View.VISIBLE);
        }
        if (img_myself.getVisibility() != View.VISIBLE) {
            img_myself.setVisibility(View.VISIBLE);
        }
        tv_homepage.setSelected(false);
        tv_system.setSelected(false);
        tv_navigation.setSelected(false);
        tv_classify.setSelected(false);
        tv_myself.setSelected(false);

        img_homepage.setSelected(false);
        img_system.setSelected(false);
        img_navigation.setSelected(false);
        img_classify.setSelected(false);
        img_myself.setSelected(false);
        switch (i) {
            case 0:
                tv_homepage.setSelected(true);
                img_homepage.setSelected(true);
                break;
            case 1:
                tv_system.setSelected(true);
                img_system.setSelected(true);
                break;
            case 2:
                tv_navigation.setSelected(true);
                img_navigation.setSelected(true);
                break;
            case 3:
                tv_classify.setSelected(true);
                img_classify.setSelected(true);
                break;
            case 4:
                tv_myself.setSelected(true);
                img_myself.setSelected(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_homepage:
                setButtonBg(0);
                showHomepageFragment();
                break;
            case R.id.ll_system:
                setButtonBg(1);
                showSystemFragment();
                break;
            case R.id.ll_navigation:
                setButtonBg(2);
                showNavigationFragment();
                break;
            case R.id.ll_classify:
                setButtonBg(3);
                showClassifyFragment();
                break;
            case R.id.ll_myself:
                setButtonBg(4);
                showMyselfFragment();
                break;
            case R.id.search:
                SearchActivity.startAction(this);
                break;
            case R.id.often:
                FrequentlyActivity.startAction(this);
                break;
            default:
                break;
        }
    }

    private void showMyselfFragment() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (myselfFragment == null) {
            myselfFragment = new MyselfFragment();
        }
        if (myselfFragment.isAdded()) {
            transaction.hide(baseFragment).show(myselfFragment).commit();
        } else {
            if (baseFragment == null) {
                baseFragment = myselfFragment;
                transaction.add(R.id.main_frame, myselfFragment).commit();
            } else {
                transaction.hide(baseFragment).add(R.id.main_frame, myselfFragment).commit();
            }
        }
        setTitleTx(getString(R.string.main_myself));
        baseFragment = myselfFragment;
    }

    private void showClassifyFragment() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (mClassifyFragment == null) {
            mClassifyFragment = new ClassifyFragment();
        }
        if (mClassifyFragment.isAdded()) {
            transaction.hide(baseFragment).show(mClassifyFragment).commit();
        } else {
            if (baseFragment == null) {
                baseFragment = mClassifyFragment;
                transaction.add(R.id.main_frame, mClassifyFragment).commit();
            } else {
                transaction.hide(baseFragment).add(R.id.main_frame, mClassifyFragment).commit();
            }
        }
        setTitleTx(getString(R.string.main_classify));
        baseFragment = mClassifyFragment;
    }

    private void showNavigationFragment() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (mNavigationFragment == null) {
            mNavigationFragment = new NavigationFragment();
        }
        if (mNavigationFragment.isAdded()) {
            transaction.hide(baseFragment).show(mNavigationFragment).commit();
        } else {
            if (baseFragment == null) {
                baseFragment = mNavigationFragment;
                transaction.add(R.id.main_frame, mNavigationFragment).commit();
            } else {
                transaction.hide(baseFragment).add(R.id.main_frame, mNavigationFragment).commit();
            }
        }
        setTitleTx(getString(R.string.main_navigation));
        baseFragment = mNavigationFragment;
    }

    private void showSystemFragment() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (mSystemFragment == null) {
            mSystemFragment = new SystemFragment();
        }
        if (mSystemFragment.isAdded()) {
            transaction.hide(baseFragment).show(mSystemFragment).commit();
        } else {
            if (baseFragment == null) {
                baseFragment = mSystemFragment;
                transaction.add(R.id.main_frame, mSystemFragment).commit();
            } else {
                transaction.hide(baseFragment).add(R.id.main_frame, mSystemFragment).commit();
            }
        }
        setTitleTx(getString(R.string.main_system));
        baseFragment = mSystemFragment;
    }

    private void showHomepageFragment() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (mHomapageFragment == null) {
            mHomapageFragment = new HomepageFragment();
        }
        if (mHomapageFragment.isAdded()) {
            transaction.hide(baseFragment).show(mHomapageFragment).commit();
        } else {
            if (baseFragment == null) {
                baseFragment = mHomapageFragment;
                transaction.add(R.id.main_frame, mHomapageFragment).commit();
            } else {
                transaction.hide(baseFragment).add(R.id.main_frame, mHomapageFragment).commit();
            }
        }
        setTitleTx(getString(R.string.main_homepage));
        baseFragment = mHomapageFragment;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        App.getInstance().finishAll();
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastTimeMillis <= BACKPRESS_TIME) {
            App.getInstance().finishAll();
            super.onBackPressed();
        } else {
            lastTimeMillis = System.currentTimeMillis();
            ToastUtil.showShort(this, getString(R.string.backpress_again_finish));
        }
    }
}
