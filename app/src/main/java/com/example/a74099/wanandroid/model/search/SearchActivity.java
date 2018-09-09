package com.example.a74099.wanandroid.model.search;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseActivity;
import com.example.a74099.wanandroid.base.MBaseAdapter;
import com.example.a74099.wanandroid.bean.HistoryData;
import com.example.a74099.wanandroid.bean.TopSearchData;
import com.example.a74099.wanandroid.model.search.adapter.HistorySearchAdapter;
import com.example.a74099.wanandroid.model.search.detail.SearchDetailActivity;
import com.example.a74099.wanandroid.net.util.NetworkType;
import com.example.a74099.wanandroid.util.ToolUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author quchao
 * @date 2018/3/1
 */

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.VIew, ViewTreeObserver.OnPreDrawListener, View.OnClickListener {

    ImageButton mBackIb;
    TextView mTintTv;
    EditText mSearchEdit;
    TextView mSearchTv;
    TextView mClearAllHistoryTv;
    TextView mHistoryNullTintTv;
    RecyclerView mRecyclerView;
    TagFlowLayout mTopSearchFlowLayout;


    private List<TopSearchData> mTopSearchDataList;
    private HistorySearchAdapter historySearchAdapter;

    public static void startAction(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }
    /***
     * 断网重连
     * @param networkType
     */
    @Override
    public void onNetConnected(NetworkType networkType) {
        super.onNetConnected(networkType);
        mPresenter.getTopSearchData();
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initEventAndData() {
        setBackPress();
        mTintTv = findViewById(R.id.search_tint_tv);
        mSearchEdit = findViewById(R.id.search_edit);
        mSearchTv = findViewById(R.id.search_tv);
        mClearAllHistoryTv = findViewById(R.id.search_history_clear_all_tv);
        mHistoryNullTintTv = findViewById(R.id.search_history_null_tint_tv);
        mRecyclerView = findViewById(R.id.search_history_rv);
        mTopSearchFlowLayout = findViewById(R.id.top_search_flow_layout);

        mClearAllHistoryTv.setOnClickListener(this);
        mSearchTv.setOnClickListener(this);
        mPresenter.getTopSearchData();

        initRecyclerView();
        mTopSearchDataList = new ArrayList<>();

    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }


    /**
     * 更新搜索之后的搜索历史
     *
     * @param historyDataList List<HistoryData>
     */
    @Override
    public void showHistoryData(List<HistoryData> historyDataList) {
        if (historyDataList == null || historyDataList.size() <= 0) {
            setHistoryTvStatus(true);
            return;
        }
        setHistoryTvStatus(false);
        Collections.reverse(historyDataList);
        historySearchAdapter.setdata(historyDataList);
        historySearchAdapter.notifyDataSetChanged();
    }

    /**
     * 搜索热词显示
     *
     * @param topSearchDataList List<TopSearchData>
     */
    @Override
    public void showTopSearchData(List<TopSearchData> topSearchDataList) {
        mTopSearchDataList = topSearchDataList;
        mTopSearchFlowLayout.setAdapter(new TagAdapter<TopSearchData>(mTopSearchDataList) {
            @Override
            public View getView(FlowLayout parent, int position, TopSearchData topSearchData) {
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.flow_layout_tv,
                        parent, false);
                assert topSearchData != null;
                String name = topSearchData.getName();
                tv.setText(name);
                setItemBackground(tv);
                mTopSearchFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        //搜索热词点击监听
                        showTopSearchView(position);
                        return true;
                    }
                });
                return tv;
            }
        });
    }

    /**
     * 跳转到搜索详情页面
     */
    @Override
    public void judgeToTheSearchListActivity(String data) {
        SearchDetailActivity.startAction(this, data);
        finish();
    }


    @Override
    public boolean onPreDraw() {
        mSearchEdit.getViewTreeObserver().removeOnPreDrawListener(this);
//        mCircularRevealAnim.show(mSearchEdit, SearchDialogFragment.this);
        return true;
    }

    /***
     * 清空搜索历史
     */
    private void clearHistoryData() {
        mPresenter.clearHistoryData();
        historySearchAdapter.setdata(mPresenter.loadAllHistoryData());
        setHistoryTvStatus(true);
    }

    /***
     * 搜索热词搜索
     * @param position1
     */
    private void showTopSearchView(int position1) {
        mSearchEdit.setText(mTopSearchDataList.get(position1).getName().trim());
        mSearchEdit.setSelection(mSearchEdit.getText().length());
        mPresenter.addHistoryData(mTopSearchDataList.get(position1).getName().trim());
        setHistoryTvStatus(false);

    }

    /**
     * 初始化搜索历史recycleView
     */
    private void initRecyclerView() {
        historySearchAdapter = new HistorySearchAdapter(SearchActivity.this, mPresenter.loadAllHistoryData());
        historySearchAdapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o) {
                HistoryData historyData= (HistoryData) o;
                /**
                 * 搜索历史点击监听
                 */
                searchHistoryData(historyData, position);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        mRecyclerView.setAdapter(historySearchAdapter);
    }

    private void searchHistoryData(HistoryData historyData, int position) {
        mPresenter.addHistoryData(historyData.getData());
        mSearchEdit.setText(historyData.getData());
        mSearchEdit.setSelection(mSearchEdit.getText().length());
        setHistoryTvStatus(false);
    }

    private void setItemBackground(TextView tv) {
        tv.setBackgroundColor(ToolUtils.randomTagColor());
        tv.setTextColor(ContextCompat.getColor(SearchActivity.this, R.color.c_ffffff));
    }


    /**
     * 改变搜索历史显示状态
     *
     * @param isClearAll
     */
    private void setHistoryTvStatus(boolean isClearAll) {
        mClearAllHistoryTv.setEnabled(!isClearAll);
        if (isClearAll) {
            setHistoryTvStatus(View.VISIBLE, R.color.search_grey_gone, R.drawable.ic_clear_all_gone);
        } else {
            setHistoryTvStatus(View.GONE, R.color.search_grey, R.drawable.ic_clear_all);
        }
    }

    private void setHistoryTvStatus(int visibility, @ColorRes int textColor, @DrawableRes int clearDrawable) {
        Drawable drawable;
        mHistoryNullTintTv.setVisibility(visibility);
        mClearAllHistoryTv.setTextColor(ContextCompat.getColor(SearchActivity.this, textColor));
        drawable = ContextCompat.getDrawable(SearchActivity.this, clearDrawable);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mClearAllHistoryTv.setCompoundDrawables(drawable, null, null, null);
        mClearAllHistoryTv.setCompoundDrawablePadding(6);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_history_clear_all_tv:
                clearHistoryData();
                break;
            //点击搜索
            case R.id.search_tv:
                //保存输入搜索词
                mPresenter.addHistoryData(mSearchEdit.getText().toString().trim());
                setHistoryTvStatus(false);

                showHistoryData(mPresenter.loadAllHistoryData());
                mPresenter.getTopSearchData();
                break;

            default:
                break;
        }
    }
}
