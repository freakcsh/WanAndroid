package com.example.a74099.wanandroid.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2018/2/5.
 *  无MVP的Fragment基类
 */

public abstract class SimpleFragment extends SupportFragment {

    protected View mView;
    protected Activity mActivity;
    protected Context mContext;


    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(),container, false);

        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initEventAndData(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initLazyData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

//    public void toastShow(int resId) {
//        ToastUtil.show(resId);
//    }
//
//    public void toastShow(String text) {
//        if (text.equals("用户未登录")){
//            SPUtils.put(mContext, Constants.TOKEN_ABLE,false);
//            RxBus.getDefault().post(new UserEvent(1, Constants.RENOVATE));
//        }
//        ToastUtil.shortShow(text);
//    }

    protected abstract int getLayoutId();

    protected abstract void initEventAndData(View view);

    protected abstract void initLazyData();
}