package com.example.livedatademo.repository;


import com.example.livedatademo.api.MainApiService;
import com.example.mvvmlibrary.base.BaseRepository;
import com.example.mvvmlibrary.base.BaseViewModel;
import com.example.networkrequest.callback.RequestCallback;

public class MainRepository extends BaseRepository {

    public MainRepository(BaseViewModel baseViewModel) {
        super(baseViewModel);
    }

    /**
     * 请求接口
     */
    public void requestData(String mobile, RequestCallback<Boolean> callback) {
        execute(getApi(MainApiService.class).getUserMobileCheck("",mobile),callback);
    }


}

