package com.example.a74099.wanandroid.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.app.App;
import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;


/**
 * Created by Administrator on 2018/2/4.
 * MVP activity基类
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected T mPresenter;
    protected Activity mActivity;

    protected abstract int getLayout();

    protected abstract void initEventAndData();
    protected abstract T createPresenter();
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /**
         * 创建presenter对象
         */
        mPresenter = createPresenter();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        mActivity = this;
        //活动控制器
        App.getInstance().addActivity(this);


//        ActivityCollector.addActivity(this);


        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        initEventAndData();
    }

    @Override
    protected void onResume() {
        App.baseActivity = this;
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ActivityCollector.removeActivity(this);

        /**
         * presenter 解除view订阅
         */
        if (mPresenter != null) {
            mPresenter.detachView();
        }

        App.getInstance().removeActivity(this);

    }

    //返回监听
    public void setBackPress() {
        try {
            View backView = findViewById(R.id.leftImg_ly);
            backView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e) {

        }
    }

    //设置title
    public void setTitleTx(String title_tx) {
        try {
            TextView title = findViewById(R.id.title);
            title.setText(title_tx);
        } catch (Exception e) {

        }
    }

    /**
     * 打开一个Activity 默认 不关闭当前activity
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle ex) {
        Intent intent = new Intent(this, clz);
        if (ex != null) intent.putExtras(ex);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();//返回
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
