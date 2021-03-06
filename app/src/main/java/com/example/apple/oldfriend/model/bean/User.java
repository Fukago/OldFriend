package com.example.apple.oldfriend.model.bean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * 用户
 * Created by gan on 2016/4/21.
 */
public class User extends BmobUser {
    private BmobRelation likeArticle;//点赞的文章
    private String sex;//性别
    private String blood;//血型
    private Boolean isOld;//是否是老人
    private User myNurse;//我的护理人员
    private OldState myOldState;//老人的个人状态
    private NurseState myNurseState;//看护人员的个人状态

    private BmobFile headPic;

    public BmobRelation getLikeArticle() {
        return likeArticle;
    }

    public void setLikeArticle(BmobRelation likeArticle) {
        this.likeArticle = likeArticle;
    }

//


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public Boolean getOld() {
        return isOld;
    }

    public void setOld(Boolean old) {
        isOld = old;
    }


    public User getMyNurse() {
        return myNurse;
    }

    public void setMyNurse(User myNurse) {
        this.myNurse = myNurse;
    }

    public OldState getMyOldState() {
        return myOldState;
    }

    public void setMyOldState(OldState myOldState) {
        this.myOldState = myOldState;
    }

    public NurseState getMyNurseState() {
        return myNurseState;
    }

    public void setMyNurseState(NurseState myNurseState) {
        this.myNurseState = myNurseState;
    }

    public BmobFile getHeadPic() {
        return headPic;
    }

    public void setHeadPic(BmobFile headPic) {
        this.headPic = headPic;
    }
}
