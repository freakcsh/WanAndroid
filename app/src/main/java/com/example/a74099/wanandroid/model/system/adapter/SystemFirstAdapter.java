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
 * 一级分类adapter
 * Created by 74099 on 2018/7/17.
 */

public class SystemFirstAdapter extends MBaseAdapter<SystemFirstAdapter.SystemFirstViewHolder> {
    private Context mContext;
    private List<SystemClassifyBean> mList;

    public SystemFirstAdapter(Context context, List<SystemClassifyBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setSelect(int position) {
        for (int i = 0; i < mList.size(); i++) {
            if (i == position) {
                mList.get(i).setSelect(true);
            } else {
                mList.get(i).setSelect(false);
            }
        }
    }

    public void setData(List<SystemClassifyBean> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public SystemFirstViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SystemFirstViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_system, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final SystemFirstViewHolder holder, final int position) {
        holder.tv_system_classify.setText(mList.get(position).getName());

        holder.tv_system_classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(position, mList.get(position).getChildren());


                }
            }
        });
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(position).isSelect()) {

                holder.tv_system_classify.setSelected(true);
                mList.get(position).setSelect(true);
            }else {
                holder.tv_system_classify.setSelected(false);
                mList.get(position).setSelect(false);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class SystemFirstViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_system_classify;

        public SystemFirstViewHolder(View itemView) {
            super(itemView);
            tv_system_classify = itemView.findViewById(R.id.tv_system_classify);
        }
    }
}
