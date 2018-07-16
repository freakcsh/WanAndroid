package com.example.a74099.wanandroid.model.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.bean.ArticleListBean;

import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.HomePageViewHolder> {
    private Context mContext;
    private List<ArticleListBean.Datas> mList;

    public HomePageAdapter(Context mContext, List<ArticleListBean.Datas> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setData(List<ArticleListBean.Datas> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public HomePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomePageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_homepage, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageViewHolder holder, int position) {
        holder.home_item_title.setText(mList.get(position).getTitle());
        holder.home_item_author.setText(mList.get(position).getAuthor());
        holder.home_item_classify.setText(mList.get(position).getSuperChapterName());
        holder.home_item_time.setText(mList.get(position).getNiceDate());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class HomePageViewHolder extends RecyclerView.ViewHolder {
        private TextView home_item_time, home_item_classify, home_item_author, home_item_title;
        private LinearLayout home_item_collect;


        public HomePageViewHolder(View itemView) {
            super(itemView);
            home_item_collect = itemView.findViewById(R.id.home_item_collect);
            home_item_title = itemView.findViewById(R.id.home_item_title);
            home_item_author = itemView.findViewById(R.id.home_item_author);
            home_item_classify = itemView.findViewById(R.id.home_item_classify);
            home_item_time = itemView.findViewById(R.id.home_item_time);
        }
    }
}
