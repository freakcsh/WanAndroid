package com.example.a74099.wanandroid.bean;

/**
 * @author quchao
 * @date 2018/3/5
 */

public class HistoryData {


    private Long id;

    private long date;

    private String data;


    public HistoryData(Long id, long date, String data) {
        this.id = id;
        this.date = date;
        this.data = data;
    }


    public HistoryData() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
