package com.example.a74099.wanandroid.model.search.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseActivity;
import com.example.a74099.wanandroid.bean.SearchDetailBean;
import com.example.a74099.wanandroid.model.home.activity.ArticleDetailAct;
import com.example.a74099.wanandroid.model.login.LoginActivity;
import com.example.a74099.wanandroid.model.search.adapter.SearchDetailAdapter;
import com.example.a74099.wanandroid.util.ToastUtil;
import com.example.a74099.wanandroid.util.ToolUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 74099 on 2018/9/6.
 */

public class SearchDetailActivity extends BaseActivity<SearchDetailContract.Presenter> implements SearchDetailContract.Viev {
    private String searchMsg;
    private LinearLayout search_detail_no_data;
    private XRecyclerView search_detail_recycle;
    private int page = 0;
    private List<SearchDetailBean.Datas> mListBeans;
    private SearchDetailAdapter mSearchDetailAdapter;

    public static void startAction(Context context, String searchMsg) {
        Intent intent = new Intent(context, SearchDetailActivity.class);
        intent.putExtra("searchMsg", searchMsg);
        context.startActivity(intent);
    }

    @Override
    public void showError(String msg) {

    }

    /***
     * 获取成功回调
     */
    @Override
    public void getSearchDetailSuccess(SearchDetailBean searchDetailBeanList) {
        List<SearchDetailBean.Datas> datas = searchDetailBeanList.getDatas();
        if (page == 0) {
            refresh(datas);
        } else {
            loading(datas);
        }
    }

    @Override
    public void getSearchDetailError(String msg) {
        ToastUtil.showShort(this, msg);
    }

    /**
     * 取消收藏成功
     */
    @Override
    public void doCancelCollectSuccess() {
        page = 1;
        mPresenter.getSearchDetail(page,searchMsg);
        ToastUtil.showShort(this, "取消收藏成功");
    }

    @Override
    public void doCollectError() {
        ToastUtil.showShort(this, "登录过期，请重新登录");
        ToolUtils.logout(this);
        LoginActivity.startAction(this);
    }

    @Override
    public void doCancelCollectError() {
        ToastUtil.showShort(this, "登录过期，请重新登录");
        ToolUtils.logout(this);
        LoginActivity.startAction(this);
    }

    @Override
    public void doCollectSuccess() {
        page = 1;
        mPresenter.getSearchDetail(page,searchMsg);
        ToastUtil.showShort(this, "收藏成功");
    }

    @Override
    protected int getLayout() {
        return R.layout.act_search_detail;
    }

    @Override
    protected void initEventAndData() {
        searchMsg = getIntent().getStringExtra("searchMsg");
        mListBeans = new ArrayList<>();
        setBackPress();
        setTitleTx(searchMsg);
        search_detail_no_data = findViewById(R.id.search_detail_no_data);
        search_detail_recycle = findViewById(R.id.search_detail_recycle);
        search_detail_recycle.setLayoutManager(new LinearLayoutManager(this));
        search_detail_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 0;
                mPresenter.getSearchDetail(page, searchMsg);
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getSearchDetail(page, searchMsg);
            }
        });
        page=0;
        mPresenter.getSearchDetail(page, searchMsg);
    }

    private void loading(List<SearchDetailBean.Datas> mList) {
        if (mList != null && mList.size() != 0) {
            search_detail_recycle.loadMoreComplete();
            mListBeans.addAll(mList);
            mSearchDetailAdapter.setData(mListBeans);
            mSearchDetailAdapter.notifyDataSetChanged();

        } else {
            search_detail_recycle.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    search_detail_recycle.setNoMore(true);
                }
            }, 1000);
        }
    }

    private void refresh(List<SearchDetailBean.Datas> mList) {
        if (mList != null && mList.size() != 0) {
            search_detail_no_data.setVisibility(View.GONE);
            search_detail_recycle.setVisibility(View.VISIBLE);
            mListBeans.clear();
            mListBeans.addAll(mList);
            if (mSearchDetailAdapter == null) {
                mSearchDetailAdapter = new SearchDetailAdapter(this, mListBeans);
                search_detail_recycle.setAdapter(mSearchDetailAdapter);
                mSearchDetailAdapter.setOnItemClickListener(new SearchDetailAdapter.OnItemClickListener() {
                    @Override
                    public void doCollage(SearchDetailBean.Datas mData, ImageView imageView) {
                        if (mData.getCollect()) {
                            mPresenter.doCancelCollect(mData.getId());
                        } else {
                            mPresenter.doCollect(mData.getId());
                        }
                    }

                    @Override
                    public void doIntern(SearchDetailBean.Datas mData) {
                        ArticleDetailAct.startAction(SearchDetailActivity.this,mData.getTitle(),mData.getLink());
                    }
                });
            } else {
                mSearchDetailAdapter.setData(mListBeans);
                mSearchDetailAdapter.notifyDataSetChanged();
            }
            search_detail_recycle.refreshComplete();
        } else {
            search_detail_no_data.setVisibility(View.VISIBLE);
            search_detail_recycle.setVisibility(View.GONE);
        }
    }

    @Override
    protected SearchDetailContract.Presenter createPresenter() {
        return new SearchDetailPresenter();
    }
}
