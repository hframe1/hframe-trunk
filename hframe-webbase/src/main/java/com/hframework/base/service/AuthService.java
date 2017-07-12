package com.hframework.base.service;

import com.hframework.base.bean.AuthContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhangquanhong on 2016/11/7.
 */
public interface AuthService {

    public AuthContext initAuthContext(HttpServletRequest request) throws Exception;

    public List<Long> getFunctionIds(HttpServletRequest request) throws Exception;

    public AuthContext getAuthContext(HttpServletRequest request) throws Exception;
}
