package com.example.a74099.wanandroid.model.system.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.MBaseAdapter;
import com.example.a74099.wanandroid.bean.SystemDetailBean;

import java.util.List;

public class SystemDetailAdapter extends MBaseAdapter<SystemDetailAdapter.SystemDetailViewHolder> {
    private Context mContext;
    private List<SystemDetailBean.Datas> list;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SystemDetailAdapter(Context mContext, List<SystemDetailBean.Datas> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public void setData(List<SystemDetailBean.Datas> list) {
        this.list = list;
    }
    public interface OnItemClickListener {
        void doCollage(SystemDetailBean.Datas mData, ImageView imageView);

        void doIntern(SystemDetailBean.Datas mData);
    }
    @NonNull
    @Override
    public SystemDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SystemDetailViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_system_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final SystemDetailViewHolder holder, final int position) {
        holder.system_item_title.setText(list.get(position).getTitle());
        holder.system_item_author.setText(list.get(position).getAuthor());
        holder.system_item_time.setText(list.get(position).getNiceDate());

        holder.ll_system_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.doIntern(list.get(position));
                }
            }
        });
        holder.system_item_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.doCollage(list.get(position),holder.system_item_img);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class SystemDetailViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout system_item_collect,ll_system_item;
        private ImageView system_item_img;
        private TextView system_item_title, system_item_author, system_item_time;


        public SystemDetailViewHolder(View itemView) {
            super(itemView);
            system_item_collect = itemView.findViewById(R.id.system_item_collect);
            system_item_img = itemView.findViewById(R.id.system_item_img);
            system_item_title = itemView.findViewById(R.id.system_item_title);
            system_item_author = itemView.findViewById(R.id.system_item_author);
            system_item_time = itemView.findViewById(R.id.system_item_time);
            ll_system_item = itemView.findViewById(R.id.ll_system_item);
        }
    }
}
