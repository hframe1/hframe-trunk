package com.hframework.generator.bean;

/**
 * Created by zhangquanhong on 2016/4/19.
 */
public abstract class AbstractGenerateDescriptor implements GenerateDescriptor {
    private String javaPackage = "com.hframework.strategy.rule.repository";
    private String javaRootPath = "D:/my_workspace/hframe-trunk/hframe-target/src/main/java/";
    private String templatePath = "com/hframework/generator/vm/bean.vm";

    public String getJavaPackage() {
        return javaPackage;
    }

    public void setJavaPackage(String javaPackage) {
        this.javaPackage = javaPackage;
    }

    public String getJavaRootPath() {
        return javaRootPath;
    }

    public void setJavaRootPath(String javaRootPath) {
        this.javaRootPath = javaRootPath;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }
}
