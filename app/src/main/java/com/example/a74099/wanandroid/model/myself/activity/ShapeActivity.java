package com.example.a74099.wanandroid.model.myself.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.SimpleActivity;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by 74099 on 2018/8/29.
 */

public class ShapeActivity extends SimpleActivity {
    private Button btn_shape;
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
        btn_shape = findViewById(R.id.btn_shape);
        btn_shape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
    }
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享测试");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
//        Uri imageUri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(),bitmap , null,null));
//        Intent shareIntent = new Intent();
//        shareIntent.setAction(Intent.ACTION_SEND);
//        shareIntent.putExtra("Kdescription", "分享朋友圈的图片说明");
//        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
//        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "这是我的博客地址：https://blog.csdn.net/freak_csh");
//
//        shareIntent.setType("image/*");
//        startActivity(Intent.createChooser(shareIntent, "分享到"));
    }
}
