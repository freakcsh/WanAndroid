package com.example.a74099.wanandroid.net.util;

/**
 * 网络状态变化观察者
 */
public interface NetStateChangeObserver {
    /**
     * 网络断开
     */
    void onNetDisconnected();

    /**
     * 网络连接
     *
     * @param networkType 网络类型
     */
    void onNetConnected(NetworkType networkType);
}
