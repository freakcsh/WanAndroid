package com.example.a74099.wanandroid.model.frequently;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseActivity;
import com.example.a74099.wanandroid.bean.FrequentlyBean;
import com.example.a74099.wanandroid.model.home.activity.ArticleDetailAct;
import com.example.a74099.wanandroid.util.ToastUtil;
import com.example.a74099.wanandroid.util.ToolUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * Created by 74099 on 2018/9/6.
 */

public class FrequentlyActivity extends BaseActivity<FrequentlyPresenter> implements FrequentlyContract.View {
    private TagFlowLayout frequently_flow_layout;
    private List<FrequentlyBean> mFrequentlyBeanList;

    public static void startAction(Context context) {
        Intent intent = new Intent(context, FrequentlyActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void getFrequentlySuccess(List<FrequentlyBean> frequentlyBeanList) {
        mFrequentlyBeanList=frequentlyBeanList;
        frequently_flow_layout.setAdapter(new TagAdapter<FrequentlyBean>(frequentlyBeanList) {
            @Override
            public View getView(FlowLayout parent, int position, FrequentlyBean frequentlyBean) {
                TextView tv = (TextView) LayoutInflater.from(FrequentlyActivity.this).inflate(R.layout.flow_layout_tv,
                        parent, false);
                assert frequentlyBean != null;
                String name = frequentlyBean.getName();
                tv.setText(name);
                setItemBackground(tv);
                frequently_flow_layout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        ArticleDetailAct.startAction(FrequentlyActivity.this,mFrequentlyBeanList.get(position).getName(),mFrequentlyBeanList.get(position).getLink());
                        return true;
                    }
                });


                return tv;
            }
        });
    }

    @Override
    public void getFrequentlyError(String msg) {
        ToastUtil.showShort(this, msg);
    }

    private void setItemBackground(TextView tv) {
        tv.setBackgroundColor(ToolUtils.randomTagColor());
        tv.setTextColor(ContextCompat.getColor(FrequentlyActivity.this, R.color.c_ffffff));
    }

    @Override
    protected int getLayout() {
        return R.layout.act_frequently;
    }

    @Override
    protected void initEventAndData() {
        setBackPress();
        setTitleTx("常用网址");
        frequently_flow_layout = findViewById(R.id.frequently_flow_layout);
        mPresenter.getFrequently();
    }

    @Override
    protected FrequentlyPresenter createPresenter() {
        return new FrequentlyPresenter();
    }
}
