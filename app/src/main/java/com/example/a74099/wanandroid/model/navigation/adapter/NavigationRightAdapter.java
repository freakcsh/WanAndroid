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
import com.example.a74099.wanandroid.bean.NavigationBean;

import java.util.List;

public class NavigationRightAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<NavigationBean> list;
    private final int MENU_TYPE = 0;
    private final int DISH_TYPE = 1;
    private int mItemCount;

    public NavigationRightAdapter(Context context, List<NavigationBean> list) {
        this.context = context;
        this.list = list;
        this.mItemCount = list.size();
        for (NavigationBean menu : list) {
            mItemCount += menu.getArticles().size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        int sum = 0;
        for (NavigationBean menu : list) {
            if (position == sum) {
                return MENU_TYPE;
            }
            sum += menu.getArticles().size() + 1;
        }
        return DISH_TYPE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MENU_TYPE) {
            return new MenuViewHolder(LayoutInflater.from(context).inflate(R.layout.right_menu_item, parent, false));
        } else {
            return new NavigationLeftViewHolder(LayoutInflater.from(context).inflate(R.layout.item_navigation_right, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == MENU_TYPE) {
            MenuViewHolder menuholder = (MenuViewHolder) holder;
            if (menuholder != null) {
                menuholder.right_menu_title.setText(getMenuByPosition(position).getName());
                menuholder.right_menu_layout.setContentDescription(position + "");
            }
        } else {
            NavigationLeftViewHolder navigationLeftViewHolder = (NavigationLeftViewHolder) holder;
            if (navigationLeftViewHolder != null) {
                final NavigationBean.Articles articles = getDishByPosition(position);
                navigationLeftViewHolder.tv_navigation_title_right.setText(articles.getTitle());
                navigationLeftViewHolder.ll_right.setContentDescription(position + "");
            }
        }
    }


    public NavigationBean getMenuByPosition(int position) {
        int sum = 0;
        for (NavigationBean menu : list) {
            if (position == sum) {
                return menu;
            }
            sum += menu.getArticles().size() + 1;
        }
        return null;
    }


    public NavigationBean.Articles getDishByPosition(int position) {
        for (NavigationBean menu : list) {
            if (position > 0 && position <= menu.getArticles().size()) {
                return menu.getArticles().get(position - 1);
            } else {
                position -= menu.getArticles().size() + 1;
            }
        }
        return null;
    }

    public NavigationBean getMenuOfMenuByPosition(int position) {
        for (NavigationBean menu : list) {
            if (position == 0) return menu;
            if (position > 0 && position <= menu.getArticles().size()) {
                return menu;
            } else {
                position -= menu.getArticles().size() + 1;
            }
        }
        return null;
    }


    public void setData(List<NavigationBean> list) {
        this.list = list;
    }

    private class MenuViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout right_menu_layout;
        private TextView right_menu_title;

        public MenuViewHolder(View itemView) {
            super(itemView);
            right_menu_layout = (LinearLayout) itemView.findViewById(R.id.right_menu_item);
            right_menu_title = (TextView) itemView.findViewById(R.id.right_menu_tv);
        }
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    private class NavigationLeftViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_navigation_title_right;
        private LinearLayout ll_right;

        public NavigationLeftViewHolder(View itemView) {
            super(itemView);
            tv_navigation_title_right = itemView.findViewById(R.id.tv_navigation_title_right);
            ll_right = itemView.findViewById(R.id.ll_right);
        }
    }
}
