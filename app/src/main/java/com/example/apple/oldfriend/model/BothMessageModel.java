package com.example.apple.oldfriend.model;

import android.content.Context;
import android.util.Log;

import com.example.apple.oldfriend.cofing.IGetBothMessage;
import com.example.apple.oldfriend.model.bean.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;

/**
 * Created by gan on 2016/4/22.
 */
public class BothMessageModel {
    private Context context;

    public BothMessageModel(Context context) {
        this.context = context;
    }


    /**
     * 拿到所有护士信息
     */
    public void getAllNurseMessage(final IGetBothMessage callback) {
        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereEqualTo("isOld", false);
        query.include("myOld,myNurseState");
        query.findObjects(context, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                callback.getAllNurseMessage(list);
            }

            @Override
            public void onError(int i, String s) {
                Log.d("TAG", "getAllNurseMessage(s)" + s);

            }
        });
    }

    /**
     * 拿到所有老人信息
     */

    public void getAllOldMessage(final IGetBothMessage callback) {
        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereEqualTo("isOld", true);
        query.include("myOldState.oldPsychoState,myOldState.oldSociaState,myOldState.oldPhysioState");
        query.findObjects(context, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                callback.getAllOldMessage(list);
            }

            @Override
            public void onError(int i, String s) {
                Log.d("TAG", "getAllOldMessage(s)" + s);
            }
        });
    }

    /**
     * 老人拿到照顾自己的护士的信息
     */
    public void getNurseMessage(final IGetBothMessage callback) {
        User me = BmobUser.getCurrentUser(context, User.class);
        if (me.getOld()) {
            BmobQuery<User> query = new BmobQuery<User>();
            query.include("myNurse.myNurseState");
            query.getObject(context, me.getObjectId(), new GetListener<User>() {
                @Override
                public void onSuccess(User user) {
                    callback.getNurseMessage(user.getMyNurse());
                }

                @Override
                public void onFailure(int i, String s) {
                    Log.d("TAG", "getNurseMessage---wrong" + s);
                }
            });
        }
    }

    /**
     * 护士拿到老人的信息
     */

    public void getOldMessage(final IGetBothMessage callback) {
        User me = BmobUser.getCurrentUser(context, User.class);
        if (!me.getOld()) {
            BmobQuery<User> query = new BmobQuery<>();
            query.addWhereEqualTo("myNurse", me);
            query.include("myOldState");
            query.findObjects(context, new FindListener<User>() {
                @Override
                public void onSuccess(List<User> list) {
                    callback.getOldMessage(list);
                }

                @Override
                public void onError(int i, String s) {
                    Log.d("TAG", "getOldMessageError(s)" + s);
                }
            });
        }
    }


}
