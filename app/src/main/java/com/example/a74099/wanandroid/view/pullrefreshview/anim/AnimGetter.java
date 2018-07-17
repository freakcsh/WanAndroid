package com.example.a74099.wanandroid.view.pullrefreshview.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.animation.Interpolator;


/**
 * Created by ybao on 2017/5/14.
 */

public class AnimGetter implements IAnimGetter {

    @Override
    public Animator createMoveToAnim(int startDelay, int duration, Interpolator interpolator, final AnimListener animListener, float... p) {
        ValueAnimator animator = ValueAnimator.ofFloat(p);
        animator.setStartDelay(startDelay);
        animator.setInterpolator(interpolator);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animListener.onUpdate((float) animation.getAnimatedValue());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animListener.onAnimEnd();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                animListener.onAnimCencel();
            }
        });
        return animator;
    }
}
