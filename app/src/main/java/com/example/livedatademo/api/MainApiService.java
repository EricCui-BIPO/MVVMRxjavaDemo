package com.example.livedatademo.api;

import com.example.livedatademo.model.MainBean;
import com.example.livedatademo.model.MainEntity;
import com.example.networkrequest.base.BaseResponse;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MainApiService {

    @GET("dukang-user/users/mobile_check")
    Observable<BaseResponse<Boolean>> getUserMobileCheck(
            @Query("country_code") String country_code,
            @Query("mobile_number") String mobile_number
    );

}
