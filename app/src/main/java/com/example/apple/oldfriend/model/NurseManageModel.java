package com.example.apple.oldfriend.model;

import android.content.Context;

import com.example.apple.oldfriend.cofing.IGetNurseState;
import com.example.apple.oldfriend.model.bean.User;

/**
 * Created by gan on 2016/4/26.
 */
public class NurseManageModel {
    private Context context;

    public NurseManageModel(Context context) {
        this.context = context;
    }

    //设置护士的基本信息
    public void setNurseNameAndAge(User nurse, String name, Integer age) {
        nurse.getMyNurseState().setName(name);
        nurse.getMyNurseState().setAge(age);
        nurse.update(context);
    }

    //设置护士的经验
    public void setNurseExp(User nurse, String Exp) {
        nurse.getMyNurseState().setExp(Exp);
        nurse.update(context);
    }

    //设置护士的最新动态
    public void setNurseSituation(User nurse, String situation) {
        nurse.getMyNurseState().setSituation(situation);
        nurse.update(context);
    }

    //得到护士姓名和年龄
    public void getNurseNameAndAge(User nurse, IGetNurseState callback) {
        Integer age = nurse.getMyNurseState().getAge();
        String name = nurse.getMyNurseState().getName();
        callback.getNurseNameAndAgeSuccess(name, age);
    }

    public void getNurseExp(User nurse, IGetNurseState callback) {
        callback.getNurseExpSuccess(nurse.getMyNurseState().getExp());
    }

    public void getNurseSituation(User nurse, IGetNurseState callback) {
        callback.getNurseSituationSuccess(nurse.getMyNurseState().getSituation());
    }


}
