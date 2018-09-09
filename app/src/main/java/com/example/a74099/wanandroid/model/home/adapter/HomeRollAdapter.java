package com.example.a74099.wanandroid.model.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a74099.wanandroid.app.App;
import com.example.a74099.wanandroid.bean.BannerBean;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.List;

/**
 * Created by 74099 on 2018/9/1.
 */

public class HomeRollAdapter extends StaticPagerAdapter {
    private Context context;
    private List<BannerBean> dataList;
    private ImageView mImageView;

    public HomeRollAdapter(Context context, List list) {
        this.context = context;
        this.dataList = list;
    }

    @Override
    public View getView(ViewGroup container, final int position) {

        mImageView = new ImageView(App.getInstance().getApplicationContext());
        Glide.with(App.getInstance().getApplicationContext()).load(dataList.get(position).getImagePath()).into(mImageView);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .MATCH_PARENT));
        return mImageView;
    }



    @Override
    public int getCount() {
        return dataList.size();
    }

    public void setData(List<BannerBean> dataList) {
        this.dataList = dataList;
    }
}
