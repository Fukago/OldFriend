package com.example.apple.oldfriend.presenter;

import android.content.Context;

import com.example.apple.oldfriend.cofing.IGetBedState;
import com.example.apple.oldfriend.cofing.IGetMyNurse;
import com.example.apple.oldfriend.cofing.IGetOldBatheState;
import com.example.apple.oldfriend.cofing.IGetOldBriefState;
import com.example.apple.oldfriend.cofing.IGetOldClothState;
import com.example.apple.oldfriend.cofing.IGetOldHairCutState;
import com.example.apple.oldfriend.cofing.IGetOldPhyStateAndTimeList;
import com.example.apple.oldfriend.cofing.IGetOldPhysioAndPsychoState;
import com.example.apple.oldfriend.model.OldManageModel;
import com.example.apple.oldfriend.model.bean.User;

/**
 * Created by gan on 2016/4/23.
 * <p/>
 * ********************测试通过********************
 * FBI:warning如果要设置同时简要身体状态和姓名和年龄，请不要单独调用两个方法，而是调用setOldNameAndAgeAndBriefState方法。
 * <p/>
 * 血脂的正常范围：2.8～5.17mmol/L
 * 血压的正常范围：收缩压<130mmHg，舒张压<85mmHg；
 * 血糖的正常范围：<2.78mmol/L
 */
public class OldManagePresenter {
    private OldManageModel model;

    public OldManagePresenter(Context context) {
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
     * 设置老人的年龄和姓名和简要身体状况
     */

    public void setOldNameAndAgeAndBriefState(final User oldPeople, final String name, final Integer age, String
            briefStateContent) {
        model.setOldNameAndAgeAndBriefState(oldPeople, name, age, briefStateContent);
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


    /**
     * 得到照顾老人的护士
     */
    public void getMyNurseInfo(final IGetMyNurse callback) {
        model.getMyNurseInfo(callback);
    }


    /**
     * 得到老人全部身体数据时间、最大值、最小值和差值
     */
    public void getOldPhysioStateTimeList(User oldPeople, final IGetOldPhyStateAndTimeList callback) {
        model.getOldPhysioStateTimeList(oldPeople, callback);
    }


    //得到老人的洗澡数据
    public void getBatheState(User oldPeople, IGetOldBatheState callback) {
        model.getBatheState(oldPeople, callback);
    }

    //得到老人的服装数据
    public void getClothState(User oldPeople, IGetOldClothState callback) {
        model.getClothState(oldPeople, callback);
    }

    //得到老人的理发数据
    public void getHairCutState(User oldPeople, IGetOldHairCutState callback) {
        model.getHairCutState(oldPeople, callback);
    }

    //得到老人的床上用品数据
    public void getBedStateState(User oldPeople, IGetBedState callback) {
        model.getBedStateState(oldPeople, callback);
    }
}


