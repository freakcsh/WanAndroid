package com.example.a74099.wanandroid.event;

/**
 * Created by 74099 on 2018/8/28.
 */

public class NetworkChangeEvent {
    public boolean isConnected; //是否存在网络

    public NetworkChangeEvent(boolean isConnected) {
        this.isConnected = isConnected;
    }
}
