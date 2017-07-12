package com.ucf.first.ao;

import java.util.Date;

public class NcfSetting {
    private Integer id;

    private String setName;

    private Integer createUser;

    private Date createTime;

    private String setValue;

    public NcfSetting(Integer id, String setName, Integer createUser, Date createTime, String setValue) {
        this.id = id;
        this.setName = setName;
        this.createUser = createUser;
        this.createTime = createTime;
        this.setValue = setValue;
    }

    public Integer getId() {
        return id;
    }

    public String getSetName() {
        return setName;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getSetValue() {
        return setValue;
    }

    public void setId(Integer id) {
        this.id=id;
    }

    public void setSetName(String setName) {
        this.setName=setName;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser=createUser;
    }

    public void setCreateTime(Date createTime) {
        this.createTime=createTime;
    }

    public void setSetValue(String setValue) {
        this.setValue=setValue;
    }

    public NcfSetting() {
        super();
    }
}