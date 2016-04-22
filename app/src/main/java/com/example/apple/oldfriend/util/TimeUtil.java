package com.example.apple.oldfriend.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gan on 2016/4/22.
 */
public class TimeUtil {
    public static String getTime(String format) {
        if (format == null){
            format = "yyyy-MM-dd";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);//设置日期格式
        return df.format(new Date());
    }
}
