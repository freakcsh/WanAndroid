package com.example.a74099.wanandroid.model.classify.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.MBaseAdapter;
import com.example.a74099.wanandroid.bean.ClassifyBean;

import java.util.List;

/**
 * Created by 74099 on 2018/8/22.
 */

public class ClassifyDetailAdapter extends MBaseAdapter<ClassifyDetailAdapter.ClassifyDetailViewHolder> {
    private List<ClassifyBean.Datas> mList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public ClassifyDetailAdapter(List<ClassifyBean.Datas> list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public ClassifyDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClassifyDetailViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_classify_detail, parent, false));
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void doCollage(ClassifyBean.Datas mData, ImageView imageView);

        void doIntern(ClassifyBean.Datas mData);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassifyDetailViewHolder holder, int position) {
        holder.bindData(position, mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setData(List<ClassifyBean.Datas> data) {
        mList = data;
    }

    class ClassifyDetailViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_classify_bg, img_classify_item_img;
        private TextView tv_classify_title, tv_classify_context, tv_classify_time, tv_classify_author;
        private LinearLayout ll_classify_item;

        public ClassifyDetailViewHolder(View itemView) {
            super(itemView);
            img_classify_bg = itemView.findViewById(R.id.img_classify_bg);
            img_classify_item_img = itemView.findViewById(R.id.img_classify_item_img);
            tv_classify_title = itemView.findViewById(R.id.tv_classify_title);
            tv_classify_context = itemView.findViewById(R.id.tv_classify_context);
            tv_classify_time = itemView.findViewById(R.id.tv_classify_time);
            tv_classify_author = itemView.findViewById(R.id.tv_classify_author);
            ll_classify_item = itemView.findViewById(R.id.ll_classify_item);
        }

        public void bindData(int position, final ClassifyBean.Datas classifyBean) {
            Glide.with(mContext).load(classifyBean.getEnvelopePic()).into(img_classify_bg);
            tv_classify_title.setText(classifyBean.getTitle());
            tv_classify_context.setText(Html.fromHtml(classifyBean.getDesc()));
            tv_classify_time.setText(classifyBean.getNiceDate());
            tv_classify_author.setText(classifyBean.getAuthor());
            img_classify_item_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.doCollage(classifyBean, img_classify_item_img);
                    }
                }
            });
            ll_classify_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.doIntern(classifyBean);
                    }
                }
            });
        }
    }
}
