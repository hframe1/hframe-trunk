package com.hframework.reconciliation.core;

/**
 * Created by zhangquanhong on 2016/5/4.
 */
public abstract class AbstractReader implements Reader{

    protected HostHolder hostHolder;

    protected ServiceContext context;

    public AbstractReader(ServiceContext context, HostHolder hostHolder) {
        this.context = context;
        this.hostHolder = hostHolder;
    }

    public HostHolder getHostHolder() {
        return hostHolder;
    }

    public void setHostHolder(HostHolder hostHolder) {
        this.hostHolder = hostHolder;
    }
}
