package com.example.livedatademo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.livedatademo.repository.MainRepository;
import com.example.mvvmlibrary.base.BaseViewModel;
import com.example.networkrequest.callback.RequestMultiplyCallback;
import com.example.networkrequest.exception.BaseException;


public class MainViewModel extends BaseViewModel<MainRepository> {

    private MutableLiveData<Boolean> booleanLiveData = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected MainRepository getRepository() {
        return new MainRepository(this);
    }




    //接口的请求数据以及回调
    public void getUserInfo(String key) {
        mRepository.requestData(key,new RequestMultiplyCallback<Boolean>() {

            @Override
            public void onFail(BaseException e) {

            }

            @Override
            public void onSuccess(Boolean bean) {
                booleanLiveData.setValue(bean);
            }
        });
    }


    public MutableLiveData<Boolean> getBooleanMutableLiveData() {
        return booleanLiveData;
    }
}
