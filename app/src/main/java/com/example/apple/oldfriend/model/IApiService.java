package com.example.apple.oldfriend.model;

import com.example.apple.oldfriend.api.API;
import com.example.apple.oldfriend.model.bean.GetNewsInfoResponse;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by gan on 2016/4/21.
 */
public interface IApiService {
    @Headers("apikey:" + API.apiKey)
    @GET("tngou/info/news")
    Observable<GetNewsInfoResponse> getNewsInfoResponse(@Query("id") int id, @Query("classify") int classify,
                                                        @Query("rows") int rows);
}
