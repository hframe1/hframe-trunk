package com.hframework.reconciliation.core;

import com.hframework.reconciliation.bean.RData;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangquanhong on 2016/5/3.
 */
public abstract class DefaultLifeCycle implements LifeCycle {

    protected ServerContext context;

    public DefaultLifeCycle(ServerContext context){
        this.context = context;
    }

}
