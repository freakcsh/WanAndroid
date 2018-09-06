package com.example.a74099.wanandroid.model.search.db;

import com.example.a74099.wanandroid.bean.HistoryData;

import org.litepal.LitePal;

import java.util.List;

/**
 * Created by 74099 on 2018/9/6.
 */

public class RealmHelper {
    public RealmHelper() {
    }

    public void saveHistory(String searchMsg) {
        HistoryData historyData = new HistoryData();
        if (!checkData(searchMsg)) {
            historyData.setData(searchMsg);
            historyData.save();
        }

    }

    public List<HistoryData> selectAllHistory() {
        List<HistoryData> historyDataList = LitePal.findAll(HistoryData.class);
        return historyDataList;
    }

    public boolean checkData(String searchMsg) {
        HistoryData historyData = LitePal.where("data = ?", searchMsg).findFirst(HistoryData.class);
        if (historyData != null) {
            return true;
        } else {
            return false;
        }
    }

    public void clearHistory() {
        LitePal.deleteAll(HistoryData.class);
    }
}
