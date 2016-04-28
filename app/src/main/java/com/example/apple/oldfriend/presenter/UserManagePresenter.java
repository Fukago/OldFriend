package com.example.apple.oldfriend.presenter;

import android.content.Context;

import com.example.apple.oldfriend.cofing.ILogin;
import com.example.apple.oldfriend.cofing.IRegist;
import com.example.apple.oldfriend.cofing.IUser;
import com.example.apple.oldfriend.model.UserManageModel;
import com.example.apple.oldfriend.model.bean.User;

/**
 * Created by gan on 2016/4/26.
 * <p>
 * ********************测试通过********************
 */
public class UserManagePresenter {
    private UserManageModel model;
    private Context context;

    public UserManagePresenter(Context context) {
        model = new UserManageModel(context);
    }

    /**
     * 得到当前用户
     */
    public void getUser(IUser callback) {
        model.getUser(callback);
    }


    /**
     * 发送验证码
     */
    public void getSMS(String phoneNum) {
        model.getSMS(phoneNum);
    }

    /**
     * 注册
     */

    public void register(String phoneNum, String password, String sms, Boolean isOld, User nurse, final
    IRegist callback) {
        model.register(phoneNum, password, sms, isOld, nurse, callback);
    }

    /**
     * 登陆
     */

    public void login(String phoneNum, String passWord, final ILogin callback) {
        model.login(phoneNum, passWord, callback);
    }

    /**
     * 退出登录
     */

    public void exitLogin() {
        model.exitLogin();
    }

    /**
     * 上传头像
     */
    public void uploadHeadPic(String picPath) {
        model.upLoadHeadPic(picPath);
    }

    /**
     * 得到头像地址
     */
    public String getHeadPicUrl(User user) {
        return model.getHeadPicUrl(user);
    }


}
