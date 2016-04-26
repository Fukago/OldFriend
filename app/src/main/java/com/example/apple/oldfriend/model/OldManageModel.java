package com.example.apple.oldfriend.model;

import android.content.Context;
import android.util.Log;

import com.example.apple.oldfriend.cofing.IGetOldBriefState;
import com.example.apple.oldfriend.cofing.IGetOldPhysioAndPsychoState;
import com.example.apple.oldfriend.model.bean.OldPhysioState;
import com.example.apple.oldfriend.model.bean.OldPsychoState;
import com.example.apple.oldfriend.model.bean.OldSociaState;
import com.example.apple.oldfriend.model.bean.User;
import com.example.apple.oldfriend.util.TimeUtil;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by gan on 2016/4/23.
 * <p>
 * 护理人员对老人信息进行编辑
 */
public class OldManageModel {
    private Context context;

    public OldManageModel(Context context) {
        this.context = context;
    }


    //设置简要情况
    public void setOldbriefState(User oldPeople, String briefStateContent) {
        oldPeople.getMyOldState().setBriefState(briefStateContent);
        oldPeople.update(context);
    }

    //设置姓名年龄
    public void setOldNameAndAge(User oldPeople, String name, Integer age) {
        oldPeople.getMyOldState().setName(name);
        oldPeople.getMyOldState().setAge(age);
        oldPeople.update(context);
    }

    //得到简要情况
    public void getOldBriefState(User oldPeople, IGetOldBriefState callback) {
        String briefState = oldPeople.getMyOldState().getBriefState();
        callback.getOldbriefStateSuccess(briefState);

    }

    //得到姓名年龄
    public void getOldNameAndAge(User oldPeople, IGetOldBriefState callback) {
        callback.getOldNameAndAge(oldPeople.getMyOldState().getName(), oldPeople.getMyOldState().getAge());
    }

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
//                        state.delete(context, new DeleteListener() {
//                            @Override
//                            public void onSuccess() {
//                                OldPhysioState oldPhysioState = new OldPhysioState();
//                                oldPhysioState.setOldState(oldPeople.getMyOldState());
//                                oldPhysioState.setTiwen(tiWen);
//                                oldPhysioState.setXuetang(xueTang);
//                                oldPhysioState.setXueya(xueYa);
//                                oldPhysioState.setXuezhi(xueZhi);
//                                oldPhysioState.save(context, new SaveListener() {
//                                    @Override
//                                    public void onSuccess() {
//                                        Log.d("TAG", "setOlPhysioState------>success");
//                                    }
//
//                                    @Override
//                                    public void onFailure(int i, String s) {
//                                        Log.d("TAG", "setOlPhysioState------>fail" + s);
//
//                                    }
//                                });
//                            }
//
//                            @Override
//                            public void onFailure(int i, String s) {
//                                Log.d("TAG", "setOlPhysioState->getOldPhysioState--->fail" + s);
//
//                            }
//                        });
                        state.setTiwen(tiWen);
                        state.setXuetang(xueTang);
                        state.setXueya(xueYa);
                        state.setXuezhi(xueZhi);
                        state.update(context, new UpdateListener() {
                            @Override
                            public void onSuccess() {
                                Log.d("TAG", "setOlPhysioState------>success");
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Log.d("TAG", "setOlPhysioState------>fail" + s);
                            }
                        });
                    } else {
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
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }

    //设置老人心理状况
    public void setOldPsychoState(User oldPeople, final String content) {
        BmobQuery<OldPsychoState> query = new BmobQuery<>();
        query.addWhereEqualTo("myOldState", oldPeople.getMyOldState());
        query.findObjects(context, new FindListener<OldPsychoState>() {
            @Override
            public void onSuccess(List<OldPsychoState> list) {
                for (OldPsychoState state : list) {
                    if (state.getCreatedAt().contains(TimeUtil.getTime("MM-dd"))) {
//                        state.delete(context, new DeleteListener() {
//                            @Override
//                            public void onSuccess() {
//                                OldPsychoState oldPsychoState = new OldPsychoState();
//                                oldPsychoState.setSituation(content);
//                                oldPsychoState.save(context, new SaveListener() {
//                                    @Override
//                                    public void onSuccess() {
//                                        Log.d("TAG", "setOldPsychoState------>success");
//                                    }
//
//                                    @Override
//                                    public void onFailure(int i, String s) {
//                                        Log.d("TAG", "setOlPhysioState->getOldPhysioState--->fail" + s);
//                                    }
//                                });
//                            }
//
//                            @Override
//                            public void onFailure(int i, String s) {
//
//                            }
//                        });
                        state.setSituation(content);
                        state.update(context, new UpdateListener() {
                            @Override
                            public void onSuccess() {
                                Log.d("TAG", "setOldPsychoState------>success");
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Log.d("TAG", "setOlPhysioState->getOldPhysioState--->fail" + s);
                            }
                        });
                    } else {
                        OldPsychoState oldPsychoState = new OldPsychoState();
                        oldPsychoState.setSituation(content);
                        oldPsychoState.save(context, new SaveListener() {
                            @Override
                            public void onSuccess() {
                                Log.d("TAG", "setOldPsychoState------>success");
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

    //设置老人的社会状况
    public void setOldSociaState(User oldPeople, final String situation) {
        BmobQuery<OldSociaState> query = new BmobQuery<>();
        query.addWhereEqualTo("myOldState", oldPeople.getMyOldState());
        query.findObjects(context, new FindListener<OldSociaState>() {
            @Override
            public void onSuccess(List<OldSociaState> list) {
                for (OldSociaState state : list) {
                    if (state.getCreatedAt().contains(TimeUtil.getTime("MM-dd"))) {
                        state.setSituation(situation);
                        state.update(context, new UpdateListener() {
                            @Override
                            public void onSuccess() {
                                Log.d("TAG", "setOldSociaState------>success");
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Log.d("TAG", "setOldSociaState---->fail" + s);
                            }
                        });
                    }
                }
            }

            @Override
            public void onError(int i, String s) {
                Log.d("TAG", "setOldSociaState-->getList-->fail" + s);

            }
        });


    }

    //得到老人全部身体数据
    public void getOldPhysioState(User oldPeople, final IGetOldPhysioAndPsychoState callback) {
        BmobQuery<OldPhysioState> query = new BmobQuery<>();
        query.addWhereEqualTo("myOldState", oldPeople.getMyOldState());
        query.findObjects(context, new FindListener<OldPhysioState>() {
            @Override
            public void onSuccess(List<OldPhysioState> list) {
                callback.getOldPhysioStateSuccess(list);
            }

            @Override
            public void onError(int i, String s) {
                callback.getOldPhysioStateFail(s);
            }
        });
    }

    //得到老人全部心理数据
    public void getOldPsychoState(User oldPeople, final IGetOldPhysioAndPsychoState callback) {
        BmobQuery<OldPsychoState> query = new BmobQuery<>();
        query.addWhereEqualTo("myOldState", oldPeople.getMyOldState());
        query.findObjects(context, new FindListener<OldPsychoState>() {
            @Override
            public void onSuccess(List<OldPsychoState> list) {
                callback.getOldPsychoStateSuccess(list);
            }

            @Override
            public void onError(int i, String s) {
                callback.getOldPsychoStateFail(s);
            }
        });
    }

    //得到老人全部的社会数据
    public void getOldSociaState(User oldPeople, final IGetOldBriefState callback) {
        BmobQuery<OldSociaState> query = new BmobQuery<>();
        query.addWhereEqualTo("myOldState", oldPeople.getMyOldState());
        query.findObjects(context, new FindListener<OldSociaState>() {
            @Override
            public void onSuccess(List<OldSociaState> list) {
                callback.getOldSociaStateSuccess(list);
            }

            @Override
            public void onError(int i, String s) {
                Log.d("TAG", "getOldSociaStateFail" + s);
            }
        });
    }


}
