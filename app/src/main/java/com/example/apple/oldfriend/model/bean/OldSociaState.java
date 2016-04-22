package com.example.apple.oldfriend.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by gan on 2016/4/22.
 * <p/>
 * 老人的社会情况表
 */
public class OldSociaState extends BmobObject {
    private OldState oldState;
    private String key;
    private String value;

    public OldState getOldState() {
        return oldState;
    }

    public void setOldState(OldState oldState) {
        this.oldState = oldState;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}