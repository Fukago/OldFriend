package com.example.apple.oldfriend.cofing;

import com.example.apple.oldfriend.model.bean.OldPhysioState;
import com.example.apple.oldfriend.model.bean.OldPsychoState;
import com.example.apple.oldfriend.model.bean.OldSociaState;

import java.util.List;

/**
 * Created by gan on 2016/4/25.
 */
public interface IGetOldPhysioAndPsychoState {
    void getOldPhysioStateSuccess(List<OldPhysioState> allOldPhysioState);

    void getOldPhysioStateFail(String s);

    void getOldPsychoStateSuccess(List<OldPsychoState> allOldPsychoState);

    void getOldPsychoStateFail(String s);

}
