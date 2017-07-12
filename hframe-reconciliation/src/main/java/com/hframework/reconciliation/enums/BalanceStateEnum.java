package com.hframework.reconciliation.enums;

/**
 * Created by zhangquanhong on 2016/5/13.
 */
public enum BalanceStateEnum {

    NONE(0,"无需平账"),UNDO(1,"未平账"),DOWN(2,"已平账"),ERROR(9,"平账异常");


    private int id;
    private String name;

    BalanceStateEnum(int id, String name) {
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
