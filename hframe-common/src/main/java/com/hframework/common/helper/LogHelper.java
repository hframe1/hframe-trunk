package com.hframework.common.helper;


import java.util.Arrays;

/**
 * User: zhangqh6
 * Date: 2016/3/18 14:24:24
 */
public class LogHelper {

    public static String begin(String TagName, Object... objects) {
        return TagName + " : " + Arrays.toString(objects) + " , begin！";
    }

    public static String check(String TagName, Object... objects) {
        return TagName + " : " + Arrays.toString(objects) + " , 检查不通过！";
    }

    public static  String error(String TagName, Throwable t ,Object... objects) {
        return TagName+ " : " + Arrays.toString(objects)   + " 异常 : " + t ;
    }


    public static String end(String TagName, Object... objects) {
        return TagName + " : " + Arrays.toString(objects) + " , end！";
    }
}
