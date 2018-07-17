package com.example.a74099.wanandroid.view.pullrefreshview.resolver;

import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.view.MotionEvent;
import android.view.View;

import com.example.a74099.wanandroid.view.pullrefreshview.impl.PullAble;


/**
 * Created by ybao on 2017/5/14.
 */

public interface IEventResolver extends NestedScrollingChild, NestedScrollingParent {

    PullAble getPullAble(View view);

    PullAble getPullAble(PullAble pullable);

    boolean isScrolling();

    void setViewTranslationP(View view, float value);

    boolean dispatchTouchEvent(MotionEvent ev);

    boolean interceptTouchEvent(MotionEvent ev);

    boolean touchEvent(MotionEvent ev);

    float getVelocity();

    void onDetachedFromWindow();
}
