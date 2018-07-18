package com.example.a74099.wanandroid.model.navigation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.MBaseAdapter;
import com.example.a74099.wanandroid.bean.NavigationBean;

import java.util.List;

public class NavigationLeftAdapter extends MBaseAdapter<NavigationLeftAdapter.NavigationLeftViewHolder> {
    private Context context;
    private List<NavigationBean> list;

    public NavigationLeftAdapter(Context context, List<NavigationBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<NavigationBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NavigationLeftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NavigationLeftViewHolder(LayoutInflater.from(context).inflate(R.layout.item_navigation_left, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationLeftViewHolder holder, int position) {
        holder.tv_navigation_title.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class NavigationLeftViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_navigation_title;

        public NavigationLeftViewHolder(View itemView) {
            super(itemView);
            tv_navigation_title = itemView.findViewById(R.id.tv_navigation_title);
        }
    }
}
