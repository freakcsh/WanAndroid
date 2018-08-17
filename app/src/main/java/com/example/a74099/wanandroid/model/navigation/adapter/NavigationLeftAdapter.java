package com.example.a74099.wanandroid.model.navigation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.MBaseAdapter;
import com.example.a74099.wanandroid.bean.NavigationBean;

import java.util.ArrayList;
import java.util.List;

public class NavigationLeftAdapter extends MBaseAdapter<NavigationLeftAdapter.NavigationLeftViewHolder> {
    private Context context;
    private List<NavigationBean> list;
    private int mSelectedNum;
    private List<onItemSelectedListener> mSelectedListenerList;

    public interface onItemSelectedListener {
        public void onLeftItemSelected(int postion, NavigationBean menu);
    }

    public void addItemSelectedListener(onItemSelectedListener listener) {
        if (mSelectedListenerList != null)
            mSelectedListenerList.add(listener);
    }

    public void removeItemSelectedListener(onItemSelectedListener listener) {
        if (mSelectedListenerList != null && !mSelectedListenerList.isEmpty())
            mSelectedListenerList.remove(listener);
    }

    public NavigationLeftAdapter(Context context, List<NavigationBean> list) {
        this.context = context;
        this.list = list;
        this.mSelectedNum = -1;
        this.mSelectedListenerList = new ArrayList<>();
        if (list.size() > 0)
            mSelectedNum = 0;
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
        if (mSelectedNum == position) {
            holder.ll_left.setSelected(true);
        } else {
            holder.ll_left.setSelected(false);
        }

    }
    public void setSelectedNum(int selectedNum) {
        if (selectedNum < getItemCount() && selectedNum >= 0) {
            this.mSelectedNum = selectedNum;
            notifyDataSetChanged();
        }
    }
    private void notifyItemSelected(int position) {
        if (mSelectedListenerList != null && !mSelectedListenerList.isEmpty()) {
            for (onItemSelectedListener listener : mSelectedListenerList) {
                listener.onLeftItemSelected(position, list.get(position));
            }
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class NavigationLeftViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_navigation_title;
        private LinearLayout ll_left;

        public NavigationLeftViewHolder(View itemView) {
            super(itemView);
            tv_navigation_title = itemView.findViewById(R.id.tv_navigation_title);
            ll_left = itemView.findViewById(R.id.ll_left);
            ll_left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickPosition = getAdapterPosition();
//                    setSelectedNum(clickPosition);
                    notifyItemSelected(clickPosition);
                }
            });
        }
    }
}
