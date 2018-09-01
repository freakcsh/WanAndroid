package com.example.a74099.wanandroid.model.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a74099.wanandroid.bean.BannerBean;
import com.example.a74099.wanandroid.util.ToolUtils;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.List;

/**
 * Created by 74099 on 2018/9/1.
 */

public class HomeRollAdapter extends StaticPagerAdapter {
    private Context context;
    private List<BannerBean> dataList;

    public HomeRollAdapter(Context context, List list) {
        this.context = context;
        this.dataList = list;
    }

    @Override
    public View getView(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(container.getContext());

        Glide.with(context).load(dataList.get(position).getImagePath()).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .MATCH_PARENT));
        if (!ToolUtils.isNull(dataList.get(position).getUrl())) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posIntent(position, dataList.get(position).getUrl());
                }
            });
        }

        return imageView;
    }

    private void posIntent(int pos, String link) {
        Intent intent;
        /**
         * pos=0 跳转外链
         * pos=1 跳转商品 wap页面 根据 link id去跳转
         * pos=2 跳转文章 wap页面 根据link id去跳转
         */

        switch (pos) {
            case 0:
//                intent = new Intent(context, UrlAct.class);
//                intent.putExtra("url", link);
//                context.startActivity(intent);
                break;
            case 1:
//                intent = new Intent(context, ArticleAct.class);
//                intent.putExtra("link", link);
//                intent.putExtra("pos", pos);
//                context.startActivity(intent);
                break;
            case 2:
//                intent = new Intent(context, ArticleAct.class);
//                intent.putExtra("link", link);
//                intent.putExtra("pos", pos);
//                context.startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    public void setData(List<BannerBean> dataList) {
        this.dataList = dataList;
    }
}
