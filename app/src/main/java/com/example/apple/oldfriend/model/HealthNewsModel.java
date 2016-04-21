package com.example.apple.oldfriend.model;

import android.util.Log;

import com.example.apple.oldfriend.api.API;
import com.example.apple.oldfriend.model.bean.GetNewsInfoResponse;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by gan on 2016/4/21.
 */
public class HealthNewsModel {
    public void getData(int id, int classify, int rows, final IGetHealthNews callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        IApiService apiService = retrofit.create(IApiService.class);
        apiService.getNewsInfoResponse(id, classify, rows).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe(new Observer<GetNewsInfoResponse>() {
            @Override
            public void onCompleted() {
                Log.d("TAG", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                callback.onHealthNewsError(e);

            }

            @Override
            public void onNext(GetNewsInfoResponse getNewsInfoResponse) {
                callback.onHealthNewsSuccess(getNewsInfoResponse.getTngou());
            }
        });
    }
}
