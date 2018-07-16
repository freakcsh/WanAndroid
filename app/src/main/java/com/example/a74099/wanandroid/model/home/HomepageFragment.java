package com.example.a74099.wanandroid.model.home;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;
import com.example.a74099.wanandroid.bean.ArticleListBean;
import com.example.a74099.wanandroid.bean.BannerBean;
import com.example.a74099.wanandroid.model.home.adapter.HomePageAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class HomepageFragment extends BaseFragment<HomepagePresenter> implements HomePageContract.View {
    private ConvenientBanner home_banner;
    private XRecyclerView home_recycle;
    private int curPage = 1;
    private List<ArticleListBean.Datas> mListBeans;
    private HomePageAdapter mHomePageAdapter;
    private List<ArticleListBean.Datas> mList;

    @Override
    protected HomepagePresenter createPresenter() {
        return new HomepagePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initEventAndData(View view) {
        initView(view);
//        mPresenter.getBanner();
        mPresenter.getArticle(String.valueOf(curPage));

    }

    private void initView(View view) {
        mListBeans = new ArrayList<>();
        home_banner = view.findViewById(R.id.home_banner);
        home_recycle = view.findViewById(R.id.home_recycle);
        home_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        home_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                curPage = 1;
                mPresenter.getArticle(String.valueOf(curPage));
            }

            @Override
            public void onLoadMore() {
                curPage ++;
                mPresenter.getArticle(String.valueOf(curPage));
            }
        });
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {

    }

    @Override
    public void getHomepageSuccess() {

    }

    @Override
    public void getBannerSuccess(final List<BannerBean> list) {
        home_banner.setPages(new CBViewHolderCreator<String>() {
            @Override
            public String createHolder() {
                return null;
            }
        }, list);
    }

    @Override
    public void getBannerError(String msg) {

    }

    @Override
    public void getArticleSuccess(ArticleListBean articleListBean) {
        mList = articleListBean.getDatas();
        if (curPage == 1) {
            refresh(mList);
        } else {
            loading(mList);
        }
    }

    private void loading(List<ArticleListBean.Datas> mList) {
        if (mList != null && mList.size() != 0) {
            mListBeans.addAll(mList);
            mHomePageAdapter.setData(mListBeans);
            mHomePageAdapter.notifyDataSetChanged();
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
            mListBeans.clear();
            mListBeans.addAll(mList);
            if (mHomePageAdapter == null) {
                mHomePageAdapter = new HomePageAdapter(getActivity(), mListBeans);
                home_recycle.setAdapter(mHomePageAdapter);
            } else {
                mHomePageAdapter.setData(mListBeans);
                mHomePageAdapter.notifyDataSetChanged();
            }
            home_recycle.refreshComplete();
        }
    }

    @Override
    public void getArticleError(String msg) {

    }

    @Override
    public void showError(String msg) {

    }
}
