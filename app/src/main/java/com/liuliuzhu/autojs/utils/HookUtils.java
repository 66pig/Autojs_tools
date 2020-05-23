package com.liuliuzhu.autojs.utils;

import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


/**
 * @author 溜溜猪
 * @date 2020/05/23
 * @desc 微信公众号：AI小子
 */
public abstract class HookUtils {

    /**
     * 将字节数组转为字符串
     * @param bs: 字节数组
     * @return
     */
    public static String bytesToString(byte[] bs) {
        try {
            // 通过指定的字符集解码指定的byte数组并构造一个新的字符串
            return new String(bs, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串写入文件并保存
     * @param data: 脚本数据文件
     */
    public static void strToFile(String data, String filePath) {
        String path = filePath.substring(0, filePath.lastIndexOf("/"));
        File destDir = new File(path);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter(filePath);
            fwriter.write(data);
        } catch (IOException ex) {
            ex.printStackTrace();
            Log.d("溜溜猪提示", ex.toString());
        } finally {
            if (fwriter != null)
                try {
                    fwriter.flush();
                    fwriter.close();
                    Log.d("溜溜猪提示", "保存路径为：" + filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        }

    }
}
