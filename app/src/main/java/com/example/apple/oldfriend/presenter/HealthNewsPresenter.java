package com.example.apple.oldfriend.presenter;

import com.example.apple.oldfriend.model.HealthNewsModel;
import com.example.apple.oldfriend.model.IGetHealthNews;

/**
 * Created by gan on 2016/4/21.
 */
public class HealthNewsPresenter {
    private HealthNewsModel model;

    public HealthNewsPresenter() {
        model = new HealthNewsModel();
    }

    /**
     * 拿到新闻列表
     * id：当前资讯最大ID，默认0
     * classify：分类ID，默认0
     * rows：条数
     */
    public void getNewsList(int id, int classify, int rows, final IGetHealthNews callback) {
        model.getData(id, classify, rows, callback);
    }



}
