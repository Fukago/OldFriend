package com.example.apple.oldfriend.util;

import android.content.Context;
import android.util.Log;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by gan on 2016/4/28.
 */
public class FileUtil {


    public static void upLoadFile(final Context context, String filePath) {
        final BmobFile bmobFile = new BmobFile(new File(filePath));
        bmobFile.uploadblock(context, new UploadFileListener() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "upLoadFile-------Success" + bmobFile.getFileUrl(context));
            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("TAG", "upLoadFile-------Fail" + s);
            }
        });
    }


}
