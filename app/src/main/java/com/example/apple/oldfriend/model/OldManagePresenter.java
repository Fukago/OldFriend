package com.example.apple.oldfriend.model;

import android.content.Context;

import com.example.apple.oldfriend.model.bean.User;

/**
 * Created by gan on 2016/4/23.
 */
public class OldManagePresenter {
    private OldManageModel model;

    public OldManagePresenter(OldManageModel model, Context context) {
        model = new OldManageModel(context);
    }

    /**
     * 设置老人的简要身体状况
     */
    public void setOldbriefState(User oldPeople, String briefStateContent) {
        model.setOldbriefState(oldPeople, briefStateContent);
    }

    /**
     * 设置老人的生理数据
     */
    public void setOldPhysioState(final User oldPeople, final String tiWen, final String xueTang, final String xueYa,
                                  final String xueZhi) {
        model.setOldPhysioState(oldPeople, tiWen, xueTang, xueYa, xueZhi);
    }
}
