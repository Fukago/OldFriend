package com.example.apple.oldfriend.presenter;

import android.content.Context;

import com.example.apple.oldfriend.cofing.IGetOldBriefState;
import com.example.apple.oldfriend.cofing.IGetOldPhysioAndPsychoState;
import com.example.apple.oldfriend.model.OldManageModel;
import com.example.apple.oldfriend.model.bean.User;

/**
 * Created by gan on 2016/4/23.
 */
public class OldManagePresenter {
    private OldManageModel model;

    public OldManagePresenter(OldManageModel model, Context context) {
        model = new OldManageModel(context);
    }

    /**
     * 设置老人的简要身体状况
     */
    public void setOldbriefState(User oldPeople, String briefStateContent) {
        model.setOldbriefState(oldPeople, briefStateContent);
    }

    /**
     * 得到老人的简要身体状况
     */

    public void getOldBriefState(User oldPeople, IGetOldBriefState callback) {
        model.getOldBriefState(oldPeople, callback);
    }

    /**
     * 设置老人的年龄和姓名
     */
    public void setOldNameAndAge(User oldPeople, String name, Integer age) {
        model.setOldNameAndAge(oldPeople, name, age);
    }


    /**
     * 得到老人的年龄和姓名
     */
    public void getOldNameAndAge(User oldPeople, IGetOldBriefState callback) {
        model.getOldNameAndAge(oldPeople, callback);
    }


    /**
     * 设置老人的生理数据
     */
    public void setOldPhysioState(final User oldPeople, final String tiWen, final String xueTang, final String xueYa,
                                  final String xueZhi) {
        model.setOldPhysioState(oldPeople, tiWen, xueTang, xueYa, xueZhi);
    }

    /**
     * 得到老人的生理数据
     */

    public void getOldPhysioState(User oldPeople, final IGetOldPhysioAndPsychoState callback) {
        model.getOldPhysioState(oldPeople, callback);
    }

    /**
     * 设置老人的心理数据
     */

    public void setOldPsychoState(User oldPeople, final String content) {
        model.setOldPsychoState(oldPeople, content);
    }

    /**
     * 得到老人的心理数据
     */

    public void getOldPsychoState(User oldPeople, final IGetOldPhysioAndPsychoState callback) {
        model.getOldPsychoState(oldPeople, callback);
    }

    /**
     * 设置老人的社会数据
     */
    public void setOldSociaState(User oldPeople, final String situation) {
        model.setOldSociaState(oldPeople, situation);
    }

    /**
     * 得到老人的社会数据
     */
    public void getOldSociaState(User oldPeople, final IGetOldBriefState callback) {
        model.getOldSociaState(oldPeople, callback);
    }
}