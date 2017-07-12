package com.hframework.reconciliation.enums;

/**
 * Created by zhangquanhong on 2016/5/13.
 */
public enum CompareStateEnum {

    SUCCESS(1,"对账成功"),FAIL(2,"对账失败"),ERROR(9,"对账异常");

    private int id;
    private String name;

    CompareStateEnum(int id, String name) {
        this.id =id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
