package com.liuliuzhu.autojs;

import android.app.Application;
import android.util.Log;

import com.liuliuzhu.autojs.utils.HookUtils;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


/**
 * @author 溜溜猪
 * @date 2020/05/23
 * @desc 微信公众号：AI小子
 */
public class Hook extends Application implements IXposedHookLoadPackage, Config {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        final Class<?> clazz = XposedHelpers.findClass(
                "com.stardust.autojs.engine.encryption.ScriptEncryption",
                loadPackageParam.classLoader);

        XposedHelpers.findAndHookMethod(
                clazz,
                "decrypt",
                byte[].class,
                int.class,
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        // 拿到的解密数据
                        String str = HookUtils.bytesToString((byte[]) param.getResult());
                        HookUtils.strToFile(str, FILEPATH);
                    }
                });
    }
}
