package com.example.a74099.wanandroid.model.myself.activity.collect;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseActivity;
import com.example.a74099.wanandroid.bean.CollectBean;
import com.example.a74099.wanandroid.model.myself.activity.collect.adapter.CollectAdapter;
import com.example.a74099.wanandroid.model.myself.activity.collect.detail.CollectDetailAct;
import com.example.a74099.wanandroid.util.ToastUtil;
import com.example.a74099.wanandroid.util.ToolUtils;
import com.example.a74099.wanandroid.view.pullrefreshview.layout.PullRefreshLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 收藏
 * Created by 74099 on 2018/8/29.
 */

public class CollectActivity extends BaseActivity<CollectPresenter> implements CollectContract.View {

    private List<CollectBean.Datas> mList;
    private int curPage = 1;
    private PullRefreshLayout collect_include_no_data;
    private XRecyclerView collect_recycle;
    private CollectAdapter mCollectAdapter;
    private List<CollectBean.Datas> mListBeans;

    @Override
    public void showError(String msg) {

    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, CollectActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.act_collect;
    }

    @Override
    protected void initEventAndData() {
        initView();
        mPresenter.getCollectList(String.valueOf(curPage));
    }

    private void initView() {
        setBackPress();
        setTitleTx("我的收藏");
        mListBeans = new ArrayList<>();
//        collect_include_no_data = findViewById(R.id.collect_include_no_data);
        collect_recycle = findViewById(R.id.collect_recycle);
        collect_recycle.setLayoutManager(new LinearLayoutManager(this));
        collect_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                curPage = 1;
                mPresenter.getCollectList(String.valueOf(curPage));
            }

            @Override
            public void onLoadMore() {
                curPage++;
                mPresenter.getCollectList(String.valueOf(curPage));
            }
        });
    }

    @Override
    protected CollectPresenter createPresenter() {
        return new CollectPresenter();
    }

    @Override
    public void getCollectListSuccess(CollectBean collectBean) {
        mList = collectBean.getDatas();
        if (curPage == 1) {
            refresh(mList);
        } else {
            loading(mList);
        }
        ToolUtils.dismissLoading(this);
    }

    private void loading(List<CollectBean.Datas> mList) {
        if (mList != null && mList.size() != 0) {
            collect_recycle.loadMoreComplete();
            mListBeans.addAll(mList);
            mCollectAdapter.setData(mListBeans);
            mCollectAdapter.notifyDataSetChanged();

        } else {
            collect_recycle.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    collect_recycle.setNoMore(true);
                }
            }, 1000);
        }
    }

    private void refresh(List<CollectBean.Datas> mList) {
        if (mList != null && mList.size() != 0) {
//            collect_include_no_data.setVisibility(View.GONE);
            collect_recycle.setVisibility(View.VISIBLE);
            mListBeans.clear();
            mListBeans.addAll(mList);
            if (mCollectAdapter == null) {
                mCollectAdapter = new CollectAdapter(this, mListBeans);
                collect_recycle.setAdapter(mCollectAdapter);
                mCollectAdapter.setOnItemClickListener(new CollectAdapter.OnItemClickListener() {
                    @Override
                    public void doCancelCollage(CollectBean.Datas mData, ImageView imageView) {
                        if (ToolUtils.isNull(String.valueOf(mData.getOriginId()))){
                            mPresenter.doCollectCancel(mData.getId(),-1);
                        }else {
                            mPresenter.doCollectCancel(mData.getId(),mData.getOriginId());
                        }
                    }

                    @Override
                    public void doIntern(CollectBean.Datas mData) {
                        Intent intent = new Intent(CollectActivity.this, CollectDetailAct.class);
                        intent.putExtra("url", mData.getLink());
                        intent.putExtra("title", mData.getTitle());
                        startActivity(intent);
                    }
                });
            } else {
                mCollectAdapter.setData(mListBeans);
                mCollectAdapter.notifyDataSetChanged();
            }
            collect_recycle.refreshComplete();
        } else {
//            collect_include_no_data.setVisibility(View.VISIBLE);
            collect_recycle.setVisibility(View.GONE);
        }
    }

    @Override
    public void getCollectError(String mse) {

    }

    /**
     * 取消收藏成功回调
     */
    @Override
    public void doCollectCancelSuccess() {
        curPage = 1;
        mPresenter.getCollectList(String.valueOf(curPage));
        ToastUtil.showShort(this,"取消收藏成功");
    }
}
