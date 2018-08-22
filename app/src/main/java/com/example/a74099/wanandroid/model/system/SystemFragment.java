package com.example.a74099.wanandroid.model.system;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;
import com.example.a74099.wanandroid.base.MBaseAdapter;
import com.example.a74099.wanandroid.bean.SystemClassifyBean;
import com.example.a74099.wanandroid.bean.SystemDetailBean;
import com.example.a74099.wanandroid.dialog.SystemSecondDialogFragment;
import com.example.a74099.wanandroid.model.home.activity.ArticleDetailAct;
import com.example.a74099.wanandroid.model.system.adapter.SystemDetailAdapter;
import com.example.a74099.wanandroid.model.system.adapter.SystemFirstAdapter;
import com.example.a74099.wanandroid.util.DialogUtil;
import com.example.a74099.wanandroid.util.ToolUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 体系
 */
public class SystemFragment extends BaseFragment<SystemPresenter> implements SystemContract.View {
    private LinearLayout system_first_no_data, system_detail_no_data;
    private XRecyclerView system_detail_recycle;
    private RecyclerView system_first_recycle;
    private SystemFirstAdapter mSystemFirstAdapter;

    private List<SystemClassifyBean> mClassifyBeanList;
    private List<SystemClassifyBean.Children> mChildrenList;
    private TextView tv_system_second_name;
    private int curPage = 1;
    private List<SystemDetailBean.Datas> list;
    private SystemDetailAdapter systemDetailAdapter;
    private static int mId;

    @Override
    protected SystemPresenter createPresenter() {
        return new SystemPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initEventAndData(View view) {
        initView(view);
        initRecycle();

    }

    private void initRecycle() {
        system_first_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        system_detail_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        system_detail_recycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                curPage = 1;
                mPresenter.getClassifyDetail(String.valueOf(curPage), String.valueOf(mId));
            }

            @Override
            public void onLoadMore() {
                curPage++;
                mPresenter.getClassifyDetail(String.valueOf(curPage), String.valueOf(mId));
            }
        });
    }

    private void initView(View view) {
        list = new ArrayList<>();
        system_first_no_data = view.findViewById(R.id.system_first_no_data);
        system_first_recycle = view.findViewById(R.id.system_first_recycle);
        system_detail_recycle = view.findViewById(R.id.system_detail_recycle);
        system_detail_no_data = view.findViewById(R.id.system_detail_no_data);
        tv_system_second_name = view.findViewById(R.id.tv_system_second_name);
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getSystem();
    }

    @Override
    protected void showLoading() {
        ToolUtils.showLoading(getActivity());
    }

    /**
     * 获取一级与二级分类成功回调
     *
     * @param systemClassifyBeanList
     */
    @Override
    public void getSystemSuccess(List<SystemClassifyBean> systemClassifyBeanList) {
        if (systemClassifyBeanList != null && systemClassifyBeanList.size() != 0) {
            mClassifyBeanList = systemClassifyBeanList;
            mId = systemClassifyBeanList.get(0).getChildren().get(0).getId();
            mPresenter.getClassifyDetail(String.valueOf(curPage), String.valueOf(mId));
        }
        /**
         * 一级分类recycleView
         */
        if (mClassifyBeanList != null && mClassifyBeanList.size() != 0) {
            system_first_no_data.setVisibility(View.GONE);
            system_first_recycle.setVisibility(View.VISIBLE);
            if (mSystemFirstAdapter == null) {
                mSystemFirstAdapter = new SystemFirstAdapter(getActivity(), mClassifyBeanList);
                system_first_recycle.setAdapter(mSystemFirstAdapter);
                mSystemFirstAdapter.setSelect(0);
                mSystemFirstAdapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object o) {
                        mSystemFirstAdapter.setData(mClassifyBeanList);
                        mSystemFirstAdapter.setSelect(position);
                        mSystemFirstAdapter.notifyDataSetChanged();
                        mChildrenList = (List<SystemClassifyBean.Children>) o;
                        DialogUtil.showSystemSecond(getActivity(), "二级分类", mChildrenList, new SystemSecondDialogFragment.OnTipsListener() {
                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onSuccess(String name, int id) {
                                Log.e("freak", "name=" + name + "id=" + id);
                                mId = id;
                                curPage=1;
                                tv_system_second_name.setText(name);
                                mPresenter.getClassifyDetail(String.valueOf(curPage), String.valueOf(mId));
                            }
                        });

                    }
                });
            } else {
                mSystemFirstAdapter.setData(mClassifyBeanList);
                mSystemFirstAdapter.setSelect(0);
                mSystemFirstAdapter.notifyDataSetChanged();
            }
        } else {
            system_first_no_data.setVisibility(View.VISIBLE);
            system_first_recycle.setVisibility(View.GONE);
        }
        ToolUtils.dismissLoading(getActivity());
    }

    /**
     * 获取分类列表成功回调
     *
     * @param systemDetailBean
     */
    @Override
    public void getClassifyDetailSuccess(SystemDetailBean systemDetailBean) {
        if (systemDetailBean != null) {
            List<SystemDetailBean.Datas> dataList = systemDetailBean.getDatas();
            if (curPage == 1) {
                refresh(dataList);
            } else {
                loading(dataList);
            }
        }
    }

    private void loading(List<SystemDetailBean.Datas> mList) {
        if (mList != null && mList.size() != 0) {
            system_detail_recycle.loadMoreComplete();
            list.addAll(mList);
            systemDetailAdapter.setData(list);
            systemDetailAdapter.notifyDataSetChanged();

        } else {
            system_detail_recycle.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    system_detail_recycle.setNoMore(true);
                }
            }, 1000);
        }
    }

    private void refresh(List<SystemDetailBean.Datas> mList) {
        if (mList != null && mList.size() != 0) {
            list.clear();
            list.addAll(mList);
            if (systemDetailAdapter == null) {
                systemDetailAdapter = new SystemDetailAdapter(getActivity(), list);
                system_detail_recycle.setAdapter(systemDetailAdapter);
                systemDetailAdapter.setOnItemClickListener(new SystemDetailAdapter.OnItemClickListener() {
                    @Override
                    public void doCollage(SystemDetailBean.Datas mData, ImageView imageView) {
                        if (mData.getCollect()) {
                            imageView.setSelected(false);
                        } else {
                            imageView.setSelected(true);
                        }
                    }

                    @Override
                    public void doIntern(SystemDetailBean.Datas mData) {
                        Intent intent = new Intent(getActivity(), ArticleDetailAct.class);
                        intent.putExtra("url", mData.getLink());
                        intent.putExtra("title", mData.getTitle());
                        startActivity(intent);
                    }
                });
            } else {
                systemDetailAdapter.setData(list);
                systemDetailAdapter.notifyDataSetChanged();
            }
            system_detail_recycle.refreshComplete();
        }
    }

    @Override
    public void showError(String msg) {

    }

}
