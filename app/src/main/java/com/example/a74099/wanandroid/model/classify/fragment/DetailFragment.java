package com.example.a74099.wanandroid.model.classify.fragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;
import com.example.a74099.wanandroid.bean.ClassifyBean;
import com.example.a74099.wanandroid.bean.ClassifyTitleBean;
import com.example.a74099.wanandroid.model.classify.ClassifyContract;
import com.example.a74099.wanandroid.model.classify.ClassifyPresenter;
import com.example.a74099.wanandroid.model.classify.adapter.ClassifyDetailAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends BaseFragment<ClassifyPresenter> implements ClassifyContract.View {
    private XRecyclerView classify_recycle;
    private ClassifyDetailAdapter mClassifyDetailAdapter;
    private List<ClassifyBean.Datas> mList;
    private int page = 1;
    private String cid = "";

    @Override
    protected ClassifyPresenter createPresenter() {
        return new ClassifyPresenter();
    }

    public static DetailFragment newInstance(String cid) {
//        Bundle args = new Bundle();
//        args.putSerializable("marketTitleEntity", marketTitleEntity);
        DetailFragment fragment = new DetailFragment();
//        fragment.setArguments(args);
        fragment.cid = cid;
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify_detail;
    }

    @Override
    protected void initEventAndData(View view) {
        mList = new ArrayList<>();
        classify_recycle = view.findViewById(R.id.classify_recycle);

        classify_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        classify_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getClassify(String.valueOf(page), cid);
            }

            @Override
            public void onLoadMore() {

            }
        });
        mPresenter.getClassify(String.valueOf(page), cid);
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {

    }

    @Override
    public void getClassifySuccess(ClassifyBean model) {
        List<ClassifyBean.Datas> cDatasList = model.getDatas();
        if (page == 1) {
            refresh(cDatasList);
        } else {
            loading(cDatasList);
        }
    }

    public void refresh(List<ClassifyBean.Datas> list) {
        if (list != null && list.size() != 0) {

            classify_recycle.setVisibility(View.VISIBLE);
            mList.clear();
            mList.addAll(list);
            if (mClassifyDetailAdapter == null) {
                mClassifyDetailAdapter = new ClassifyDetailAdapter(mList, getActivity());
                classify_recycle.setAdapter(mClassifyDetailAdapter);
            } else {
                mClassifyDetailAdapter.setData(mList);
                mClassifyDetailAdapter.notifyDataSetChanged();
            }
            classify_recycle.refreshComplete();
        } else {
            classify_recycle.setVisibility(View.GONE);
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
}
