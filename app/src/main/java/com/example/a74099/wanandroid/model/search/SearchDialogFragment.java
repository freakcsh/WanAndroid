package com.example.a74099.wanandroid.model.search;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseDialogFragment;
import com.example.a74099.wanandroid.base.MBaseAdapter;
import com.example.a74099.wanandroid.bean.HistoryData;
import com.example.a74099.wanandroid.bean.TopSearchData;
import com.example.a74099.wanandroid.model.search.adapter.HistorySearchAdapter;
import com.example.a74099.wanandroid.util.ToolUtils;
import com.example.a74099.wanandroid.view.CircularRevealAnim;
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

public class SearchDialogFragment extends BaseDialogFragment<SearchPresenter> implements SearchContract.VIew, CircularRevealAnim.AnimListener, ViewTreeObserver.OnPreDrawListener, View.OnClickListener {

    ImageButton mBackIb;
    TextView mTintTv;
    EditText mSearchEdit;
    TextView mSearchTv;
    TextView mClearAllHistoryTv;
    NestedScrollView mSearchScrollView;
    TextView mHistoryNullTintTv;
    RecyclerView mRecyclerView;
    TagFlowLayout mTopSearchFlowLayout;

    private List<TopSearchData> mTopSearchDataList;
    private CircularRevealAnim mCircularRevealAnim;
    private HistorySearchAdapter historySearchAdapter;

    @Override
    protected void initEventAndData(View view) {
        mBackIb = view.findViewById(R.id.search_back_ib);
        mTintTv = view.findViewById(R.id.search_tint_tv);
        mSearchEdit = view.findViewById(R.id.search_edit);
        mSearchTv = view.findViewById(R.id.search_tv);
        mClearAllHistoryTv = view.findViewById(R.id.search_history_clear_all_tv);
        mSearchScrollView = view.findViewById(R.id.search_scroll_view);
        mHistoryNullTintTv = view.findViewById(R.id.search_history_null_tint_tv);
        mRecyclerView = view.findViewById(R.id.search_history_rv);
        mTopSearchFlowLayout = view.findViewById(R.id.top_search_flow_layout);

        mBackIb.setOnClickListener(this);
        mClearAllHistoryTv.setOnClickListener(this);
        mSearchTv.setOnClickListener(this);
        mPresenter.getTopSearchData();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle);

    }

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }


    @Override
    protected void initEventAndData() {
        initCircleAnimation();
        initRecyclerView();
        mTopSearchDataList = new ArrayList<>();
        mSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(mSearchEdit.getText().toString())) {
                    mTintTv.setText(R.string.search_tint);
                } else {
                    mTintTv.setText("");
                }
            }
        });

    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {

    }

    /**
     * 更新搜索之后的搜索历史
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
//        historySearchAdapter.replaceData(historyDataList);
    }

    /**
     * 搜索热词
     *
     * @param topSearchDataList List<TopSearchData>
     */
    @Override
    public void showTopSearchData(List<TopSearchData> topSearchDataList) {
        mTopSearchDataList = topSearchDataList;
        mTopSearchFlowLayout.setAdapter(new TagAdapter<TopSearchData>(mTopSearchDataList) {
            @Override
            public View getView(FlowLayout parent, int position, TopSearchData topSearchData) {
                assert getActivity() != null;
                TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.flow_layout_tv,
                        parent, false);
                assert topSearchData != null;
                String name = topSearchData.getName();
                tv.setText(name);
                setItemBackground(tv);
                mTopSearchFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
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
    public void judgeToTheSearchListActivity() {
        backEvent();
//        JudgeUtils.startSearchListActivity(getActivity(), mSearchEdit.getText().toString().trim());
    }

    @Override
    public void onHideAnimationEnd() {
        mSearchEdit.setText("");
        dismissAllowingStateLoss();
    }


    @Override
    public void onShowAnimationEnd() {
//        KeyBoardUtils.openKeyboard(getActivity(), mSearchEdit);
    }

    @Override
    public boolean onPreDraw() {
        mSearchEdit.getViewTreeObserver().removeOnPreDrawListener(this);
        mCircularRevealAnim.show(mSearchEdit, mView);
        return true;
    }


    private void clearHistoryData() {
//        mPresenter.clearHistoryData();
//        historySearchAdapter.replaceData(new ArrayList<>());
//        setHistoryTvStatus(true);
    }

    /***
     * 搜索
     * @param position1
     */
    private void showTopSearchView(int position1) {
//        mPresenter.addHistoryData(mTopSearchDataList.get(position1).getName().trim());
        setHistoryTvStatus(false);
        mSearchEdit.setText(mTopSearchDataList.get(position1).getName().trim());
        mSearchEdit.setSelection(mSearchEdit.getText().length());
    }

    /**
     * 初始化搜索历史recycleView
     */
    private void initRecyclerView() {
        historySearchAdapter = new HistorySearchAdapter(getActivity(), null);
        historySearchAdapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o) {
                /**
                 * 搜索历史点击监听
                 */
//                searchHistoryData(adapter, position);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(historySearchAdapter);
    }

    private void searchHistoryData(HistorySearchAdapter adapter, int position) {
//        HistoryData historyData = (HistoryData) adapter.getData().get(position);
//        mPresenter.addHistoryData(historyData.getData());
//        mSearchEdit.setText(historyData.getData());
//        mSearchEdit.setSelection(mSearchEdit.getText().length());
//        setHistoryTvStatus(false);
    }

    private void setItemBackground(TextView tv) {
        tv.setBackgroundColor(ToolUtils.randomTagColor());
        tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.c_ffffff));
    }

    private void initCircleAnimation() {
        mCircularRevealAnim = new CircularRevealAnim();
        mCircularRevealAnim.setAnimListener(this);
        mSearchEdit.getViewTreeObserver().addOnPreDrawListener(this);
    }

    private void initDialog() {
        Window window = getDialog().getWindow();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        //DialogSearch的宽
        int width = (int) (metrics.widthPixels * 0.98);
        assert window != null;
        window.setLayout(width, WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.TOP);
        //取消过渡动画 , 使DialogSearch的出现更加平滑
        window.setWindowAnimations(R.style.DialogEmptyAnimation);
    }

    public void backEvent() {
//        KeyBoardUtils.closeKeyboard(getActivity(), mSearchEdit);
//        mCircularRevealAnim.hide(mSearchEdit, mView);
    }

    /**
     * 改变搜索历史显示状态
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
        mClearAllHistoryTv.setTextColor(ContextCompat.getColor(getActivity(), textColor));
        drawable = ContextCompat.getDrawable(getActivity(), clearDrawable);
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
            case R.id.search_back_ib:
                backEvent();
                break;
            case R.id.search_history_clear_all_tv:
                clearHistoryData();
                break;
            //点击搜索
            case R.id.search_tv:
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
