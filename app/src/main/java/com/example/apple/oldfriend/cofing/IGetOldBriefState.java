package com.example.apple.oldfriend.cofing;

import com.example.apple.oldfriend.model.bean.OldSociaState;

import java.util.List;

/**
 * Created by gan on 2016/4/25.
 */
public interface IGetOldBriefState {
    void getOldSociaStateSuccess(List<OldSociaState> allOldSociaState);

    void getOldbriefStateSuccess(String s);

    void getOldNameAndAge(String name, Integer age);
}
