package com.example.apple.oldfriend.presenter;

import android.content.Context;

import com.example.apple.oldfriend.cofing.IGetNurseState;
import com.example.apple.oldfriend.model.NurseManageModel;
import com.example.apple.oldfriend.model.bean.User;

/**
 * Created by gan on 2016/4/26.
 */
public class NurseManagePresenter {
    private NurseManageModel model;

    public NurseManagePresenter(NurseManageModel model, Context context) {
        model = new NurseManageModel(context);
    }

    /**
     * 得到护士姓名和年龄
     */
    public void getNurseNameAndAge(User nurse, IGetNurseState callback) {
        model.getNurseNameAndAge(nurse, callback);
    }

    /**
     * 得到护士经验
     */
    public void getNurseExp(User nurse, IGetNurseState callback) {
        model.getNurseExp(nurse, callback);
    }

    /**
     * 得到护士最新状况
     */

    public void getNurseSituation(User nurse, IGetNurseState callback) {
        model.getNurseSituation(nurse, callback);
    }
}
