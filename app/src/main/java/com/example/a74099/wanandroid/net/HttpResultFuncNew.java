package com.example.a74099.wanandroid.net;

import rx.functions.Func1;

/**
 * 此方法是接口返回数据的解析
 * @param <T>
 */
public class HttpResultFuncNew<T> implements Func1<HttpResultNew<T>, T> {
    @Override
    public T call(HttpResultNew<T> tHttpResult) {
        if (tHttpResult.getError_code() != 0) {
            throw new ApiException(tHttpResult.getReason());
        }
        return tHttpResult.getResult();
    }
}
