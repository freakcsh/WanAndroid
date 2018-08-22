package com.example.a74099.wanandroid.model.classify.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.MBaseAdapter;
import com.example.a74099.wanandroid.bean.ClassifyBean;

import java.util.List;

/**
 * Created by 74099 on 2018/8/22.
 */

public class ClassifyDetailAdapter extends MBaseAdapter<ClassifyDetailAdapter.ClassifyDetailViewHolder> {
    private List<ClassifyBean.Datas> mList;
    private Context mContext;

    public ClassifyDetailAdapter(List<ClassifyBean.Datas> list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public ClassifyDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClassifyDetailViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_classify_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ClassifyDetailViewHolder holder, int position) {
        holder.bindData(position, mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setData(List<ClassifyBean.Datas> data) {
        mList = data;
    }

    class ClassifyDetailViewHolder extends RecyclerView.ViewHolder {

        public ClassifyDetailViewHolder(View itemView) {
            super(itemView);
        }

        public void bindData(int position, ClassifyBean.Datas classifyBean) {

        }
    }
}
