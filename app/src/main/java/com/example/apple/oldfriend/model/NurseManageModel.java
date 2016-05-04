package com.example.apple.oldfriend.model;

import android.content.Context;

import com.example.apple.oldfriend.cofing.IGetNurseState;
import com.example.apple.oldfriend.model.bean.NurseState;
import com.example.apple.oldfriend.model.bean.User;

import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by gan on 2016/4/26.
 */
public class NurseManageModel {
    private Context context;

    public NurseManageModel(Context context) {
        this.context = context;
    }

    //设置护士的全部信息
    public void setNurseAllInfomation(final User nurse, String name, Integer age, String Exp, String situation) {
        if (nurse.getMyNurseState() == null) {
            final NurseState nurseState = new NurseState();
            nurseState.setName(name);
            nurseState.setAge(age);
            nurseState.setSituation(situation);
            nurseState.setExp(Exp);
            nurseState.save(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    nurse.setMyNurseState(nurseState);
                    nurse.update(context);
                }

                @Override
                public void onFailure(int i, String s) {

                }
            });

        } else {
            nurse.getMyNurseState().setName(name);
            nurse.getMyNurseState().setAge(age);
            nurse.getMyNurseState().setSituation(situation);
            nurse.getMyNurseState().setExp(Exp);
            nurse.getMyNurseState().update(context, new UpdateListener() {
                @Override
                public void onSuccess() {
                    nurse.update(context);
                }

                @Override
                public void onFailure(int i, String s) {

                }
            });
        }
    }


    //设置护士的基本信息
    public void setNurseNameAndAge(final User nurse, String name, Integer age) {
        if (nurse.getMyNurseState() == null) {
            final NurseState nurseState = new NurseState();
            nurseState.setName(name);
            nurseState.setAge(age);
            nurse.save(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    nurse.setMyNurseState(nurseState);
                    nurse.update(context);
                }

                @Override
                public void onFailure(int i, String s) {

                }
            });

        } else {
            nurse.getMyNurseState().setName(name);
            nurse.getMyNurseState().setAge(age);
            nurse.getMyNurseState().update(context, new UpdateListener() {
                @Override
                public void onSuccess() {
                    nurse.update(context);
                }

                @Override
                public void onFailure(int i, String s) {

                }
            });
        }
    }

    //设置护士的经验
    public void setNurseExp(final User nurse, String Exp) {
        if (nurse.getMyNurseState() == null) {
            final NurseState nurseState = new NurseState();
            nurseState.setExp(Exp);
            nurseState.save(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    nurse.setMyNurseState(nurseState);
                    nurse.update(context);
                }

                @Override
                public void onFailure(int i, String s) {

                }
            });
        } else {
            nurse.getMyNurseState().setExp(Exp);
            nurse.update(context, new UpdateListener() {
                @Override
                public void onSuccess() {
                    nurse.update(context);
                }

                @Override
                public void onFailure(int i, String s) {

                }
            });
        }

    }

    //设置护士的最新动态
    public void setNurseSituation(final User nurse, String situation) {
        if (nurse.getMyNurseState() == null) {
            final NurseState nurseState = new NurseState();
            nurseState.setSituation(situation);
            nurseState.save(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    nurse.setMyNurseState(nurseState);
                    nurse.update(context);
                }

                @Override
                public void onFailure(int i, String s) {

                }
            });
        } else {
            nurse.getMyNurseState().setSituation(situation);
            nurse.update(context, new UpdateListener() {
                @Override
                public void onSuccess() {
                    nurse.update(context);
                }

                @Override
                public void onFailure(int i, String s) {

                }
            });
        }
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
