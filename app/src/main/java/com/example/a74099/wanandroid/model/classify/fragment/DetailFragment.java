package com.example.a74099.wanandroid.model.classify.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;
import com.example.a74099.wanandroid.bean.ClassifyBean;
import com.example.a74099.wanandroid.bean.ClassifyTitleBean;
import com.example.a74099.wanandroid.model.classify.ClassifyContract;
import com.example.a74099.wanandroid.model.classify.ClassifyPresenter;
import com.example.a74099.wanandroid.model.classify.activity.ClassifyDetailActivity;
import com.example.a74099.wanandroid.model.classify.adapter.ClassifyDetailAdapter;
import com.example.a74099.wanandroid.model.login.LoginActivity;
import com.example.a74099.wanandroid.net.util.NetworkType;
import com.example.a74099.wanandroid.util.ToastUtil;
import com.example.a74099.wanandroid.util.ToolUtils;
import com.example.a74099.wanandroid.view.pullrefreshview.layout.PullRefreshLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class DetailFragment extends BaseFragment<ClassifyPresenter> implements ClassifyContract.View {
    private XRecyclerView classify_recycle;
    private ClassifyDetailAdapter mClassifyDetailAdapter;
    private List<ClassifyBean.Datas> mList;
    private int page = 1;
    private String cid = "";
    private PullRefreshLayout classify_include_no_data;

    @Override
    protected ClassifyPresenter createPresenter() {
        return new ClassifyPresenter();
    }

//    public static DetailFragment newInstance(String cid) {
//        Bundle args = new Bundle();
////        args.putSerializable("marketTitleEntity", marketTitleEntity);
//        args.putString("cid", cid);
//        DetailFragment fragment = new DetailFragment();
//        fragment.setArguments(args);
////        fragment.cid = cid;
//        return fragment;
//    }

    /**
     * 断网重连
     * @param networkType
     */
    @Override
    public void onNetConnected(NetworkType networkType) {
        super.onNetConnected(networkType);
        page = 1;
        mPresenter.getClassify(String.valueOf(page), cid);
    }

    public DetailFragment(String cid) {
        this.cid = cid;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify_detail;
    }

    @Override
    protected void initEventAndData(View view) {
        mList = new ArrayList<>();
        classify_recycle = view.findViewById(R.id.classify_recycle);
        classify_include_no_data = view.findViewById(R.id.classify_include_no_data);

        classify_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        classify_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getClassify(String.valueOf(page), cid);
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getClassify(String.valueOf(page), cid);
            }
        });
        classify_recycle.setFootViewText("正在努力加载中...", "我也是有底线的");
    }

    @Override
    protected void initLazyData() {
        page = 1;
        mPresenter.getClassify(String.valueOf(page), cid);

    }


    @Override
    protected void showLoading() {
        ToolUtils.showLoading(getActivity());
    }

    @Override
    public void getClassifySuccess(ClassifyBean model) {

        List<ClassifyBean.Datas> cDatasList = model.getDatas();
        if (page == 1) {
            refresh(cDatasList);
        } else {
            loading(cDatasList);
        }
        ToolUtils.dismissLoading(getActivity());
    }

    public void refresh(List<ClassifyBean.Datas> list) {
        if (list != null && list.size() != 0) {
            classify_include_no_data.setVisibility(View.GONE);
            classify_recycle.setVisibility(View.VISIBLE);
            mList.clear();
            mList.addAll(list);
            if (mClassifyDetailAdapter == null) {
                mClassifyDetailAdapter = new ClassifyDetailAdapter(mList, getActivity());
                classify_recycle.setAdapter(mClassifyDetailAdapter);
                mClassifyDetailAdapter.setmOnItemClickListener(new ClassifyDetailAdapter.OnItemClickListener() {
                    @Override
                    public void doCollage(ClassifyBean.Datas mData, ImageView imageView) {
                        if (mData.getCollect()) {
                            mPresenter.doCancelCollect(mData.getId());
                        } else {
                            mPresenter.doCollect(mData.getId());
                        }
                    }

                    @Override
                    public void doIntern(ClassifyBean.Datas mData) {
                        Intent intent = new Intent(getActivity(), ClassifyDetailActivity.class);
                        intent.putExtra("url", mData.getLink());
                        intent.putExtra("title", mData.getTitle());
                        startActivity(intent);
                    }
                });
            } else {
                mClassifyDetailAdapter.setData(mList);
                mClassifyDetailAdapter.notifyDataSetChanged();
            }
            classify_recycle.refreshComplete();
        } else {
            classify_recycle.setVisibility(View.GONE);
            classify_include_no_data.setVisibility(View.VISIBLE);
        }
    }

    public void loading(List<ClassifyBean.Datas> list) {
        classify_recycle.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            mList.addAll(list);
            mClassifyDetailAdapter.notifyDataSetChanged();
        } else {
            classify_recycle.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    classify_recycle.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    @Override
    public void getClassifyTitleSuccess(List<ClassifyTitleBean> model) {

    }

    @Override
    public void showError(String msg) {

    }

    /**
     * 收藏成功回调
     */
    @Override
    public void doCollectSuccess() {
        page = 1;
        mPresenter.getClassify(String.valueOf(page), cid);
        ToastUtil.showShort(getActivity(),"收藏成功");
    }

    /**
     * 取消收藏成功回调
     */
    @Override
    public void doCancelCollectSuccess() {
        page = 1;
        mPresenter.getClassify(String.valueOf(page), cid);
        ToastUtil.showShort(getActivity(),"取消收藏成功");
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
}
