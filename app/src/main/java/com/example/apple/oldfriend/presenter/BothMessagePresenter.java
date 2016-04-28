package com.example.apple.oldfriend.presenter;

import android.content.Context;

import com.example.apple.oldfriend.model.BothMessageModel;
import com.example.apple.oldfriend.cofing.IGetBothMessage;

/**
 * Created by gan on 2016/4/22.
 *
 * -------------------------已测试------------------------
 */
public class BothMessagePresenter {
    private BothMessageModel model;

    public BothMessagePresenter(Context context) {
        model = new BothMessageModel(context);
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


    /**
     * 拿到所有老人信息
     */

    public void getAllOldMessage(final IGetBothMessage callback) {
        model.getAllOldMessage(callback);
    }


    /**
     * 拿到所有护士信息
     */
    public void getAllNurseMessage(final IGetBothMessage callback) {
        model.getAllNurseMessage(callback);
    }
}
