package com.example.a74099.wanandroid.model.system;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;
import com.example.a74099.wanandroid.base.MBaseAdapter;
import com.example.a74099.wanandroid.bean.SystemClassifyBean;
import com.example.a74099.wanandroid.bean.SystemDetailBean;
import com.example.a74099.wanandroid.dialog.SystemSecondDialogFragment;
import com.example.a74099.wanandroid.model.system.adapter.SystemFirstAdapter;
import com.example.a74099.wanandroid.util.DialogUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * 体系
 */
public class SystemFragment extends BaseFragment<SystemPresenter> implements SystemContract.View {
    private LinearLayout system_first_no_data, system_detail_no_data;
    private XRecyclerView system_first_recycle, system_detail_recycle;
    private SystemFirstAdapter mSystemFirstAdapter;

    private List<SystemClassifyBean> mClassifyBeanList;
    private List<SystemClassifyBean.Children> mChildrenList;
    private TextView tv_system_second_name;
    private int curPage=1;

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
    }

    private void initView(View view) {
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
                                tv_system_second_name.setText(name);
                                mPresenter.getClassifyDetail(String.valueOf(curPage), String.valueOf(id));
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

    }

    /**
     * 获取分类列表成功回调
     *
     * @param systemDetailBean
     */
    @Override
    public void getClassifyDetailSuccess(SystemDetailBean systemDetailBean) {

    }

    @Override
    public void showError(String msg) {

    }

}
