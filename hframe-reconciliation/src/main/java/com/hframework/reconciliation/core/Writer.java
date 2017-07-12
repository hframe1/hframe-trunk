package com.hframework.reconciliation.core;

import com.hframework.reconciliation.bean.RData;

import java.util.Map;

/**
 * Created by zhangquanhong on 2016/5/4.
 */
public interface Writer {

    public  int  write(RData rData);

//    public RData readAll();
}
