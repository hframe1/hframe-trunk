package com.hframework.web;

import com.hframework.beans.class0.Class;
import com.hframework.common.frame.cache.PropertyConfigurerUtils;
import com.hframework.common.util.ResourceWrapper;
import com.hframework.common.util.StringUtils;

/**
 * Created by zhangquanhong on 2016/6/27.
 */
public class CreatorUtil {
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

    public static com.hframework.beans.class0.Class getDefPoExampleClass(String companyName,
                                                                         String projectName,String moduleName, String tableName) throws Exception {
        if(StringUtils.isBlank(tableName)) {
            throw new Exception("表名称为不能为空！");
        }

        companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
        projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
        moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);


        Class class1 = new Class();
        class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
        class1.setClassPackage(CreatorUtil.getPoClassPackage(
                companyName, projectName, moduleName, tableName));
        class1.setClassName(ResourceWrapper.JavaUtil.getJavaClassName(tableName) + "_Example");
        return class1;
    }
//    public static Class getDefServiceClass(String companyName,
//                                           String projectName, String moduleName,String tableName) throws Exception {
//        if(StringUtils.isBlank(tableName)) {
//            throw new Exception("表名称为不能为空！");
//        }
//
//        companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
//        projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
//        moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);
//
//        Class class1 = new Class();
//        class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
//        class1.setClassPackage(CreatorUtil.getServiceClassPackage(
//                companyName, projectName, moduleName, tableName));
//        class1.setClassName("I" + ResourceWrapper.JavaUtil.getJavaClassName(tableName) + "SV");
//        return class1;
//    }
//
//    public static String getServiceClassPackage(String companyName,
//                                                String projectName,String moduleName, String tableName) throws Exception {
//
//        return PropertyConfigurerUtils.getProperty(
//                "service_class_package",
//                companyName.toLowerCase(),
//                projectName.toLowerCase(),
//                moduleName.toLowerCase());
//    }

    public static Class getDefServiceImplClass(String companyName,
                                               String projectName, String moduleName,String tableName) throws Exception {
        if(StringUtils.isBlank(tableName)) {
            throw new Exception("表名称为不能为空！");
        }

        companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
        projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
        moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);

        Class class1 = new Class();
        class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
        class1.setClassPackage(CreatorUtil.getServiceImplClassPackage(
                companyName, projectName, moduleName, tableName));
        class1.setClassName(ResourceWrapper.JavaUtil.getJavaClassName(tableName) + "SVImpl");
        return class1;
    }
    public static Class getDefControllerClass(String companyName,
                                              String projectName, String moduleName,String tableName) throws Exception {

        if(StringUtils.isBlank(tableName)) {
            throw new Exception("表名称为不能为空！");
        }

        companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
        projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
        moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);

        Class class1 = new Class();
        class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
        class1.setClassPackage(CreatorUtil.getActionClassPackage(
                companyName, projectName, moduleName, tableName));
        class1.setClassName(ResourceWrapper.JavaUtil.getJavaClassName(tableName) + "Controller");
        return class1;
    }

    public static String getActionClassPackage(String companyName,
                                               String projectName,String moduleName,String tableName) throws Exception {

        return PropertyConfigurerUtils.getProperty(
                "action_class_package",
                companyName.toLowerCase(),
                projectName.toLowerCase(),
                moduleName.toLowerCase());
    }
    public static String getServiceImplClassPackage(String companyName,
                                                    String projectName,String moduleName, String tableName) throws Exception {

        return PropertyConfigurerUtils.getProperty(
                "serviceimpl_class_package",
                companyName.toLowerCase(),
                projectName.toLowerCase(),
                moduleName.toLowerCase());
    }

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


    /**
     * @param companyName
     * @param projectName
     * @return 获取SQL文件在项目中存放的路径即名称
     * @throws Exception
     */
    public static String getSrcFilePath(String companyName,
                                        String projectName) throws Exception {

        if("".equals(companyName) || companyName == null){
            companyName="zqh";
        }


        if(StringUtils.isBlank(projectName)) {
            throw new Exception("项目名称为不能为空！");
        }

        return PropertyConfigurerUtils.getProperty("project_src_file_path");
    }



    /**
     * @param companyName
     * @param projectName
     * @param tableName
     * @return 获取SQL文件在项目中存放的路径即名称
     * @throws Exception
     */
    public static String getPoClassPackage(String companyName,
                                           String projectName,String moduleName,String tableName) throws Exception {
        return PropertyConfigurerUtils.getProperty(
                "po_class_package",
                companyName.toLowerCase(),
                projectName.toLowerCase(),
                moduleName.toLowerCase(),
                getJavaClassName(tableName.toLowerCase()));
    }


    public static Class getDefPoClass(String companyName,
                                      String projectName, String moduleName,String tableName) throws Exception {
        if(StringUtils.isBlank(tableName)) {
            throw new Exception("表名称为不能为空！");
        }

        companyName = StringUtils.isBlank(companyName)?"":"."+(companyName);
        projectName = StringUtils.isBlank(projectName)?"":"."+(projectName);
        moduleName = StringUtils.isBlank(moduleName)?"":"."+(moduleName);


        Class class1 = new Class();
        class1.setSrcFilePath(CreatorUtil.getSrcFilePath(companyName, projectName));
        class1.setClassPackage(CreatorUtil.getPoClassPackage(
                companyName, projectName, moduleName,tableName));
        class1.setClassName(ResourceWrapper.JavaUtil.getJavaClassName(tableName) + "");
        return class1;
    }
}
