package com.example.apple.oldfriend.model.bean;

import java.util.List;

/**
 * Created by gan on 2016/4/21.
 */
public class GetNewsInfoResponse {
    private boolean status;

    private int total;

    private List<NewsInfo> tngou;

    public boolean isStatus() {
        return status;
    }

    public int getTotal() {
        return total;
    }

    public List<NewsInfo> getTngou() {
        return tngou;
    }
}
