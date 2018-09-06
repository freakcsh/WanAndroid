package com.example.a74099.wanandroid.model.search.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.MBaseAdapter;
import com.example.a74099.wanandroid.bean.SearchDetailBean;

import java.util.List;

/**
 * Created by 74099 on 2018/9/6.
 */

public class SearchDetailAdapter extends MBaseAdapter<SearchDetailAdapter.SearchDetailViewHolder> {
    private Context mContext;
    private List<SearchDetailBean.Datas> mList;
    private OnItemClickListener mOnItemClickListener;

    public SearchDetailAdapter(Context context, List<SearchDetailBean.Datas> list) {
        mContext = context;
        mList = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public SearchDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchDetailViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_search_pager, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchDetailViewHolder holder, int position) {
        holder.bingData(mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setData(List<SearchDetailBean.Datas> data) {
        mList = data;
    }

    public interface OnItemClickListener {
        void doCollage(SearchDetailBean.Datas mData, ImageView imageView);

        void doIntern(SearchDetailBean.Datas mData);
    }

    class SearchDetailViewHolder extends RecyclerView.ViewHolder {
        private TextView item_search_pager_author, item_search_pager_chapterName, item_search_pager_title, item_search_pager_tag_red_tv, item_search_pager_tag_green_tv, item_search_pager_niceDate;
        private ImageView item_search_pager_like_iv;
        private LinearLayout item_search_tag_group;
        private CardView item_search_pager_group;


        public SearchDetailViewHolder(View itemView) {
            super(itemView);
            item_search_pager_author = itemView.findViewById(R.id.item_search_pager_author);
            item_search_pager_chapterName = itemView.findViewById(R.id.item_search_pager_chapterName);
            item_search_pager_title = itemView.findViewById(R.id.item_search_pager_title);
            item_search_pager_tag_red_tv = itemView.findViewById(R.id.item_search_pager_tag_red_tv);
            item_search_pager_tag_green_tv = itemView.findViewById(R.id.item_search_pager_tag_green_tv);
            item_search_pager_niceDate = itemView.findViewById(R.id.item_search_pager_niceDate);
            item_search_pager_like_iv = itemView.findViewById(R.id.item_search_pager_like_iv);
            item_search_tag_group = itemView.findViewById(R.id.item_search_tag_group);
            item_search_pager_group = itemView.findViewById(R.id.item_search_pager_group);
        }

        public void bingData(final SearchDetailBean.Datas searchDetailBean, int position) {
            if (!TextUtils.isEmpty(searchDetailBean.getTitle())) {
                item_search_pager_title.setText(Html.fromHtml(searchDetailBean.getTitle()));
            }
            if (searchDetailBean.getCollect()) {
                item_search_pager_like_iv.setImageResource(R.drawable.icon_like);
            } else {
                item_search_pager_like_iv.setImageResource(R.drawable.icon_like_article_not_selected);
            }
            if (!TextUtils.isEmpty(searchDetailBean.getAuthor())) {
                item_search_pager_author.setText(searchDetailBean.getAuthor());
            }
            if (!TextUtils.isEmpty(searchDetailBean.getChapterName())) {
                String classifyName = searchDetailBean.getSuperChapterName() + " / " + searchDetailBean.getChapterName();
                if (searchDetailBean.getCollect()) {
                    item_search_pager_chapterName.setText(searchDetailBean.getChapterName());
                } else {
                    item_search_pager_chapterName.setText(classifyName);
                }
            }
            if (!TextUtils.isEmpty(searchDetailBean.getNiceDate())) {
                item_search_pager_niceDate.setText(searchDetailBean.getNiceDate());
            }
            if (searchDetailBean.getFresh()) {
                item_search_pager_tag_red_tv.setVisibility(View.VISIBLE);
            } else {
                item_search_pager_tag_red_tv.setVisibility(View.GONE);
            }

            item_search_pager_like_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.doCollage(searchDetailBean, item_search_pager_like_iv);
                    }
                }
            });
            item_search_pager_group.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.doIntern(searchDetailBean);
                    }
                }
            });
        }


    }
}
