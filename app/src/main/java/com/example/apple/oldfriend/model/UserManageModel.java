package com.example.apple.oldfriend.model;

import android.content.Context;
import android.util.Log;

import com.example.apple.oldfriend.cofing.ILogin;
import com.example.apple.oldfriend.cofing.IRegist;
import com.example.apple.oldfriend.cofing.IUser;
import com.example.apple.oldfriend.model.bean.User;

import java.io.File;
import java.util.List;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by gan on 2016/4/26.
 */
public class UserManageModel {
    private Context context;

    public UserManageModel(Context context) {
        this.context = context;
    }


    public void getUser(final IUser callback) {
        User user = BmobUser.getCurrentUser(context, User.class);
        if (user != null) {
            BmobQuery<User> query = new BmobQuery<>();
            query.addWhereEqualTo("username", user.getUsername());
            query.include("myOldState.oldPsychoState,myOldState.oldSociaState,myOldState.oldPhysioState,myNurseState," +
                    "" + "headPic");
            query.findObjects(context, new FindListener<User>() {
                @Override
                public void onSuccess(List<User> list) {
                    callback.getUserSuccess(list.get(0));
                }

                @Override
                public void onError(int i, String s) {
                    Log.d("TAG", "getUser----fail" + s);
                }
            });
        }
    }

    //发送验证码
    public void getSMS(String phoneNum) {
        BmobSMS.requestSMSCode(context, phoneNum, "模板名称", new RequestSMSCodeListener() {
            @Override
            public void done(Integer smsId, BmobException ex) {
                if (ex == null) {//验证码发送成功
                    Log.i("smile", "短信id：" + smsId);//用于查询本次短信发送详情
                }
            }
        });
    }

    public void register(String phoneNum, String password, String sms, Boolean isOld, User nurse, final
    IRegist callback) {
        User user = new User();
        user.setMobilePhoneNumber(phoneNum);
        user.setUsername(phoneNum);
        user.setPassword(password);
        user.setOld(isOld);
        if (nurse != null) {
            user.setMyNurse(nurse);
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


    public void login(String phoneNum, String passWord, final ILogin callback) {
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
    public void exitLogin() {
        BmobUser.logOut(context);
    }

    //上传头像
    public void upLoadHeadPic(String picPath) {
        final BmobFile bmobFile = new BmobFile(new File(picPath));
        final User user = BmobUser.getCurrentUser(context, User.class);
        bmobFile.uploadblock(context, new UploadFileListener() {
            @Override
            public void onSuccess() {
                user.setHeadPic(bmobFile);
                user.update(context, new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        Log.d("TAG", "upLoadFile-------Success" + bmobFile.getFileUrl(context));
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Log.d("TAG", "uoLoadFile-----Wrong");
                    }
                });
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("TAG", "upLoadFile-------Fail" + s);
            }
        });
    }

    //下拉头像
    public String getHeadPicUrl(User user) {
        BmobFile file = user.getHeadPic();
        if (file != null) {
            return file.getFileUrl(context);
        }
        return null;
    }

    //设置性别
    public void setSex(String sex) {
        User user = BmobUser.getCurrentUser(context, User.class);
        user.setSex(sex);
        user.update(context);
    }

    //设置血型
    public void setBlood(String blood) {
        User user = BmobUser.getCurrentUser(context, User.class);
        user.setSex(blood);
        user.update(context);
    }
}
