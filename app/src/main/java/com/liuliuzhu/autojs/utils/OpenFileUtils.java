package com.liuliuzhu.autojs.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;

import java.io.File;

import static com.liuliuzhu.autojs.utils.FileProviderUtils.getUriForFile;

/**
 * @author SmartSean
 * @date 17/12/11 16:04
 * @desc 已被修改：2020/05/23 by 溜溜猪
 */
public class OpenFileUtils {

    /**
     * 声明各种类型文件的dataType
     **/
    private static final String DATA_TYPE_APK = "application/vnd.android.package-archive";
    private static final String DATA_TYPE_VIDEO = "video/*";
    private static final String DATA_TYPE_AUDIO = "audio/*";
    private static final String DATA_TYPE_HTML = "text/html";
    private static final String DATA_TYPE_IMAGE = "image/*";
    private static final String DATA_TYPE_PPT = "application/vnd.ms-powerpoint";
    private static final String DATA_TYPE_EXCEL = "application/vnd.ms-excel";
    private static final String DATA_TYPE_WORD = "application/msword";
    private static final String DATA_TYPE_CHM = "application/x-chm";
    private static final String DATA_TYPE_TXT = "text/plain";
    private static final String DATA_TYPE_PDF = "application/pdf";
    /**
     * 未指定明确的文件类型，不能使用精确类型的工具打开，需要用户选择
     */
    private static final String DATA_TYPE_ALL = "*/*";

    /**
     * 打开文件
     * @param mContext
     * @param file
     */
    public static void openFile(Context mContext, File file) {
        if (!file.exists()) {
            return;
        }
        // 取得文件扩展名
        String end = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()).toLowerCase();
        // 依扩展名的类型决定MimeType
        switch (end) {
            case "3gp":
            case "mp4":
                openVideoFileIntent(mContext, file);
                break;
            case "m4a":
            case "mp3":
            case "mid":
            case "xmf":
            case "ogg":
            case "wav":
                openAudioFileIntent(mContext, file);
                break;
            case "doc":
            case "docx":
                commonOpenFileWithType(mContext, file, DATA_TYPE_WORD);
                break;
            case "xls":
            case "xlsx":
                commonOpenFileWithType(mContext, file, DATA_TYPE_EXCEL);
                break;
            case "jpg":
            case "gif":
            case "png":
            case "jpeg":
            case "bmp":
                commonOpenFileWithType(mContext, file, DATA_TYPE_IMAGE);
                break;
            case "txt":
            case "js":
                commonOpenFileWithType(mContext, file, DATA_TYPE_TXT);
                break;
            case "htm":
            case "html":
                commonOpenFileWithType(mContext, file, DATA_TYPE_HTML);
                break;
            case "apk":
                commonOpenFileWithType(mContext, file, DATA_TYPE_APK);
                break;
            case "ppt":
                commonOpenFileWithType(mContext, file, DATA_TYPE_PPT);
                break;
            case "pdf":
                commonOpenFileWithType(mContext, file, DATA_TYPE_PDF);
                break;
            case "chm":
                commonOpenFileWithType(mContext, file, DATA_TYPE_CHM);
                break;
            default:
                commonOpenFileWithType(mContext, file, DATA_TYPE_ALL);
                break;
        }
    }

    /**
     * 分享前必须执行本代码，主要用于兼容SDK18以上的系统
     */
    private static void checkFileUriExposure() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }
    }
    /**
     * Android传入type打开文件
     * @param mContext
     * @param file
     * @param type
     */
    private static void commonOpenFileWithType(Context mContext, File file, String type) {


        if (TextUtils.isEmpty(file.toString())) {
            return;
        }

        checkFileUriExposure();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));  //传输图片或者文件 采用流的方式
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        Uri contentUri = getUriForFile(mContext, file);
        intent.setDataAndType(contentUri, type);
        mContext.startActivity(Intent.createChooser(intent, "打开方式"));

//-------------------old-------------------------
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        FileProviderUtils.setIntentDataAndType(mContext, intent, type, file, true);
//        mContext.startActivity(intent);
    }

    /**
     * Android打开Video文件
     * @param mContext
     * @param file
     */
    private static void openVideoFileIntent(Context mContext, File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        FileProviderUtils.setIntentDataAndType(mContext, intent, DATA_TYPE_VIDEO, file, false);
        mContext.startActivity(intent);
    }

    /**
     * Android打开Audio文件
     * @param mContext
     * @param file
     */
    private static void openAudioFileIntent(Context mContext, File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        FileProviderUtils.setIntentDataAndType(mContext, intent, DATA_TYPE_AUDIO, file, false);
        mContext.startActivity(intent);
    }
}