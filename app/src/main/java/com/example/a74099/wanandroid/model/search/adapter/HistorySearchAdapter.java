package com.example.a74099.wanandroid.model.search.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.MBaseAdapter;
import com.example.a74099.wanandroid.bean.HistoryData;

import java.util.List;

/**
 * @author quchao
 * @date 2018/3/23
 */

public class HistorySearchAdapter extends MBaseAdapter<HistorySearchAdapter.HistorySearchViewHolder> {
    private Context mContext;
    private List<HistoryData> mList;

    public HistorySearchAdapter(Context context, List<HistoryData> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public HistorySearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistorySearchViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_search_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistorySearchViewHolder holder, int position) {
        holder.bindData(mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class HistorySearchViewHolder extends RecyclerView.ViewHolder {
        private TextView item_search_history_tv;

        public HistorySearchViewHolder(View itemView) {
            super(itemView);
            item_search_history_tv = itemView.findViewById(R.id.item_search_history_tv);

        }

        public void bindData(HistoryData historyData, int position) {
            item_search_history_tv.setText(historyData.getData());
            item_search_history_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
