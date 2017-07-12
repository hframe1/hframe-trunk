package com.hframe.controller.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理结果返回对象
 * Created by zhangqh6
 */
    public class ResultMessage {

    private boolean success = false;

    private String retMsg;

    private String retCode;


    private List<String> content = new ArrayList<String>();

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
