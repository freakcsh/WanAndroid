package com.example.a74099.wanandroid.model.myself.lock.core;

import android.graphics.Canvas;
import android.support.annotation.NonNull;

/**
 * Created by hsg on 22/02/2018.
 */

public interface INormalCellView {
    /**
     * 绘制正常情况下（即未设置的）每个图案的样式
     *
     * @param canvas
     * @param cellBean the target cell view
     */
    void draw(@NonNull Canvas canvas, @NonNull CellBean cellBean);
}