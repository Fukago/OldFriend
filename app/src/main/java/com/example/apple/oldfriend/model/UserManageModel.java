package com.example.apple.oldfriend.model;

import android.content.Context;
import android.util.Log;

import com.example.apple.oldfriend.cofing.ILogin;
import com.example.apple.oldfriend.cofing.IRegist;
import com.example.apple.oldfriend.model.bean.NurseState;
import com.example.apple.oldfriend.model.bean.OldPhysioState;
import com.example.apple.oldfriend.model.bean.OldPsychoState;
import com.example.apple.oldfriend.model.bean.OldSociaState;
import com.example.apple.oldfriend.model.bean.OldState;
import com.example.apple.oldfriend.model.bean.User;

import java.util.List;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by gan on 2016/4/26.
 */
public class UserManageModel {
    private Context context;

    public UserManageModel(Context context) {
        this.context = context;
    }


    //发送验证码
    private void getSMS(String phoneNum) {
        BmobSMS.requestSMSCode(context, phoneNum, "模板名称", new RequestSMSCodeListener() {
            @Override
            public void done(Integer smsId, BmobException ex) {
                if (ex == null) {//验证码发送成功
                    Log.i("smile", "短信id：" + smsId);//用于查询本次短信发送详情
                }
            }
        });
    }

    private void register(String phoneNum, String password, String sms, Boolean isOld, User nurse, final
    IRegist callback) {
        User user = new User();
        user.setMobilePhoneNumber(phoneNum);
        user.setUsername(phoneNum);
        user.setPassword(password);
        user.setOld(isOld);
        if (isOld) {
            user.setMyNurse(nurse);
            OldState oldState = new OldState();
            OldSociaState oldSociaState = new OldSociaState();
            oldSociaState.save(context);
            OldPhysioState oldPhysioState = new OldPhysioState();
            oldPhysioState.save(context);
            OldPsychoState oldPsychoState = new OldPsychoState();
            oldPsychoState.save(context);
            oldState.setOldPhysioState(oldPhysioState);
            oldState.setOldPsychoState(oldPsychoState);
            oldState.setOldSociaState(oldSociaState);
            oldState.save(context);
            user.setMyOldState(oldState);
        } else {
            NurseState nurseState = new NurseState();
            nurseState.save(context);
            user.setMyNurseState(nurseState);
        }
        user.signOrLogin(context, sms, new SaveListener() {

            @Override
            public void onSuccess() {
                callback.registSuccess();
            }

            @Override
            public void onFailure(int code, String msg) {
                Log.d("TAG", "Register OR Login Wrong" + msg);
            }
        });
    }


    private void login(String phoneNum, String passWord, final ILogin callback) {
        BmobUser.loginByAccount(context, phoneNum, passWord, new LogInListener<User>() {
            @Override
            public void done(User user, cn.bmob.v3.exception.BmobException e) {
                if (user != null) {
                    callback.loginSuccess();
                } else {
                    Log.d("TAG", "LoginWrong----->" + e.getMessage());
                }
            }
        });
    }

    //退出登录
    private void exitLogin() {
        BmobUser.logOut(context);
    }
}