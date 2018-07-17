package com.example.a74099.wanandroid.util;

import android.content.Context;

import com.example.a74099.wanandroid.view.thirdview.catloading.CatLoadingView;


/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class LoadingUtils {

    private volatile static LoadingUtils INSTANCE; //声明成 volatile
    private Object loadingView;

    public static LoadingUtils getLoadingUtils() {
        if (INSTANCE == null) {
            synchronized (LoadingUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoadingUtils();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * 加载的动画
     * @param context
     */
    public void showLoadingView(final Context context){
        if(context==null||isLoading()){
            return ;
        }
        loadingView = loadingAnimation(context);
    }

    public void hideLoadingView(Context context){
        if(loadingView!=null){
            loadingDismiss(context,loadingView);
        }
    }



    /**
     * 加载动画
     */
    private final Object loadingAnimation(Context context) {
        CatLoadingView mView = CatLoadingView.createInstance();
        mView.showView(context);
        return mView;
    }
    private final boolean isLoading() {

//        if(this.loadingView!=null) {
//            CatLoadingView loadingView = (CatLoadingView) this.loadingView;
//              return !loadingView.isHidden();
//        }
//        return false;

        return false;
    }

    private final void loadingDismiss(Context context, Object object) {
        ((CatLoadingView) object).hideView(context);
    }
}
