package com.example.kotlinxc.tools;

/**
 * Created by zf on 2018/2/5.
 */

public interface OnResponseListener<T> {
    void asyncResponse(T data);
}
