package com.example.apple.oldfriend.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by gan on 2016/4/22.
 * 老人情况的总表
 */
public class OldState extends BmobObject {
    private String name;//老人名字
    private String age;//老人年龄
    private String briefState;//基本健康情况
    private OldPhysioState oldPhysioState;//老人生理情况表
    private OldPsychoState oldPsychoState;//老人心理情况表
    private OldSociaState oldSociaState;//老人社会情况表

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBriefState() {
        return briefState;
    }

    public void setBriefState(String briefState) {
        this.briefState = briefState;
    }

    public OldPhysioState getOldPhysioState() {
        return oldPhysioState;
    }

    public void setOldPhysioState(OldPhysioState oldPhysioState) {
        this.oldPhysioState = oldPhysioState;
    }

    public OldPsychoState getOldPsychoState() {
        return oldPsychoState;
    }

    public void setOldPsychoState(OldPsychoState oldPsychoState) {
        this.oldPsychoState = oldPsychoState;
    }

    public OldSociaState getOldSociaState() {
        return oldSociaState;
    }

    public void setOldSociaState(OldSociaState oldSociaState) {
        this.oldSociaState = oldSociaState;
    }
}
