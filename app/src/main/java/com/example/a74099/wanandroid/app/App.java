package com.example.a74099.wanandroid.app;

import android.app.Activity;
import android.app.Application;
import android.util.DisplayMetrics;

import com.example.a74099.wanandroid.base.BaseActivity;
import com.example.a74099.wanandroid.util.picture.CachePathUtil;
import com.example.a74099.wanandroid.util.picture.DisplayUtil;

import java.io.File;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Administrator on 2018/2/4.
 * 配置全局变量
 */

public class App extends Application {
    private static App instance;
    private Set<Activity> allActivities;
    public static BaseActivity baseActivity;

    /**
     * 缓存拍照图片路径
     */
    public File takePhotoCacheDir = null;



    public Set<Activity> getAllActivities() {
        return allActivities;
    }

    public void setAllActivities(Set<Activity> allActivities) {
        this.allActivities = allActivities;
    }

    public static App getInstance() {
        return instance;
    }

    public static void setInstance(App instance) {
        App.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        baseInit();
    }

    private void baseInit() {
//        Thread.setDefaultUncaughtExceptionHandler(CrashExpection.getInstance(
//                this, CachePathUtil.getCachePathFile("/crash")
//                        .getAbsolutePath()));
        initTakePhotoFile();
        /**
         * 初始化尺寸工具类
         */
        initDisplayOpinion();
    }

    private void initDisplayOpinion() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        DisplayUtil.density = dm.density;
        DisplayUtil.densityDPI = dm.densityDpi;
        DisplayUtil.screenWidthPx = dm.widthPixels;
        DisplayUtil.screenhightPx = dm.heightPixels;
        DisplayUtil.screenWidthDip = DisplayUtil.px2dip(getApplicationContext(), dm.widthPixels);
        DisplayUtil.screenHightDip = DisplayUtil.px2dip(getApplicationContext(), dm.heightPixels);
    }
    /**
     * 图片存储初始化
     */
    public void initTakePhotoFile() {
        this.takePhotoCacheDir = CachePathUtil.getCachePathFile("/picture/sm_photo");
    }

    //##################################### 以下是activity的收litepal.xml集 ####################################
    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }
}
