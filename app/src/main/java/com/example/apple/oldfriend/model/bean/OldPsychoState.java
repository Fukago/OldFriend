package com.example.apple.oldfriend.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by gan on 2016/4/22.
 * <p/>
 * 老人的心理情况表
 */
public class OldPsychoState extends BmobObject {
    private OldState myOldState;
    private String situation;


    public OldState getMyOldState() {
        return myOldState;
    }

    public void setMyOldState(OldState myOldState) {
        this.myOldState = myOldState;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    @Override
    public String toString() {
        return "OldPsychoState{" +
                "myOldState=" + myOldState +
                ", situation='" + situation + '\'' +
                '}';
    }
}
