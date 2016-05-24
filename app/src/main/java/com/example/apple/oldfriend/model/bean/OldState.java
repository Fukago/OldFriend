package com.example.apple.oldfriend.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by gan on 2016/4/22.
 * 老人情况的总表
 */
public class OldState extends BmobObject {
    private String name;//老人名字
    private Integer age;//老人年龄
    private String briefState;//基本健康情况
    private String batheState;//洗澡基本状况
    private String clothState;//服装基本状况
    private String hairCutState;//剪头基本状况
    private String bedState;//床上用品基本状况

    public String getBatheState() {
        return batheState;
    }

    public String getClothState() {
        return clothState;
    }

    public String getHairCutState() {
        return hairCutState;
    }

    public String getBedState() {
        return bedState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBriefState() {
        return briefState;
    }

    public void setBriefState(String briefState) {
        this.briefState = briefState;
    }


}
