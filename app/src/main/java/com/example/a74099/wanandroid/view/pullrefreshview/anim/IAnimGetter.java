package com.example.a74099.wanandroid.view.pullrefreshview.anim;

import android.animation.Animator;
import android.view.animation.Interpolator;


/**
 * Created by ybao on 2017/5/14.
 */

public interface IAnimGetter {
    Animator createMoveToAnim(int offstart, int duration, Interpolator interpolator, AnimListener animListener, float... p);
}
