package com.example.a74099.wanandroid.model.system;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;
import com.example.a74099.wanandroid.base.MBaseAdapter;
import com.example.a74099.wanandroid.bean.SystemClassifyBean;
import com.example.a74099.wanandroid.model.system.adapter.SystemFirstAdapter;
import com.example.a74099.wanandroid.model.system.adapter.SystemSecondAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * 体系
 */
public class SystemFragment extends BaseFragment<SystemPresenter> implements SystemContract.View, View.OnClickListener {
    private LinearLayout system_first_no_data, system_second_no_data;
    private XRecyclerView system_first_recycle, system_second_recycle;
    private SystemFirstAdapter mSystemFirstAdapter;
    private SystemSecondAdapter mSystemSecondAdapter;
    private List<SystemClassifyBean> mClassifyBeanList;
    private List<SystemClassifyBean.Children> mChildrenList;
    private LinearLayout ll_system_classify,ll_system_second_recycle,ll_system_first_recycle;

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
        system_first_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        system_second_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initView(View view) {
//        mChildrenList=new ArrayList<>();
        system_first_no_data = view.findViewById(R.id.system_first_no_data);
        system_second_no_data = view.findViewById(R.id.system_second_no_data);
        system_first_recycle = view.findViewById(R.id.system_first_recycle);
        system_second_recycle = view.findViewById(R.id.system_second_recycle);
        ll_system_classify = view.findViewById(R.id.ll_system_classify);
        ll_system_second_recycle = view.findViewById(R.id.ll_system_second_recycle);
        ll_system_first_recycle = view.findViewById(R.id.ll_system_first_recycle);
        ll_system_classify.setOnClickListener(this);
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
//            for (int i = 0; i < systemClassifyBeanList.size(); i++){
//                mChildrenList.add(systemClassifyBeanList.get(i).getChildren());
//            }
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
                        ll_system_first_recycle.setVisibility(View.GONE);
//                        ll_system_second_recycle.setVisibility(View.VISIBLE);
                        mChildrenList = (List<SystemClassifyBean.Children>) o;

                        /**
                         * 二级分类recycleView
                         */
                        if (mChildrenList != null && mChildrenList.size() != 0) {
                            system_second_no_data.setVisibility(View.GONE);
                            system_second_recycle.setVisibility(View.VISIBLE);
                            if (mSystemSecondAdapter == null) {
                                mSystemSecondAdapter = new SystemSecondAdapter(getActivity(), mChildrenList);
                                system_second_recycle.setAdapter(mSystemSecondAdapter);
                            } else {
                                mSystemSecondAdapter.setData(mChildrenList);
                                mSystemSecondAdapter.notifyDataSetChanged();
                            }
                        } else {
                            system_second_no_data.setVisibility(View.VISIBLE);
                            system_second_recycle.setVisibility(View.GONE);
                        }
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


        /**
         * 二级分类recycleView
         */
        mChildrenList = systemClassifyBeanList.get(0).getChildren();
        if (mChildrenList != null && mChildrenList.size() != 0) {
            system_second_no_data.setVisibility(View.GONE);
            system_second_recycle.setVisibility(View.VISIBLE);
            if (mSystemSecondAdapter == null) {
                mSystemSecondAdapter = new SystemSecondAdapter(getActivity(), mChildrenList);
                system_second_recycle.setAdapter(mSystemSecondAdapter);
            } else {
                mSystemSecondAdapter.setData(mChildrenList);
                mSystemSecondAdapter.notifyDataSetChanged();
            }
        } else {
            system_second_no_data.setVisibility(View.VISIBLE);
            system_second_recycle.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_system_classify:
                ll_system_first_recycle.setVisibility(View.VISIBLE);
//                ll_system_second_recycle.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
