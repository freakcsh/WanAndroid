package com.example.a74099.wanandroid.model.system.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.MBaseAdapter;
import com.example.a74099.wanandroid.bean.SystemClassifyBean;

import java.util.List;

/**
 * Created by 74099 on 2018/7/17.
 */

public class SystemSecondAdapter extends MBaseAdapter<SystemSecondAdapter.SystemSecondViewHolder> {
    private Context mContext;
    private List<SystemClassifyBean.Children> mList;

    public SystemSecondAdapter(Context context, List<SystemClassifyBean.Children> list) {
        mContext = context;
        mList = list;
    }

    public void setData(List<SystemClassifyBean.Children> list) {
        mList = list;
    }

    @NonNull
    @Override
    public SystemSecondViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SystemSecondViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_system_second, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SystemSecondViewHolder holder, final int position) {
        holder.tv_system_classify.setText(mList.get(position).getName());
        holder.tv_system_classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick!=null){
                    onItemClick.onItemClick(position,mList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class SystemSecondViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_system_classify;

        public SystemSecondViewHolder(View itemView) {
            super(itemView);
            tv_system_classify = itemView.findViewById(R.id.tv_system_classify);
        }
    }
}
