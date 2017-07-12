package com.hframework.reconciliation.core;

/**
 * Created by zhangquanhong on 2016/5/3.
 */
public interface LifeCycle {

    public void init();

    public void start();

    public void stop();

    public void suspend();

    public void resumed();
}
