package com.example.apple.oldfriend.cofing;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gan on 2016/5/4.
 */
public interface IGetOldPhyStateAndTimeList {
    void getOldPhyStateAndTimeListSuccess(List<String> timeList, HashMap<String, ArrayList<BarEntry>> stateMap);
}
