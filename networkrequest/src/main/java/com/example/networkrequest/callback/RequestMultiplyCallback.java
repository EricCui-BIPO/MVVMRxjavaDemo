package com.example.networkrequest.callback;


import com.example.networkrequest.exception.BaseException;

/**
 * 失败回调
 */
public interface RequestMultiplyCallback<T> extends RequestCallback<T> {

    void onFail(BaseException e);

}