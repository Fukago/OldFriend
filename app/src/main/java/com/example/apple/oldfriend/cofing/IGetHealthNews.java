package com.example.apple.oldfriend.cofing;

import com.example.apple.oldfriend.model.bean.NewsInfo;

import java.util.List;

/**
 * Created by gan on 2016/4/21.
 */
public interface IGetHealthNews {
    void onHealthNewsSuccess(List<NewsInfo> tngou);
    void onHealthNewsError(Throwable e);
}
