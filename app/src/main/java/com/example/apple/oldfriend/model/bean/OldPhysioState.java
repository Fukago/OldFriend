package com.example.apple.oldfriend.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by gan on 2016/4/22.
 *
 * 老人的生理情况表
 */
public class OldPhysioState extends BmobObject {
    private OldState oldState;
    private String xueya;
    private String xuetang;
    private String xuezhi;
    private String tiwen;


    public OldState getOldState() {
        return oldState;
    }

    public void setOldState(OldState oldState) {
        this.oldState = oldState;
    }

    public String getXueya() {
        return xueya;
    }

    public void setXueya(String xueya) {
        this.xueya = xueya;
    }

    public String getXuetang() {
        return xuetang;
    }

    public void setXuetang(String xuetang) {
        this.xuetang = xuetang;
    }

    public String getXuezhi() {
        return xuezhi;
    }

    public void setXuezhi(String xuezhi) {
        this.xuezhi = xuezhi;
    }

    public String getTiwen() {
        return tiwen;
    }

    public void setTiwen(String tiwen) {
        this.tiwen = tiwen;
    }
}
