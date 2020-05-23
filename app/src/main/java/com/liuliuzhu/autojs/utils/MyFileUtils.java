package com.liuliuzhu.autojs.utils;

import java.io.File;

/**
 * @author 溜溜猪
 * @date 2020/05/23
 * @desc 微信公众号：AI小子
 */
public class MyFileUtils {

    public static Boolean isFile(String path) {
        File file = new File(path);
        return file.isFile();
    }
}
