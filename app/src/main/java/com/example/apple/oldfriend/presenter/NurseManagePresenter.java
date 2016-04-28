package com.example.apple.oldfriend.presenter;

import android.content.Context;

import com.example.apple.oldfriend.cofing.IGetNurseState;
import com.example.apple.oldfriend.model.NurseManageModel;
import com.example.apple.oldfriend.model.bean.User;

/**
 * Created by gan on 2016/4/26
 * ********************测试通过********************
 *
 * FBI:warning如果要设置同时姓名和年龄、护士经验和状况，请不要单独调用他们方法，而是使用setNurseAllInfomation。
 */
public class NurseManagePresenter {
    private NurseManageModel model;

    public NurseManagePresenter(Context context) {
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

    /**
     * 设置护士的姓名和年龄
     */
    public void setNurseNameAndAge(User nurse, String name, Integer age) {
        model.setNurseNameAndAge(nurse, name, age);
    }

    /**
     * 设置护士的经验
     */
    public void setNurseExp(User nurse, String Exp) {
        model.setNurseExp(nurse, Exp);
    }

    /**
     * 设置护士的最新动态
     */
    public void setNurseSituation(User nurse, String situation) {
        model.setNurseSituation(nurse, situation);
    }

    /**
     * 设置护士的全部信息
     */
    public void setNurseAllInfomation(final User nurse, String name, Integer age, String Exp, String situation) {
        model.setNurseAllInfomation(nurse, name, age, Exp, situation);
    }
}
