package com.example.apple.oldfriend.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gan on 2016/4/22.
 */
public class TimeUtil {
    private static  SimpleDateFormat df;
    public static String getTime(String format) {
        if (format == null){
            format = "yyyy-MM-dd";
        }
         df = new SimpleDateFormat(format);//设置日期格式
        return df.format(new Date());
    }
    public static String getDateToString(long time) {
        Date d = new Date(time);
        df = new SimpleDateFormat("yyyy年MM月dd日");
        return df.format(d);
        }
}
