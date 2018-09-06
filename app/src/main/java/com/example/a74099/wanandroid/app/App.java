package com.example.a74099.wanandroid.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.sdk.android.feedback.impl.FeedbackAPI;
import com.alibaba.sdk.android.feedback.util.ErrorCode;
import com.alibaba.sdk.android.feedback.util.FeedbackErrorCallback;
import com.alibaba.sdk.android.httpdns.HttpDns;
import com.alibaba.sdk.android.httpdns.HttpDnsService;
import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseActivity;
import com.example.a74099.wanandroid.model.MainActivity;
import com.example.a74099.wanandroid.net.util.NetStateChangeReceiver;
import com.example.a74099.wanandroid.util.picture.CachePathUtil;
import com.example.a74099.wanandroid.util.picture.DisplayUtil;
import com.mob.MobSDK;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

import org.litepal.LitePal;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;


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
        settingUpData();


        baseInit();
        // 注册BroadcastReceiver
        NetStateChangeReceiver.registerReceiver(this);
        MobSDK.init(this);
//        CrashReport.initCrashReport(getApplicationContext(), "46fb82cb75", false);
        Bugly.init(getApplicationContext(), "46fb82cb75", false);
        //阿里移动用户反馈初始化
        initFeedbackService();
        LitePal.initialize(this);
    }

    /**
     * 腾讯bugly检测更新配置
     */
    private void settingUpData() {
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
    //TODO**********************************************************************************************
    /***
     * 阿里云服务、移动用户反馈、热更新==
     */
    private static final String TAG = "AppApplication";

    //hotfix init need attr
    public interface MsgDisplayListener {
        void handle(String msg);
    }

    public static MsgDisplayListener msgDisplayListener = null;
    public static StringBuilder cacheMsg = new StringBuilder();

    private void initManService() {
        /**
         * 初始化Mobile Analytics服务
         */
        // 获取MAN服务
        MANService manService = MANServiceProvider.getService();
        // 打开调试日志
        manService.getMANAnalytics().turnOnDebug();
        manService.getMANAnalytics().setAppVersion("3.0");
        // MAN初始化方法之一，通过插件接入后直接在下发json中获取appKey和appSecret初始化
        manService.getMANAnalytics().init(this, getApplicationContext());
        // MAN另一初始化方法，手动指定appKey和appSecret
        // String appKey = "******";
        // String appSecret = "******";
        // manService.getMANAnalytics().init(this, getApplicationContext(), appKey, appSecret);
        // 若需要关闭 SDK 的自动异常捕获功能可进行如下操作,详见文档5.4
        //manService.getMANAnalytics().turnOffCrashReporter();
        // 通过此接口关闭页面自动打点功能，详见文档4.2
        manService.getMANAnalytics().turnOffAutoPageTrack();
        // 设置渠道（用以标记该app的分发渠道名称），如果不关心可以不设置即不调用该接口，渠道设置将影响控制台【渠道分析】栏目的报表展现。如果文档3.3章节更能满足您渠道配置的需求，就不要调用此方法，按照3.3进行配置即可
        manService.getMANAnalytics().setChannel("某渠道");
        // 若AndroidManifest.xml 中的 android:versionName 不能满足需求，可在此指定；
        // 若既没有设置AndroidManifest.xml 中的 android:versionName，也没有调用setAppVersion，appVersion则为null
        //manService.getMANAnalytics().setAppVersion("2.0");
    }

    private void initFeedbackService() {
        /**
         * 添加自定义的error handler
         */
        FeedbackAPI.addErrorCallback(new FeedbackErrorCallback() {
            @Override
            public void onError(Context context, String errorMessage, ErrorCode code) {
                Toast.makeText(context, "ErrMsg is: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
        FeedbackAPI.addLeaveCallback(new Callable() {
            @Override
            public Object call() throws Exception {
                Log.d("DemoApplication", "custom leave callback");
                return null;
            }
        });
        /**
         * 建议放在此处做初始化
         */
        //默认初始化
        FeedbackAPI.init(this);
        //FeedbackAPI.init(this, "DEFAULT_APPKEY", "DEFAULT_APPSECRET");
        /**
         * 在Activity的onCreate中执行的代码
         * 可以设置状态栏背景颜色和图标颜色，这里使用com.githang:status-bar-compat来实现
         */
//        FeedbackAPI.setActivityCallback(new IActivityCallback() {
//            @Override
//            public void onCreate(Activity activity) {
//                StatusBarCompat.setStatusBarColor(activity,getResources().getColor(R.color.aliwx_setting_bg_nor),true);
//            }
//        });
        /**
         * 自定义参数演示
         */
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("loginTime", "登录时间");
//            jsonObject.put("visitPath", "登陆，关于，反馈");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        FeedbackAPI.setAppExtInfo(jsonObject);
        /**
         * 以下是设置UI
         */
        //设置默认联系方式
//        FeedbackAPI.setDefaultUserContactInfo("13800000000");
        //沉浸式任务栏，控制台设置为true之后此方法才能生效
//        FeedbackAPI.setTranslucent(true);
        //设置返回按钮图标
        FeedbackAPI.setBackIcon(R.mipmap.left_arrow);
        //设置标题栏"历史反馈"的字号，需要将控制台中此字号设置为0
//        FeedbackAPI.setHistoryTextSize(20);
        //设置标题栏高度，单位为像素
//        FeedbackAPI.setTitleBarHeight(100);

    }

    private void initHttpDnsService() {
        // 初始化httpdns
        //HttpDnsService httpdns = HttpDns.getService(getApplicationContext(), accountID);
        HttpDnsService httpdns = HttpDns.getService(getApplicationContext());
        //this.setPreResoveHosts();
        // 允许过期IP以实现懒加载策略
        //httpdns.setExpiredIPEnabled(true);
    }

    private void initHotfix() {
        String appVersion;
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0.0";
        }
        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                //.setAesKey("0123456789123456")
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        String msg = new StringBuilder("").append("Mode:").append(mode)
                                .append(" Code:").append(code)
                                .append(" Info:").append(info)
                                .append(" HandlePatchVersion:").append(handlePatchVersion).toString();
                        if (msgDisplayListener != null) {
                            msgDisplayListener.handle(msg);
                        } else {
                            cacheMsg.append("\n").append(msg);
                        }
                    }
                }).initialize();
    }

    /**
     * 初始化云推送通道
     *
     * @param applicationContext
     */
    private void initPushService(final Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        final CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i(TAG, "init cloudchannel success");
                //setConsoleText("init cloudchannel success");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.e(TAG, "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
                //setConsoleText("init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
    }
}
