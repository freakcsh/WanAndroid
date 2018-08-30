package com.example.a74099.wanandroid.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.a74099.wanandroid.app.App;
import com.example.a74099.wanandroid.app.Constants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ToolUtils {

    /**
     * 获取缓存路径
     *
     * @return
     */
    public static File getApplicationCacheDir() {
        return App.getInstance().takePhotoCacheDir;
    }

    /**
     * 显示加载框
     */
    public static void showLoading(Context mContext) {

        LoadingUtils.getLoadingUtils().showLoadingView(mContext);

    }

    /**
     * 隐藏加载框
     */
    public static void dismissLoading(Context mContext) {
        LoadingUtils.getLoadingUtils().hideLoadingView(mContext);
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {

            @SuppressLint("MissingPermission") NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * subtraction
     */
    public static String subtraction(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).toString();
    }

    /**
     * 乘法
     */
    public static String multiply(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);

        return b1.multiply(b2).toString();
    }

    public static String division(String v1, String v2, int s) {
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            BigDecimal result = b1.divide(b2, s, BigDecimal.ROUND_HALF_UP);
            return result.toString();
        } catch (Exception e) {
            return "--";
        }

    }


    public interface IDelayAction {
        void delayAction();
    }

    /**
     * times毫秒后,执行操作
     *
     * @param times
     * @param delayAction
     */
    public static void delayAction(int times, final IDelayAction delayAction) {
        if (delayAction != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    delayAction.delayAction();
                }
            }, times);
        }
    }

    /**
     * 隐藏密码
     *
     * @param editText
     */
    public static void hidePassWord(EditText editText) {
        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    /**
     * 显示密码
     *
     * @param editText
     */
    public static void showPassword(EditText editText) {
        editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }

    /**
     * 字符串转int
     *
     * @param o
     * @return
     */
    public final static int String2Int(String o) {
        if (o != null) {
            try {
                return Integer.valueOf(o);
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 0;
        }

    }

    /**
     * String 转long
     *
     * @param o
     * @return
     */
    public final static long String2Long(String o) {
        if (o != null) {
            try {
                return Long.valueOf(o);
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 0;
        }

    }

    /**
     * String 转double
     *
     * @param o
     * @return
     */
    public final static double String2Double(String o) {
        if (o != null) {
            try {
                return Double.valueOf(o);
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * 乘法
     *
     * @param v1    乘数
     * @param v2    被乘数
     * @param scale 小数点保留位数
     * @return
     */
    public static String multiplyWithScale(String v1, String v2, int scale) {
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            BigDecimal result = b1.multiply(b2);
            result = result.setScale(scale, RoundingMode.HALF_EVEN);
            return result.toString();
        } catch (Exception e) {
            return " --- ";
        }

    }

    /**
     * 金钱减法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static String subtract(String v1, String v2) {

        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);

        return b1.subtract(b2).toString();
    }

    public static String add(String v1, String v2) {
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            BigDecimal result = b1.add(b2);
            return result.toString();
        } catch (Exception e) {
            return " --- ";
        }
    }

//    /**
//     * String 转double
//     *
//     * @param o
//     * @return
//     */
//    public final static double String2Double(String o) {
//        if (o != null) {
//            try {
//                return Double.valueOf(o);
//            } catch (Exception e) {
//                return 0;
//            }
//        } else {
//            return 0;
//        }
//
//    }

    /**
     * String 转float
     *
     * @param o
     * @return
     */
    public final static float String2Float(String o) {
        if (o != null) {
            try {
                return Float.valueOf(o);
            } catch (Exception e) {
                return 0f;
            }
        } else {
            return 0f;
        }

    }

    /**
     * double型数据保留两位小数
     *
     * @return
     */
    public static String doublePoint(Double double1) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#######################0.00");
        return df.format(double1);
    }

    public static String doublePoint4(Double double1) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#######################0.0000");
        return df.format(double1);
    }

    /**
     * double型数据保留八位小数
     *
     * @return
     */
    public static String doublePoint8(Double double1) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#######################0.00000000");
        return df.format(double1);
    }

    /**
     * double型数据保留六位小数
     *
     * @return
     */
    public static String doublePoint6(Double double1) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#######################0.000000");
        return df.format(double1);
    }

    /**
     * double型数据保留整数
     *
     * @return
     */
    public static String doublePointToInt(Double double1) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#######################0");
        return df.format(double1);
    }

    /**
     * PopupWindow居中。
     * 设置焦点后再调用该方法才能点击外部消失
     *
     * @param popup
     * @param parent
     * @param x
     * @param y
     */
    public static void popupWindowShowCenter(PopupWindow popup, View parent, int x, int y) {
        popup.showAtLocation(parent, Gravity.CENTER, 0, 0);
        if (Build.VERSION.SDK_INT < 24) {
            popup.update();
        } else {
            popup.dismiss();
            popup.showAtLocation(parent, Gravity.CENTER, 0, 0);
        }

    }

    /**
     * Html.fromHtml
     *
     * @param textview
     * @param htmlFormat
     */
    public static void HTML_FromHtml(TextView textview, String htmlFormat) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textview.setText(Html.fromHtml((htmlFormat), Html.FROM_HTML_MODE_LEGACY));
        } else {
            textview.setText(Html.fromHtml(htmlFormat));
        }
    }

    /**
     * The BigDecimal class provides operations for arithmetic, scale manipulation, rounding, comparison, hashing,
     * and format conversion.
     *
     * @param d
     * @return
     */
    public static double formatDouble2(int keepnum, double d) {
        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(keepnum, RoundingMode.UP);
        return bg.doubleValue();
    }

    public final static String getStringDefult(String targt, String defult) {
        return isNull(targt) ? defult : targt;
    }

    /*****
     * String 是否空
     * ******/
    public final static boolean isNull(String value) {
        return value == null || value.equals("");
    }

    /**
     * MD5加密
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 实现截图,返回保存路径
     *
     * @param activity
     * @param isopen   保存后是否打开图片
     * @return
     */
    public static void saveScreemShots(Activity activity, boolean isopen) {
        //1.构建Bitmap
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        Bitmap bitmap;

        //2.获取截屏
        View decorview = activity.getWindow().getDecorView();
        decorview.setDrawingCacheEnabled(true);
        bitmap = decorview.getDrawingCache();

        //3.创建路径
        String statue = Environment.getExternalStorageState();
        File dir = null;
        if (statue.equals(Environment.MEDIA_MOUNTED)) {
            dir = new File(Environment.getExternalStorageDirectory() + "/DCIM/Screemshots");
        } else {
            dir = new File(Environment.getDownloadCacheDirectory() + "/DCIM/Screemshots");
        }
        if (!dir.exists()) {
            dir.mkdir();
        }
        String savePath = dir.getPath();

        //4.保存bitmap
        try {
            File path = new File(savePath);
            String filePath = savePath + "/" + System.currentTimeMillis() + ".png";
            File file = new File(filePath);
            if (!path.exists()) {
                path.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            if (null != fos) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
                ToastUtil.showShort(activity, "截图已保存.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isopen) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.fromFile(new File(savePath));
            intent.setDataAndType(uri, "image/*");
            activity.startActivity(intent);
        }
    }

    /**
     * 当前软键盘若隐藏，则打开；当前软键盘若打开，则隐藏.
     *
     * @param context
     */
    public static void hideShowKeyBoard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 由时间戳转换得到时间,精确到秒
     *
     * @param timeStamp
     * @param format
     * @return
     */
    public static String timeStamp2String(String timeStamp, String format) {
        String sd = "";
        format = (isNull(format) ? "yyyy-MM-dd HH:mm" : format);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sd = sdf.format(new Date(Long.parseLong(timeStamp) * 1000));
        } catch (Exception e) {

        }
        return sd;
    }

    /**
     * Date类型转换得到时间,精确到秒
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2String(Date date, String format) {
        String sd = "";
        format = (isNull(format) ? "yyyy-MM-dd HH:mm" : format);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sd = sdf.format(date);
        } catch (Exception e) {

        }
        return sd;
    }

    /**
     * 将HTML实体转为正常HTML
     */
//    public static String htmle2String(String unicode) {
//        if (!ToolUtils.isNull(unicode)) {
////            return unicode.replaceAll("&#60;","<").replaceAll("&#62;",">").replaceAll("&#47;","/");
//            return StringEscapeUtils.unescapeHtml4(unicode);
//        } else {
//            return "";
//        }
//    }

    /**
     * 复制TextView文本
     *
     * @param context
     * @param aimTextView
     */
    public static void copyTextView(Context context, TextView aimTextView) {
        ClipboardManager clipboard = (ClipboardManager)
                context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", aimTextView.getText().toString());
        clipboard.setPrimaryClip(clip);
//        T.showShort(context, context.getString(R.string.copy_success));
    }

//    public static UserInfoEntity getUserInfo(Context context) {
//        UserInfoEntity userInfoEntity = (UserInfoEntity) ACache.get(context).getAsObject(ACEConstant.ACE_USERINFO);
//        if (userInfoEntity == null) {
//            return null;
//        } else {
//            return userInfoEntity;
//        }
//    }


    /**
     * 登录成功后保存的Token
     *
     * @param context
     * @param cookie
     */
    public static void saveToken(Context context, String cookie) {
        ACache.get(context).put(Constants.IS_LOGIN, cookie);
    }

    /**
     * 保存登录信息
     *
     * @param context
     * @param userName
     */
    public static void saveLoginInfo(Context context, String userName) {
        SPUtils.put(context,Constants.LOGIN_BEAN,userName);

    }

    /**
     * 获取登录信息
     */
    public static String getLoginBean(Context context) {
        String userName = (String)SPUtils.get(context,Constants.LOGIN_BEAN,"");
        if (ToolUtils.isNull(userName)) {
            return null;
        } else {
            return userName;
        }
    }

    /***
     * 判断是否登录
     * @param context
     * @return
     */
    public static boolean isLogin(Context context) {
        if (ToolUtils.isNull((String) ACache.get(context).getAsObject(Constants.IS_LOGIN))) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 退出登录
     *
     * @param context
     */
    public static void logout(Context context) {
        ACache.get(context).put(Constants.IS_LOGIN, "");
        SPUtils.put(context, Constants.LOGIN_BEAN, "");
    }

    /**
     * 复制String
     *
     * @param context
     * @param text
     */
    public static void copyStringText(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager)
                context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", text);
        clipboard.setPrimaryClip(clip);
//        T.showShort(context, context.getString(R.string.copy_success));
    }

    /**
     * android状态栏一体化(沉浸式模式)
     *
     * @param activity
     */
    public static void topBarImmerse(Activity activity) {
        Window window = activity.getWindow();
        if (21 <= Build.VERSION.SDK_INT) {
//设置透明状态栏,这样才能让 ContentView 向上
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

//需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//设置状态栏颜色
            window.setStatusBarColor(Color.TRANSPARENT);

            ViewGroup mContentView = activity.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
                ViewCompat.setFitsSystemWindows(mChildView, false);
            }
        } else if (21 > Build.VERSION.SDK_INT && 19 <= Build.VERSION.SDK_INT) {
            ViewGroup mContentView = activity.findViewById(Window.ID_ANDROID_CONTENT);

//首先使 ChildView 不预留空间
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                ViewCompat.setFitsSystemWindows(mChildView, false);
            }

            //最顶部的状态栏
            int statusBarHeight1 = -1;
            //获取status_bar_height资源的ID
            int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                //根据资源ID获取响应的尺寸值
                statusBarHeight1 = activity.getResources().getDimensionPixelSize(resourceId);
            }

            int statusBarHeight = statusBarHeight1;
//需要设置这个 flag 才能设置状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//避免多次调用该方法时,多次移除了 View
            if (mChildView != null && mChildView.getLayoutParams() != null && mChildView.getLayoutParams().height ==
                    statusBarHeight) {
                //移除假的 View.
                mContentView.removeView(mChildView);
                mChildView = mContentView.getChildAt(0);
            }
            if (mChildView != null) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
                //清除 ChildView 的 marginTop 属性
                if (lp != null && lp.topMargin >= statusBarHeight) {
                    lp.topMargin -= statusBarHeight;
                    mChildView.setLayoutParams(lp);
                }
            }
        }
    }


    public static byte[] File2byte(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

//    public static File getApplicationCacheDir() {
//        return MAppliaction.getApp().takePhotoCacheDir;
//    }

    public static void openFile(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
//
//    public static void setMarketUpDownTvColor(MarketListEntity entity, TextView textView) {
//
//        try {
//            Double.valueOf(entity.getChange());
//        } catch (Exception e) {
//            textView.setTextColor(0xff656565);//空值
//            return;
//        }
//
//        if (ToolUtils.String2Double(entity.getChange()) >= 0) {
//            textView.setTextColor(0xffb9cf00);
//
//        } else {
//            textView.setTextColor(0xfffe0e5e);
//        }
//    }


}
