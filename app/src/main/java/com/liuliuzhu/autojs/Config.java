package com.liuliuzhu.autojs;

import android.os.Environment;

/**
 * @author 溜溜猪
 * @date 2020/05/23
 * @desc 微信公众号：AI小子
 */
public interface Config {
    String FILENAME = "autojs_sourcecode.js";
    String FILEDIR = Environment.getExternalStorageDirectory() + "/微信公众号_AI小子/";
    String FILEPATH = FILEDIR + FILENAME;
}
