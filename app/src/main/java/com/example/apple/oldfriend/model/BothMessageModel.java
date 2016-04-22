package com.example.apple.oldfriend.model;

import android.content.Context;

import com.example.apple.oldfriend.model.bean.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by gan on 2016/4/22.
 */
public class BothMessageModel {
    private Context context;

    public BothMessageModel(Context context) {
        this.context = context;
    }

    /**
     * 老人拿到照顾自己的护士的信息
     */

    public void getNurseMessage(final IGetBothMessage callback) {
        User me = BmobUser.getCurrentUser(context, User.class);
        if (me.isOld()) {
            BmobQuery<User> query = new BmobQuery<User>();
            query.addWhereEqualTo("myOld", me);    // 查询当前用户的护理人
            query.include("myNurseState");
            query.findObjects(context, new FindListener<User>() {
                @Override
                public void onSuccess(List<User> list) {
                    callback.getNurseMessage(list.get(0));
                }

                @Override
                public void onError(int i, String s) {
                    callback.getNurseMessageError(s);
                }
            });
        }
    }

    /**
     * 护士拿到老人的信息
     */

    public void getOldMessage(final IGetBothMessage callback) {
        User me = BmobUser.getCurrentUser(context, User.class);
        if (!me.isOld()) {
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
                    callback.getOldMessageError(s);
                }
            });
        }
    }

}
