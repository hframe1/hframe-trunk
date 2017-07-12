package com.hframework.reconciliation.core;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangquanhong on 2016/5/4.
 */
public abstract class AbstractWriter implements Writer{

    protected HostHolder hostHolder;
    protected String scope;
    protected String dataItem;
    protected List<String> dataItems;
    protected String originId;
    protected String targetId;
    ServiceContext context;

    public AbstractWriter(ServiceContext context, HostHolder hostHolder, String scope, String dataItem, String originId, String targetId) {
        this.context = context;
        this.hostHolder = hostHolder;
        this.scope = scope;
        if(this.scope == null) {
            this.scope = SCOPE.diff.name();
        }
        this.dataItem = dataItem;
        this.originId = originId;
        this.targetId = targetId;
        String[] items = dataItem.split(",");
        dataItems = Arrays.asList(items);

    }

    public enum SCOPE {
        all,diff,self,target
    }
    public enum RESULT {
        SAME(0,""),DIFF(1,"不等"),SINGLE(2,"多余");
        private int code;
        private String description;
        RESULT(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

    }

}
