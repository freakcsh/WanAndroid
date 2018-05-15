package com.example.a74099.wanandroid.net;

import java.util.List;

/**
 * 此方法是根据接口返回的数据去定义的，抽取出返回json数据的对象进行去解析
 *
 * @param <T> result是接口数据的一个对象，bean类中的数据书写也是书写这个json数据的对象的字段即可
 */
public class HttpResult<T> {
    private int code;
    private T data;
    private String msg;
    private List<String> extend;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getExtend() {
        return extend;
    }

    public void setExtend(List<String> extend) {
        this.extend = extend;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (null != data) {
            sb.append(data.toString());
        }
        return sb.toString();
    }
}
