package com.example.a74099.wanandroid.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.MBaseAdapter;
import com.example.a74099.wanandroid.bean.SystemClassifyBean;
import com.example.a74099.wanandroid.model.system.adapter.SystemSecondAdapter;

import java.util.List;


/**
 * 显示系統提示的对话框，可以自行设置题目，内容，取消，成功
 */
public class SystemSecondDialogFragment extends DialogFragment implements View.OnClickListener {

    private Dialog mDetailDialog;
    private TextView tv_system_title;
    private LinearLayout ll_system_cancel;
    private SystemSecondAdapter mSystemSecondAdapter;
    private RecyclerView system_second_recycle;
    private LinearLayout system_second_no_data;

    private List<SystemClassifyBean.Children> mChildrenList;
    private SystemClassifyBean.Children mChildren;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDetailDialog = new Dialog(getActivity(), R.style.dialog);
        mDetailDialog.setContentView(R.layout.dialog_look_c2c);
        mDetailDialog.setCancelable(true);

        tv_system_title = (TextView) mDetailDialog.findViewById(R.id.tv_system_title);
        ll_system_cancel = (LinearLayout) mDetailDialog.findViewById(R.id.ll_system_cancel);
        system_second_recycle = mDetailDialog.findViewById(R.id.system_second_recycle);
        system_second_no_data = mDetailDialog.findViewById(R.id.system_second_no_data);
        system_second_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mChildrenList = (List<SystemClassifyBean.Children>) getArguments().getSerializable("mChildrenList");


        /**
         * 二级分类recycleView
         */
        if (mChildrenList != null && mChildrenList.size() != 0) {
            system_second_no_data.setVisibility(View.GONE);
            system_second_recycle.setVisibility(View.VISIBLE);
            if (mSystemSecondAdapter == null) {
                mSystemSecondAdapter = new SystemSecondAdapter(getActivity(), mChildrenList);
                system_second_recycle.setAdapter(mSystemSecondAdapter);
                mSystemSecondAdapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object o) {
                        mChildren= (SystemClassifyBean.Children) o;
                        if (onTipsListener!=null){
                            onTipsListener.onSuccess(mChildren.getName(),mChildren.getId());
                        }
                        mDetailDialog.dismiss();
                    }
                });
            } else {
                mSystemSecondAdapter.setData(mChildrenList);
                mSystemSecondAdapter.notifyDataSetChanged();
            }
        } else {
            system_second_no_data.setVisibility(View.VISIBLE);
            system_second_recycle.setVisibility(View.GONE);
        }
        tv_system_title.setText(getArguments().getString("tipsTitle"));
        ll_system_cancel.setOnClickListener(this);

        return mDetailDialog;
    }

    private OnTipsListener onTipsListener;

    public void setOnConfirmListener(OnTipsListener onTipsListener) {
        this.onTipsListener = onTipsListener;
    }

    public interface OnTipsListener {
        void onCancel();

        void onSuccess(String name,int id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_system_cancel:
                mDetailDialog.dismiss();
                if (onTipsListener != null) {
                    onTipsListener.onCancel();
                }
                break;

            default:

                break;
        }
    }
}

