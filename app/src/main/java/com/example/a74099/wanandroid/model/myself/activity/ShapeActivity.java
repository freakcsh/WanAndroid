package com.example.a74099.wanandroid.model.myself.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.SimpleActivity;
import com.example.a74099.wanandroid.util.QRCodeUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by 74099 on 2018/8/29.
 */

public class ShapeActivity extends SimpleActivity {
    private RelativeLayout rl_share;
    private ImageView img_share_qr_code;
    private Bitmap bitmap;
    private String save;

    public static void startAction(Context context) {
        Intent intent = new Intent(context, ShapeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.act_shape;
    }

    @Override
    protected void initEventAndData() {
        setBackPress();
        setTitleTx("分享");
        rl_share = findViewById(R.id.rl_share);
        img_share_qr_code = findViewById(R.id.img_share_qr_code);
        rl_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });

        setRqCode();
    }

    private void setRqCode() {
        try {
            bitmap = new QRCodeUtil().createCode("https://pan.baidu.com/s/1rQDDfK4jSAhdidCZcEA5EQ", BarcodeFormat.QR_CODE, 250, 250);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        img_share_qr_code.setImageBitmap(bitmap);
//        save = ToolUtils.save(bitmap, Bitmap.CompressFormat.PNG, 250, this);
    }

    private void showShare() {
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle("玩转Android");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("https://pan.baidu.com/s/1rQDDfK4jSAhdidCZcEA5EQ");
        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath(save);//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("https://pan.baidu.com/s/1rQDDfK4jSAhdidCZcEA5EQ");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");


        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setTitle("玩转Android");
        oks.setText("玩转Android是一款技术博文阅读APp，需要的朋友可扫码或者点击链接下载。");
//        oks.setImagePath(save);//确保SDcard下面存在此张图片
        oks.setImageData(bitmap);
//        oks.setUrl("https://pan.baidu.com/s/1rQDDfK4jSAhdidCZcEA5EQ");
        oks.setSite(getString(R.string.app_name));
// 启动分享GUI
        oks.show(this);
    }

}
