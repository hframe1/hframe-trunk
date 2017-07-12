package com.hframe.tag.util;

import com.hframework.common.frame.cache.CacheFactory;
import com.hframework.common.frame.cache.CacheKeyEnum;

import java.util.Map;

/**
 * Created by zhangqh6 on 2015/10/20.
 */
public class ClassDeclaredUtils {

    public static String getJavaVarName(String tableName) {

        String returnName="";

        tableName=tableName.toLowerCase();

        String[] parts=tableName.split("[_]+");
        for (String part : parts) {
            if(!"".equals(part)){
                returnName+=part.substring(0,1).toUpperCase()+part.substring(1);
            }
        }

        return returnName.substring(0,1).toLowerCase()+returnName.substring(1);
    }

    public static String getJavaClassName(String tableName) {

        String returnName = "";

        tableName = tableName.toLowerCase();

        String[] parts = tableName.split("[_]+");
        for (String part : parts) {
            if (!"".equals(part)) {
                returnName += part.substring(0, 1).toUpperCase()
                        + part.substring(1);
            }
        }

        return returnName;
    }

    public static String getSetMethodFromColumnName(String name) {
        return "set"+name.substring(0,1).toUpperCase()+name.substring(2);
    }

    public static String getGetMethodFromColumnName(String columnName) {

        String methodName="get";

        String[] splits=columnName.toLowerCase().split("[_]+");
        for (String split : splits) {
            methodName+=split.substring(0,1).toUpperCase()+split.substring(1,split.length());
        }

        return methodName;
    }


    public static String getServiceNameFromTableName(String tableName) {
        return  "i" + ClassDeclaredUtils.getJavaClassName(tableName)+"SV";
    }

    public static String getPoExampleNameFromTableName(String tableName) {
        return  ClassDeclaredUtils.getJavaClassName(tableName)+"_Example";
    }

    public static Class<?> getPoExampleObjFromTableName(String tableName) throws ClassNotFoundException {
        String poExampleName = getPoExampleNameFromTableName(tableName);
        //获取获取视图缓存对象
        Map<String,Object> map = (Map<String, Object>) CacheFactory.get(CacheKeyEnum.DS_CREATE_CACHE.getName(), tableName);
        String poClassPath = ((String)map.get("PoPath"));
//        Map map= SetCacheFactory.get(tableName, "table");
//        String poClassPath = ((String)map.get("PoPath"));
        String poExampleClassPath = poClassPath.substring(0,poClassPath.lastIndexOf(".")+1)+poExampleName;
        return  Class.forName(poExampleClassPath);
    }



//    public static String getClassNameFromDBObject(String dbObject){
//        //TODO
//        return User.class.toString();
//    }

}
