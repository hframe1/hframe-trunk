package com.hframe.service.handler;

import com.hframe.domain.model.HfsecUser;
import com.hframework.base.bean.AbstractBusinessHandler;
import com.hframework.base.bean.BusinessHandler;
import com.hframework.common.annotation.extension.AfterCreateHandler;
import com.hframework.common.annotation.extension.AfterUpdateHandler;
import com.hframework.common.annotation.extension.BeforeDeleteHandler;
import com.hframework.common.annotation.extension.BeforeUpdateHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * Created by zhangquanhong on 2016/9/22.
 */
@Service
public class HfsecUserSVHandler extends AbstractBusinessHandler<HfsecUser> {

    @AfterCreateHandler
    public boolean afterCreate(HfsecUser hfsecUser) {
        System.out.println("===> create !");
        return false;
    }

    @BeforeUpdateHandler(attr = "status", orig = "1" , target = "0")
    public boolean afterUpdate(HfsecUser hfsecUser, HfsecUser origHfSecUser) {
        System.out.println("===> update !");
        return false;
    }

    @BeforeDeleteHandler
    public boolean afterDelete(HfsecUser hfsecUser) {
        System.out.println("===> delete !");
        return false;
    }

}
