package com.example.a74099.wanandroid.model.myself.lock.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsg on 20/09/2017.
 */

/***
 * 绘制圆形
 */
class CellFactory {
    private int width;
    private int height;
    private List<CellBean> cellBeanList;

    public CellFactory(int width, int height) {
        this.width = width;
        this.height = height;
        this.cellBeanList = new ArrayList<>();
        this.create();
    }

    /**
     * 绘制九宫格
     */
    private void create() {
        //整体九宫格的宽高
        final float pWidth = this.width / 8f;
        final float pHeight = this.height / 8f;
        /**
         *  画出九宫格 j、i代表横向和竖向的圆圈个数
         */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.cellBeanList.add(new CellBean(
                        (i * 3 + j),
                        (j * 3 + 1) * pWidth,
                        (i * 3 + 1) * pHeight,
                        pWidth/1.2f));
            }
        }
    }

    public List<CellBean> getCellBeanList() {
        return this.cellBeanList;
    }
}