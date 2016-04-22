package com.example.apple.oldfriend.model.bean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * 用户
 * Created by gan on 2016/4/21.
 */
public class User extends BmobUser {
    private BmobRelation likeArticle;//点赞的文章
    private String username;//手机号码
    private String password;//密码
    private Integer age;
    private boolean isOld;//是否是老人
    private BmobPointer myOld;//我照顾的老人
    private BmobPointer myNurse;//我的护理人员

    public BmobRelation getLikeArticle() {
        return likeArticle;
    }

    public void setLikeArticle(BmobRelation likeArticle) {
        this.likeArticle = likeArticle;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isOld() {
        return isOld;
    }

    public void setOld(boolean old) {
        isOld = old;
    }

    public BmobPointer getMyOld() {
        return myOld;
    }

    public void setMyOld(BmobPointer myOld) {
        this.myOld = myOld;
    }

    public BmobPointer getMyNurse() {
        return myNurse;
    }

    public void setMyNurse(BmobPointer myNurse) {
        this.myNurse = myNurse;
    }
}
