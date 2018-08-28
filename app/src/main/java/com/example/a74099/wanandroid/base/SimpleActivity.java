package com.example.a74099.wanandroid.base;

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
import com.example.a74099.wanandroid.net.util.NetStateChangeObserver;
import com.example.a74099.wanandroid.net.util.NetStateChangeReceiver;
import com.example.a74099.wanandroid.net.util.NetworkType;
import com.example.a74099.wanandroid.util.ToastUtil;

/**
 * Created by Administrator on 2018/2/5.
 * 无MVP的activity基类
 */

public abstract class SimpleActivity extends AppCompatActivity implements NetStateChangeObserver {

    protected Activity mContext;
    private View netErrorView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);

        setContentView(getLayout());
        mContext = this;
        App.getInstance().addActivity(this);
//        ActivityCollector.addActivity(this);
        initEventAndData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.registerObserver(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.unregisterObserver(this);
        }
    }
    /**
     * 是否需要注册网络变化的Observer,如果不需要监听网络变化,则返回false;否则返回true
     */
    protected boolean needRegisterNetworkChangeObserver() {
        return true;
    }

    /**
     * 网络断开时执行的操作
     */
    @Override
    public void onNetDisconnected() {
        showDisConnectedView();
    }

    /**
     * 网络重连时执行的操作
     *
     * @param networkType
     */
    @Override
    public void onNetConnected(NetworkType networkType) {
        ToastUtil.showLong(mContext, "当前连接的是"+networkType.toString()+"网络");
        hideDisConnectedView();
    }

    /**
     * 显示无网络状态
     */
    public void showDisConnectedView() {
        netErrorView = findViewById(R.id.rl_net_error);
        netErrorView.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏无网络状态
     */
    public void hideDisConnectedView() {
        netErrorView = findViewById(R.id.rl_net_error);
        netErrorView.setVisibility(View.GONE);
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

    public void backOnclick(View view) {
        finish();
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();
//
//    public void toastShow(int resId) {
//        ToastUtil.show(resId);
//    }
//
//    public void toastShow(String text) {
//        if (text.equals("用户未登录")){
//            SPUtils.put(getApplicationContext(), Constants.TOKEN_ABLE,false);
//            RxBus.getDefault().post(new UserEvent(1, Constants.RENOVATE));
//        }
//        ToastUtil.shortShow(text);
//    }
}