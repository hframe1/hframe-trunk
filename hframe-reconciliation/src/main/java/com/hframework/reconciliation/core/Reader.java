package com.hframework.reconciliation.core;

import com.hframework.reconciliation.bean.RData;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangquanhong on 2016/5/4.
 */
public interface Reader {

    public  Map<String,RData>  read() throws Exception;

//    public RData readAll();
}
