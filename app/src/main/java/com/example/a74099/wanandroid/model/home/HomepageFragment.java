package com.example.a74099.wanandroid.model.home;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;
import com.example.a74099.wanandroid.bean.ArticleListBean;
import com.example.a74099.wanandroid.bean.BannerBean;
import com.example.a74099.wanandroid.model.home.activity.ArticleDetailAct;
import com.example.a74099.wanandroid.model.home.activity.BannerDetailAct;
import com.example.a74099.wanandroid.model.home.adapter.HomePageAdapter;
import com.example.a74099.wanandroid.model.home.adapter.HomeRollAdapter;
import com.example.a74099.wanandroid.model.login.LoginActivity;
import com.example.a74099.wanandroid.net.util.NetworkType;
import com.example.a74099.wanandroid.util.ToastUtil;
import com.example.a74099.wanandroid.util.ToolUtils;
import com.example.a74099.wanandroid.view.pullrefreshview.layout.PullRefreshLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

/***
 * 首页
 */
public class HomepageFragment extends BaseFragment<HomepagePresenter> implements HomePageContract.View {
    private XRecyclerView home_recycle;
    private int curPage = 1;
    private List<ArticleListBean.Datas> mListBeans;
    private HomePageAdapter mHomePageAdapter;
    private List<ArticleListBean.Datas> mList;
    private List<String> mStringList;
    private PullRefreshLayout include_no_data;
    private RelativeLayout netErrorView;
    private RollPagerView roll_pagerView;
    private HomeRollAdapter rollAdapter;

    @Override
    protected HomepagePresenter createPresenter() {
        return new HomepagePresenter();
    }

    /***
     * 断网重连
     * @param networkType
     */
    @Override
    public void onNetConnected(NetworkType networkType) {
        super.onNetConnected(networkType);
        curPage = 1;
        mPresenter.getBanner();
        mPresenter.getArticle(String.valueOf(curPage));
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initEventAndData(View view) {
        initView(view);
        mPresenter.getBanner();
        mPresenter.getArticle(String.valueOf(curPage));

    }

    private void initView(View view) {
        mListBeans = new ArrayList<>();
        mStringList = new ArrayList<>();

        home_recycle = view.findViewById(R.id.home_recycle);
        include_no_data = view.findViewById(R.id.include_no_data);
        home_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        home_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                curPage = 1;
                mPresenter.getArticle(String.valueOf(curPage));

            }

            @Override
            public void onLoadMore() {
                curPage++;
                if (curPage == 0) {
                    mPresenter.getArticle("0");
                } else {
                    mPresenter.getArticle(String.valueOf(curPage));
                }
            }
        });
        roll_pagerView = view.findViewById(R.id.roll_pagerView);
        //设置播放时间间隔
        roll_pagerView.setPlayDelay(3000);
        //设置透明度
        roll_pagerView.setAnimationDurtion(500);
        roll_pagerView.setHintView(new ColorPointHintView(getActivity(), 0xffff6d1d, 0xffeeeeee));

    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {
//        if (mList != null && mList.size() != 0){
//            ToolUtils.showLoading(getActivity());
//        }
    }

    /**
     * 收藏成功
     */
    @Override
    public void doCollectSuccess() {
        curPage = 1;
        mPresenter.getArticle(String.valueOf(curPage));
        ToastUtil.showShort(getActivity(), "收藏成功");
    }

    /***
     * 获取首页轮播图成功回调
     * @param list
     */
    @Override
    public void getBannerSuccess(final List<BannerBean> list) {
        if (rollAdapter == null) {
            rollAdapter = new HomeRollAdapter(getActivity(), list);
            roll_pagerView.setAdapter(rollAdapter);
        } else {
            rollAdapter.setData(list);
            rollAdapter.notifyDataSetChanged();
        }
        roll_pagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), BannerDetailAct.class);
                intent.putExtra("url", list.get(position).getUrl());
                intent.putExtra("title", list.get(position).getTitle());
                startActivity(intent);
            }
        });
    }

    @Override
    public void getBannerError(String msg) {

    }

    /**
     * 获取文章成功回调
     *
     * @param articleListBean
     */
    @Override
    public void getArticleSuccess(ArticleListBean articleListBean) {
        mList = articleListBean.getDatas();
        if (curPage == 1) {
            refresh(mList);
        } else {
            loading(mList);
        }
        ToolUtils.dismissLoading(getActivity());
    }

    private void loading(List<ArticleListBean.Datas> mList) {
        if (mList != null && mList.size() != 0) {
            home_recycle.loadMoreComplete();
            mListBeans.addAll(mList);
            mHomePageAdapter.setData(mListBeans);
            mHomePageAdapter.notifyDataSetChanged();

        } else {
            home_recycle.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    home_recycle.setNoMore(true);
                }
            }, 1000);
        }
    }

    private void refresh(List<ArticleListBean.Datas> mList) {
        if (mList != null && mList.size() != 0) {
            include_no_data.setVisibility(View.GONE);
            home_recycle.setVisibility(View.VISIBLE);
            mListBeans.clear();
            mListBeans.addAll(mList);
            if (mHomePageAdapter == null) {
                mHomePageAdapter = new HomePageAdapter(getActivity(), mListBeans);
                home_recycle.setAdapter(mHomePageAdapter);
                mHomePageAdapter.setOnItemClickListener(new HomePageAdapter.OnItemClickListener() {
                    @Override
                    public void doCollage(ArticleListBean.Datas mData, ImageView imageView) {
                        if (mData.getCollect()) {
                            mPresenter.doCancelCollect(mData.getId());
                        } else {
                            mPresenter.doCollect(mData.getId());
                        }
                    }

                    @Override
                    public void doIntern(ArticleListBean.Datas mData) {
                        Intent intent = new Intent(getActivity(), ArticleDetailAct.class);
                        intent.putExtra("url", mData.getLink());
                        intent.putExtra("title", mData.getTitle());
                        startActivity(intent);
                    }
                });
            } else {
                mHomePageAdapter.setData(mListBeans);
                mHomePageAdapter.notifyDataSetChanged();
            }
            home_recycle.refreshComplete();
        } else {
            include_no_data.setVisibility(View.VISIBLE);
            home_recycle.setVisibility(View.GONE);
        }
    }

    @Override
    public void getArticleError(String msg) {

    }

    /**
     * 取消收藏成功
     */
    @Override
    public void doCancelCollectSuccess() {
        curPage = 1;
        mPresenter.getArticle(String.valueOf(curPage));
        ToastUtil.showShort(getActivity(), "取消收藏成功");
    }

    @Override
    public void doCollectError() {
        ToastUtil.showShort(getActivity(), "登录过期，请重新登录");
        ToolUtils.logout(getActivity());
        LoginActivity.startAction(getActivity());
    }

    @Override
    public void doCancelCollectError() {
        ToastUtil.showShort(getActivity(), "登录过期，请重新登录");
        ToolUtils.logout(getActivity());
        LoginActivity.startAction(getActivity());
    }


    @Override
    public void showError(String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        roll_pagerView.stopNestedScroll();
    }
}
