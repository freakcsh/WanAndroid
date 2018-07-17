package com.example.a74099.wanandroid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.view.pullrefreshview.layout.BaseHeaderView;
import com.example.a74099.wanandroid.view.pullrefreshview.layout.PullRefreshLayout;
import com.example.a74099.wanandroid.view.pullrefreshview.type.LayoutType;
import com.example.a74099.wanandroid.view.pullrefreshview.utils.AnimUtil;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ybao on 2015/11/3 0003.
 */
public class LockHeaderView extends BaseHeaderView {
    View progress;
    View stateImg;
    View loadBox;
    int width = 0;
    Path path;
    Paint paint;

    int state = NONE;

    int layoutType = LayoutType.LAYOUT_SCROLLER;

    public LockHeaderView(Context context) {
        this(context, null);
    }

    public LockHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LockHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_header_lock, this, true);
        progress = findViewById(R.id.progress);
        stateImg = findViewById(R.id.state);
        loadBox = findViewById(R.id.load_box);
        path = new Path();
        paint = new Paint();
        paint.setColor(0x3399ff);
        paint.setAlpha(255);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void setPullRefreshLayout(PullRefreshLayout refreshLayout) {
        super.setPullRefreshLayout(refreshLayout);
        refreshLayout.setMaxDistance(400);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, 400);
        setMeasuredDimension(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(400, View.MeasureSpec.EXACTLY));
        width = getWidth();
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
        super.dispatchDraw(canvas);
    }

    List<Animator> animators = new ArrayList<>();

    @Override
    protected void onStateChange(int state) {
        this.state = state;
        for (Animator animator : animators) {
            animator.cancel();
        }
        animators.clear();
        stateImg.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.INVISIBLE);
        ViewHelper.setAlpha(progress, 1f);
        switch (state) {
            case NONE:
                break;
            case PULLING:
                break;
            case LOOSENT_O_REFRESH:
                break;
            case REFRESHING:
                animators.add(AnimUtil.startShow(progress, 0.1f, 200, 0));
                animators.add(AnimUtil.startRotation(progress, ViewHelper.getRotation(progress) + 359.99f, 500, 0, -1));
                break;
            case REFRESH_CLONE:
                animators.add(AnimUtil.startShow(stateImg, 0.1f, 400, 200));
                animators.add(AnimUtil.startHide(progress));
                break;

        }

    }

    @Override
    public float getSpanHeight() {
        return loadBox.getHeight();
    }

    @Override
    public int getLayoutType() {
        return layoutType;
    }

    @Override
    public boolean onScroll(float y) {
        boolean intercept = super.onScroll(y);
        ViewHelper.setTranslationY(loadBox, 0.97f * y - loadBox.getHeight());
        if (!isLockState()) {
            ViewHelper.setRotation(progress, y * y * 48 / 31250);
        }
        path.reset();// 重置path
        if (y == 0) {
            invalidate();
            return intercept;
        }
        // 贝赛尔曲线的起始点
        path.moveTo(0, 0);
        // 设置贝赛尔曲线的操作点以及终止点
        path.quadTo(width / 2, 1.94f * y, width, 0);
        invalidate();
        return intercept;
    }
}
