package com.liuliuzhu.autojs.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import com.liuliuzhu.autojs.BuildConfig;

import java.io.File;

/**
 * @author SmartSean
 * @date 17/12/11 14:23
 * @desc 已被修改：2020/05/22 by 溜溜猪
 */
class FileProviderUtils {

    public static Uri getUriForFile(Context mContext, File file) {
        Uri fileUri = null;
        if (Build.VERSION.SDK_INT >= 24) {
            fileUri = getUriForFile24(mContext, file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }

    private static Uri getUriForFile24(Context mContext, File file) {
        Uri fileUri = FileProvider.getUriForFile(mContext,
                BuildConfig.APPLICATION_ID + ".fileprovider",
                file);
        return fileUri;
    }

    static void setIntentDataAndType(Context mContext,
                                     Intent intent,
                                     String type,
                                     File file,
                                     boolean writeAble) {
        if (Build.VERSION.SDK_INT >= 24) {
            Uri contentUri = getUriForFile(mContext, file);
            intent.setDataAndType(contentUri, type);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        } else {
            intent.setDataAndType(Uri.fromFile(file), type);
        }
    }
}
