package com.example.a74099.wanandroid.app;

import android.graphics.Color;

/**
 * Created by Administrator on 2018/2/4.
 */

public class Constants {
    /**
     * 服务器地址
     */
    public static final String BASE_URL = "http://www.wanandroid.com/";
    /**
     * 拍照
     */
    public static final int GETPICTURE_TAKEPHOTO = 10001;
    /**
     * 选择手机内的图片
     */
    public static final int GETPICTURE_SELECTPHOTO = 10002;
    /**
     * 裁剪图片
     */
    public static final int CUT_PHOTO = 10003;
    public static final boolean DEBUG = Boolean.parseBoolean("true");

    /**
     * 拍照时所需权限
     */
    public static final int TAKEPHOTO_PERMISSION_REQUESTCODE = 3001;
    /**
     * 选择相册照片所需权限
     */
    public static final int ALBUM_PERMISSION_REQUESTCODE = 3002;

    public final static String IS_LOGIN = "IS_LOGIN";
    public final static String LOGIN_BEAN = "LOGIN_BEAN";
    public static String PHOTO = "photo";
    public static String FINGER = "finger";

    /**
     * Tab colors
     */
    public static final int[] TAB_COLORS = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };
}
