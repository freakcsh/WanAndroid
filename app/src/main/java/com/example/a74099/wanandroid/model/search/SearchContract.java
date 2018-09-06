package com.example.a74099.wanandroid.model.search;

import com.example.a74099.wanandroid.bean.HistoryData;
import com.example.a74099.wanandroid.bean.TopSearchData;
import com.example.a74099.wanandroid.net.BasePresenter;
import com.example.a74099.wanandroid.net.BaseView;

import java.util.List;

/**
 * Created by 74099 on 2018/9/5.
 */

public interface SearchContract {
    interface VIew extends BaseView {
        /**
         * Show history data
         *
         * @param historyDataList List<HistoryData>
         */
        void showHistoryData(List<HistoryData> historyDataList);

        /**
         * Show top search data
         *
         * @param topSearchDataList List<TopSearchData>
         */
        void showTopSearchData(List<TopSearchData> topSearchDataList);

        /**
         * Judge to the search list activity
         */
        void judgeToTheSearchListActivity(String data);

    }


    interface Presenter extends BasePresenter<VIew> {
        /**
         * Load all history data
         *
         * @return all history data
         */
        List<HistoryData> loadAllHistoryData();

        /**
         * Add history data
         *
         * @param data history data
         */
        void addHistoryData(String data);

        /**
         * 热搜
         */
        void getTopSearchData();

        /**
         * Clear history data
         */
        void clearHistoryData();
    }
}
