package com.example.apple.oldfriend.model;

import android.content.Context;
import android.util.Log;

import com.example.apple.oldfriend.model.bean.OldPhysioState;
import com.example.apple.oldfriend.model.bean.OldPsychoState;
import com.example.apple.oldfriend.model.bean.OldSociaState;
import com.example.apple.oldfriend.model.bean.OldState;
import com.example.apple.oldfriend.model.bean.User;
import com.example.apple.oldfriend.util.TimeUtil;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by gan on 2016/4/23.
 * <p/>
 * 护理人员对老人信息进行编辑
 */
public class OldManageModel {
    private Context context;

    public OldManageModel(Context context) {
        this.context = context;
    }

    public void setOldbriefState(User oldPeople, String briefStateContent) {
        oldPeople.getMyOldState().setBriefState(briefStateContent);
        oldPeople.save(context);
    }


    //
//    public void setOldSocialState(User oldPeople, String key, String value) {
//        OldSociaState oldSociaState = new OldSociaState();
//        oldSociaState.setKey(key);
//        oldSociaState.setValue(value);
//        oldSociaState.setOldState(oldPeople.getMyOldState());
//        oldSociaState.save(context, new SaveListener() {
//            @Override
//            public void onSuccess() {
//                Log.d("TAG", "setOldSocialState-------------->success");
//            }
//
//            @Override
//            public void onFailure(int i, String s) {
//                Log.d("TAG", "setOldSocialState-------------->fail" + s);
//            }
//        });
//    }
//
//    public void setOldPhysioState(User oldPeople, String key, String value) {
//        OldPhysioState oldPhysioState = new OldPhysioState();
//        oldPhysioState.setKey(key);
//        oldPhysioState.setValue(value);
//        oldPhysioState.setOldState(oldPeople.getMyOldState());
//        oldPhysioState.save(context, new SaveListener() {
//            @Override
//            public void onSuccess() {
//                Log.d("TAG", "setOldPhysioState-------------->success");
//            }
//
//            @Override
//            public void onFailure(int i, String s) {
//                Log.d("TAG", "setOldPhysioState-------------->fail" + s);
//            }
//        });
//    }
//
    //设置生理数据
    public void setOldPhysioState(final User oldPeople, final String tiWen, final String xueTang, final String xueYa,
                                  final String xueZhi) {
        BmobQuery<OldPhysioState> query = new BmobQuery<>();
        query.addWhereEqualTo("myOldState", oldPeople.getMyOldState());
        query.findObjects(context, new FindListener<OldPhysioState>() {
            @Override
            public void onSuccess(List<OldPhysioState> list) {
                for (OldPhysioState state : list) {
                    if (state.getCreatedAt().contains(TimeUtil.getTime("MM-dd"))) {
                        state.delete(context, new DeleteListener() {
                            @Override
                            public void onSuccess() {
                                OldPhysioState oldPhysioState = new OldPhysioState();
                                oldPhysioState.setOldState(oldPeople.getMyOldState());
                                oldPhysioState.setTiwen(tiWen);
                                oldPhysioState.setXuetang(xueTang);
                                oldPhysioState.setXueya(xueYa);
                                oldPhysioState.setXuezhi(xueZhi);
                                oldPhysioState.save(context, new SaveListener() {
                                    @Override
                                    public void onSuccess() {
                                        Log.d("TAG", "setOlPhysioState------>success");
                                    }

                                    @Override
                                    public void onFailure(int i, String s) {
                                        Log.d("TAG", "setOlPhysioState------>fail" + s);

                                    }
                                });
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Log.d("TAG", "setOlPhysioState->getOldPhysioState--->fail" + s);

                            }
                        });
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }

}
