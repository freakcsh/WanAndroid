package com.example.a74099.wanandroid.net;

public interface ApiCallback<T> {
    void onSuccess(T model);

    void onFailure(String msg);
}
