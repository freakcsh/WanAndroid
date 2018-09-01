package com.example.a74099.wanandroid.app;

import android.app.Activity;
import android.app.Application;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.a74099.wanandroid.base.BaseActivity;
import com.example.a74099.wanandroid.net.util.NetStateChangeReceiver;
import com.example.a74099.wanandroid.util.picture.CachePathUtil;
import com.example.a74099.wanandroid.util.picture.DisplayUtil;
import com.mob.MobSDK;
import com.tencent.bugly.crashreport.CrashReport;

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
        // 注册BroadcastReceiver
        NetStateChangeReceiver.registerReceiver(this);
        MobSDK.init(this);
        CrashReport.initCrashReport(getApplicationContext(), "46fb82cb75", false);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        // 取消BroadcastReceiver注册
        NetStateChangeReceiver.unregisterReceiver(this);
    }

    private void baseInit() {
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

    //##################################### 以下是activity的收集 ####################################

    /**
     * 添加activity
     *
     * @param act
     */
    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
        for (Activity activity : allActivities) {
            Log.e("FreakActivity", "addActivity" + activity.getLocalClassName());
        }
    }

    /**
     * 移除单独的activity
     *
     * @param act
     */
    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
        for (Activity activity : allActivities) {
            Log.e("FreakActivity", "removeActivity" + activity.getLocalClassName());
        }
    }

    /**
     * 移除所有的activity
     */
    public void finishAll() {
        for (Activity activity : allActivities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        for (Activity activity : allActivities) {
            Log.e("FreakActivity", "finishAll" + activity.getLocalClassName());
        }
    }
}
