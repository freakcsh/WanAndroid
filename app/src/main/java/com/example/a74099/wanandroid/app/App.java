package com.example.a74099.wanandroid.app;

import android.app.Activity;
import android.app.Application;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseActivity;
import com.example.a74099.wanandroid.model.MainActivity;
import com.example.a74099.wanandroid.net.util.NetStateChangeReceiver;
import com.example.a74099.wanandroid.util.picture.CachePathUtil;
import com.example.a74099.wanandroid.util.picture.DisplayUtil;
import com.mob.MobSDK;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

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

        /**** Beta高级设置*****/
        /**
         * true表示app启动自动初始化升级模块；
         * false不好自动初始化
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false
         * 在后面某个时刻手动调用
         */
        Beta.autoInit = true;

        /**
         * true表示初始化时自动检查升级
         * false表示不会自动检查升级，需要手动调用Beta.checkUpgrade()方法
         */
        Beta.autoCheckUpgrade = true;

        /**
         * 设置升级周期为60s（默认检查周期为0s），60s内SDK不重复向后天请求策略
         */
        Beta.initDelay = 1 * 1000;

        /**
         * 设置通知栏大图标，largeIconId为项目中的图片资源；
         */
        Beta.largeIconId = R.mipmap.ic_launcher;

        /**
         * 设置状态栏小图标，smallIconId为项目中的图片资源id;
         */
        Beta.smallIconId = R.mipmap.ic_launcher;


        /**
         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;
         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
         */
        Beta.defaultBannerId = R.mipmap.ic_launcher;

        /**
         * 设置sd卡的Download为更新资源保存目录;
         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
         */
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        /**
         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;
         */
        Beta.showInterruptedStrategy = false;

        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;
         * 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(MainActivity.class);

        baseInit();
        // 注册BroadcastReceiver
        NetStateChangeReceiver.registerReceiver(this);
        MobSDK.init(this);
//        CrashReport.initCrashReport(getApplicationContext(), "46fb82cb75", false);
        Bugly.init(getApplicationContext(), "46fb82cb75", false);
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
