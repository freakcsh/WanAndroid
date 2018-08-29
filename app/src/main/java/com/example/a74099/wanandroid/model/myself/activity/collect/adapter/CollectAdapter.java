package com.example.a74099.wanandroid.model.myself.activity.collect.adapter;

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
import com.example.a74099.wanandroid.bean.CollectBean;

import java.util.List;

public class CollectAdapter extends MBaseAdapter<CollectAdapter.HomePageViewHolder> {
    private Context mContext;
    private List<CollectBean.Datas> mList;

    private OnItemClickListener mOnItemClickListener;

    public CollectAdapter(Context mContext, List<CollectBean.Datas> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setData(List<CollectBean.Datas> mList) {
        this.mList = mList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void doCollage(CollectBean.Datas mData, ImageView imageView);

        void doIntern(CollectBean.Datas mData);
    }

    @NonNull
    @Override
    public HomePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomePageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_homepage, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final HomePageViewHolder holder, final int position) {
        holder.home_item_title.setText(mList.get(position).getTitle());
        holder.home_item_author.setText(mList.get(position).getAuthor());
//        holder.home_item_classify.setText(mList.get(position).getSuperChapterName());
        holder.home_item_time.setText(mList.get(position).getNiceDate());
        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.doIntern(mList.get(position));
                }
            }
        });
        holder.home_item_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.doCollage(mList.get(position),holder.home_item_img);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class HomePageViewHolder extends RecyclerView.ViewHolder {
        private TextView home_item_time, home_item_classify, home_item_author, home_item_title;
        private LinearLayout home_item_collect, ll_item;
        private ImageView home_item_img;


        public HomePageViewHolder(View itemView) {
            super(itemView);
            home_item_collect = itemView.findViewById(R.id.home_item_collect);
            home_item_title = itemView.findViewById(R.id.home_item_title);
            home_item_author = itemView.findViewById(R.id.home_item_author);
            home_item_classify = itemView.findViewById(R.id.home_item_classify);
            home_item_time = itemView.findViewById(R.id.home_item_time);
            ll_item = itemView.findViewById(R.id.ll_item);
            home_item_img = itemView.findViewById(R.id.home_item_img);
        }
    }
}