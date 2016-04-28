package com.example.apple.oldfriend.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by gan on 2016/4/22.
 * 护理人员状态
 */
public class NurseState extends BmobObject {
    private String name;
    private Integer age;
    private String exp;//护理经验
    private String situation;//最新状况

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

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    @Override
    public String toString() {
        return "NurseState{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", exp='" + exp + '\'' +
                ", situation='" + situation + '\'' +
                '}';
    }
}
