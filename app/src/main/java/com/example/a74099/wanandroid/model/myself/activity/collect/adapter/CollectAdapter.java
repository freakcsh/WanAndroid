package com.example.a74099.wanandroid.model.myself.activity.collect.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.MBaseAdapter;
import com.example.a74099.wanandroid.bean.CollectBean;
import com.example.a74099.wanandroid.util.ToastUtil;
import com.example.a74099.wanandroid.util.ToolUtils;

import java.util.List;

public class CollectAdapter extends MBaseAdapter<CollectAdapter.CollectViewHolder> {
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
        void doCancelCollage(CollectBean.Datas mData, ImageView imageView);

        void doIntern(CollectBean.Datas mData);
    }

    @NonNull
    @Override
    public CollectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CollectViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_collect_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CollectViewHolder holder, final int position) {
        holder.bindData(position, mList.get(position));
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class CollectViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_article_author, tv_article_date, tv_article_title, tv_article_chapterName;
        private LinearLayout ll_collect, ll_item;
        private ImageView iv_article_thumbnail, iv_like;
        private RelativeLayout rl_collect_item;

        public CollectViewHolder(View itemView) {
            super(itemView);
            tv_article_author = itemView.findViewById(R.id.tv_article_author);
            tv_article_date = itemView.findViewById(R.id.tv_article_date);
            iv_article_thumbnail = itemView.findViewById(R.id.iv_article_thumbnail);
            tv_article_title = itemView.findViewById(R.id.tv_article_title);
            tv_article_chapterName = itemView.findViewById(R.id.tv_article_chapterName);
            iv_like = itemView.findViewById(R.id.iv_like);
            ll_collect = itemView.findViewById(R.id.ll_collect);
            rl_collect_item = itemView.findViewById(R.id.rl_collect_item);

        }

        public void bindData(int position, final CollectBean.Datas datas) {
            if (ToolUtils.isNull(datas.getEnvelopePic())) {
                iv_article_thumbnail.setVisibility(View.GONE);
            } else {
                iv_article_thumbnail.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(datas.getEnvelopePic()).into(iv_article_thumbnail);
            }
            tv_article_author.setText(datas.getAuthor());
            tv_article_date.setText(datas.getNiceDate());
            tv_article_title.setText(datas.getTitle());
            tv_article_chapterName.setText(datas.getChapterName());
            iv_like.setSelected(true);
            ll_collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ToolUtils.isLogin(mContext)){
                        if (mOnItemClickListener!=null){
                            mOnItemClickListener.doCancelCollage(datas,iv_like);
                        }
                    }else {
                        ToastUtil.showShort(mContext, "请先登录");
                    }

                }
            });
            rl_collect_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener!=null){
                        mOnItemClickListener.doIntern(datas);
                    }
                }
            });
        }
    }
}
