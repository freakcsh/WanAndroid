package com.example.a74099.wanandroid.model.system.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.a74099.wanandroid.base.MBaseAdapter;

public class SystemDetailAdapter extends MBaseAdapter<SystemDetailAdapter.SystemDetailViewHolder>{
    @NonNull
    @Override
    public SystemDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SystemDetailViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class SystemDetailViewHolder extends RecyclerView.ViewHolder{

        public SystemDetailViewHolder(View itemView) {
            super(itemView);
        }
    }
}
