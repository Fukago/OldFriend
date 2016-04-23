package com.example.apple.oldfriend.presenter;

import com.example.apple.oldfriend.model.BothMessageModel;
import com.example.apple.oldfriend.cofing.IGetBothMessage;

/**
 * Created by gan on 2016/4/22.
 */
public class BothMessagePresenter {
    private BothMessageModel model;

    public BothMessagePresenter(BothMessageModel model) {
        this.model = model;
    }


    /**
     * 老人拿到照顾自己的护士的信息
     */

    public void getNurseMessage(IGetBothMessage callback) {
        model.getNurseMessage(callback);
    }


    /**
     * 护士拿到老人的信息
     */

    public void getOldMessage(IGetBothMessage callback) {
        model.getOldMessage(callback);
    }


}
