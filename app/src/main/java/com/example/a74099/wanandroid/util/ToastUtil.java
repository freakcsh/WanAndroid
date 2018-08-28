package com.example.a74099.wanandroid.util;

import android.content.Context;
import android.widget.Toast;



public class ToastUtil {

    public static boolean isShow = true;

    private static Toast mToast;
    private static long timeStamp = 0;

    private ToastUtil() {
        throw new UnsupportedOperationException("can not be instantiated ");
    }


    public static void showLong(Context context, CharSequence messgae) {
        try {
            if (isShow)
                toToast(context, messgae, Toast.LENGTH_SHORT);
        } catch (Exception e) {

        }
    }


    public static void showShort(Context context, CharSequence messgae) {
        try {
            if (isShow)
                toToast(context, messgae, Toast.LENGTH_SHORT);
        } catch (Exception e) {

        }
    }


    public static void showNetErroMsg(Context context, Throwable e) {
        try {
            toToast(context, e.getMessage(), Toast.LENGTH_SHORT);
        } catch (Exception e1) {

        }
    }

    private static void toToast(Context context, CharSequence content, int time) {
        if (System.currentTimeMillis() - timeStamp > 3000) {//3秒内不会重复弹出
            mToast = null;
        }
        timeStamp = System.currentTimeMillis();
        if (mToast != null) {
            mToast.setText(content);
        } else {
            mToast = Toast.makeText(context, content, time);
        }
        mToast.show();
    }


}
