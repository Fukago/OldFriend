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
}
