package com.example.apple.oldfriend.model;

import android.content.Context;
import android.util.Log;

import com.example.apple.oldfriend.cofing.IGetBedState;
import com.example.apple.oldfriend.cofing.IGetMyNurse;
import com.example.apple.oldfriend.cofing.IGetOldBatheState;
import com.example.apple.oldfriend.cofing.IGetOldBriefState;
import com.example.apple.oldfriend.cofing.IGetOldClothState;
import com.example.apple.oldfriend.cofing.IGetOldHairCutState;
import com.example.apple.oldfriend.cofing.IGetOldPhyStateAndTimeList;
import com.example.apple.oldfriend.cofing.IGetOldPhysioAndPsychoState;
import com.example.apple.oldfriend.model.bean.OldPhysioState;
import com.example.apple.oldfriend.model.bean.OldPsychoState;
import com.example.apple.oldfriend.model.bean.OldSociaState;
import com.example.apple.oldfriend.model.bean.OldState;
import com.example.apple.oldfriend.model.bean.User;
import com.example.apple.oldfriend.util.TimeUtil;
import com.github.mikephil.charting.data.BarEntry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

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


    //设置简要情况
    public void setOldbriefState(final User oldPeople, String briefStateContent) {
        if (oldPeople.getMyOldState() == null) {
            final OldState oldState = new OldState();
            oldState.setBriefState(briefStateContent);
            oldState.save(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    oldPeople.setMyOldState(oldState);
                    oldPeople.update(context);
                }

                @Override
                public void onFailure(int i, String s) {

                }
            });
        } else {
            oldPeople.getMyOldState().setBriefState(briefStateContent);
            oldPeople.getMyOldState().update(context);
        }
    }


    //设置姓名年龄
    public void setOldNameAndAge(final User oldPeople, final String name, final Integer age) {
        if (oldPeople.getMyOldState() == null) {
            final OldState oldState = new OldState();
            oldState.setName(name);
            oldState.setAge(age);
            oldState.save(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    oldPeople.setMyOldState(oldState);
                    oldPeople.update(context);
                }

                @Override
                public void onFailure(int i, String s) {
                }
            });
        } else {
            oldPeople.getMyOldState().setName(name);
            oldPeople.getMyOldState().setAge(age);
            oldPeople.getMyOldState().update(context, new UpdateListener() {
                @Override
                public void onSuccess() {
                    oldPeople.update(context);
                }

                @Override
                public void onFailure(int i, String s) {
                }
            });
        }
    }

    //同时设置姓名年龄简要状况
    public void setOldNameAndAgeAndBriefState(final User oldPeople, final String name, final Integer age, String
            briefStateContent) {
        if (oldPeople.getMyOldState() == null) {
            final OldState oldState = new OldState();
            oldState.setName(name);
            oldState.setAge(age);
            oldState.setBriefState(briefStateContent);
            oldState.save(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    oldPeople.setMyOldState(oldState);
                    oldPeople.update(context);
                }

                @Override
                public void onFailure(int i, String s) {
                }
            });
        } else {
            oldPeople.getMyOldState().setName(name);
            oldPeople.getMyOldState().setAge(age);
            oldPeople.getMyOldState().setBriefState(briefStateContent);
            oldPeople.getMyOldState().update(context, new UpdateListener() {
                @Override
                public void onSuccess() {
                    oldPeople.update(context);
                }

                @Override
                public void onFailure(int i, String s) {

                }
            });
        }

    }

    public void getMyNurseInfo(final IGetMyNurse callback) {
        User user = BmobUser.getCurrentUser(context, User.class);
        if (user != null) {
            BmobQuery<User> query = new BmobQuery<>();
            query.addWhereEqualTo("objectId", user.getMyNurse().getObjectId());
            query.include("myNurseState");
            query.findObjects(context, new FindListener<User>() {
                @Override
                public void onSuccess(List<User> list) {
                    callback.getMyNurseSuccess(list.get(0));

                }

                @Override
                public void onError(int i, String s) {
                    Log.d("TAG", "getNurse----fail------->>>>>>>>>>" + s);
                }
            });
        }
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
        query.order("-createdAt");
        query.addWhereEqualTo("myOldState", oldPeople.getMyOldState());
        query.findObjects(context, new FindListener<OldPhysioState>() {
            @Override
            public void onSuccess(List<OldPhysioState> list) {
                if (list.size() == 0) {
                    OldPhysioState oldPhysioState = new OldPhysioState();
                    oldPhysioState.setMyOldState(oldPeople.getMyOldState());
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
                } else {
                    OldPhysioState state = list.get(0);
                    if (state.getCreatedAt().contains(TimeUtil.getTime("MM-dd"))) {
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
                        oldPhysioState.setMyOldState(oldPeople.getMyOldState());
                        oldPhysioState.setTiwen(tiWen);
                        oldPhysioState.setXuetang(xueTang);
                        oldPhysioState.setXueya(xueYa);
                        oldPhysioState.setXuezhi(xueZhi);
                        oldPhysioState.save(context);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }

    //设置老人心理状况
    public void setOldPsychoState(final User oldPeople, final String content) {
        BmobQuery<OldPsychoState> query = new BmobQuery<>();
        query.addWhereEqualTo("myOldState", oldPeople.getMyOldState());
        query.order("-createdAt");
        query.findObjects(context, new FindListener<OldPsychoState>() {
            @Override
            public void onSuccess(List<OldPsychoState> list) {
                if (list.size() == 0) {
                    OldPsychoState oldPsychoState = new OldPsychoState();
                    oldPsychoState.setSituation(content);
                    oldPsychoState.setMyOldState(oldPeople.getMyOldState());
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
                } else {
                    OldPsychoState state = list.get(0);
                    if (state.getCreatedAt().contains(TimeUtil.getTime("MM-dd"))) {
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
                        oldPsychoState.setMyOldState(oldPeople.getMyOldState());
                        oldPsychoState.save(context);
                    }
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    //设置老人的社会状况
    public void setOldSociaState(final User oldPeople, final String situation) {
        BmobQuery<OldSociaState> query = new BmobQuery<>();
        query.addWhereEqualTo("myOldState", oldPeople.getMyOldState());
        query.order("-createdAt");
        query.findObjects(context, new FindListener<OldSociaState>() {
            @Override
            public void onSuccess(List<OldSociaState> list) {
                if (list.size() == 0) {
                    OldSociaState oldSociaState = new OldSociaState();
                    oldSociaState.setMyOldState(oldPeople.getMyOldState());
                    oldSociaState.setSituation(situation);
                    oldSociaState.save(context);
                } else {
                    if (list.get(0).getCreatedAt().contains(TimeUtil.getTime("MM-dd"))) {
                        list.get(0).setSituation(situation);
                        list.get(0).update(context, new UpdateListener() {
                            @Override
                            public void onSuccess() {
                                Log.d("TAG", "setOldSociaState------>success");
                            }

                            @Override
                            public void onFailure(int i, String s) {
                                Log.d("TAG", "setOldSociaState---->fail" + s);
                            }
                        });
                    } else {
                        OldSociaState oldSociaState = new OldSociaState();
                        oldSociaState.setMyOldState(oldPeople.getMyOldState());
                        oldSociaState.setSituation(situation);
                        oldSociaState.save(context);
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
                Log.d("TAG", "getOldPhysioState--Wrong" + s);
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
                Log.d("TAG", "getOldPsychoState--Wrong" + s);
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

    //得到老人全部身体数据时间、最大值、最小值和差值
    public void getOldPhysioStateTimeList(User oldPeople, final IGetOldPhyStateAndTimeList callback) {
        final List<String> timeList = new ArrayList<>();
        final List<Integer> bigList = new ArrayList<>();
        final ArrayList<BarEntry> tiwenList = new ArrayList<>();
        final ArrayList<BarEntry> xuetangList = new ArrayList<>();
        final ArrayList<BarEntry> xueyaList = new ArrayList<>();
        final ArrayList<BarEntry> xuezhiList = new ArrayList<>();
        final HashMap<String, ArrayList<BarEntry>> stateMap = new HashMap<>();
        BmobQuery<OldPhysioState> query = new BmobQuery<>();
        query.addWhereEqualTo("myOldState", oldPeople.getMyOldState());
        query.findObjects(context, new FindListener<OldPhysioState>() {
            @Override
            public void onSuccess(List<OldPhysioState> list) {
                float maxValue = 0;
                int i = 0;
                for (OldPhysioState oldPhysioState : list) {
                    //设置全部身体数据时间
                    String pat1 = "yyyy-MM-dd HH:mm:ss";
                    SimpleDateFormat sdf1 = new SimpleDateFormat(pat1);
                    SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd");
                    Date d = null;
                    try {
                        d = sdf1.parse(oldPhysioState.getCreatedAt());
                        if (!timeList.contains(sdf2.format(d))) {
                            timeList.add(sdf2.format(d));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (Float.valueOf(oldPhysioState.getXueya()) > maxValue) {
                        maxValue = Float.valueOf(oldPhysioState.getXueya());
                    }
                    tiwenList.add(new BarEntry(Float.valueOf(oldPhysioState.getTiwen()), i));
                    xuetangList.add(new BarEntry(Float.valueOf(oldPhysioState.getXuetang()), i));
                    xueyaList.add(new BarEntry(Float.valueOf(oldPhysioState.getXueya()), i));
                    xuezhiList.add(new BarEntry(Float.valueOf(oldPhysioState.getXuezhi()), i));
                    i++;
                }
//                for (int i = 0; i <= maxValue + 5; i += 15) {
//                    bigList.add(i);
//                }
                stateMap.put("tiwen", tiwenList);
                stateMap.put("xuetang", xuetangList);

                stateMap.put("xueya", xueyaList);
                stateMap.put("xuezhi", xuezhiList);
                callback.getOldPhyStateAndTimeListSuccess(timeList, stateMap);
            }

            @Override
            public void onError(int i, String s) {
                Log.d("TAG", "getOldPhysioState--Wrong" + s);
            }
        });
    }

    //得到老人的洗澡数据
    public void getBatheState(User oldPeople, IGetOldBatheState callback) {
        callback.getBatheStare(oldPeople.getMyOldState().getBatheState());
    }

    //得到老人的服装数据
    public void getClothState(User oldPeople, IGetOldClothState callback) {
        callback.getClothStare(oldPeople.getMyOldState().getClothState());
    }

    //得到老人的理发数据
    public void getHairCutState(User oldPeople, IGetOldHairCutState callback) {
        callback.getHairCutStare(oldPeople.getMyOldState().getHairCutState());
    }

    //得到老人的床上用品数据
    public void getBedStateState(User oldPeople, IGetBedState callback) {
        callback.getBedStare(oldPeople.getMyOldState().getBedState());
    }


}
