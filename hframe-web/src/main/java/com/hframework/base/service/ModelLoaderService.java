package com.hframework.base.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.hframe.domain.model.*;
import com.hframe.service.interfaces.*;
import com.hframe.tag.bean.Option;
import com.hframe.tag.bean.ShowType;
import com.hframe.tag.util.ClassDeclaredUtils;
import com.hframework.beans.class0.Class;
import com.hframework.common.frame.ServiceFactory;
import com.hframework.common.frame.cache.CacheFactory;
import com.hframework.common.frame.cache.CacheKeyEnum;
import com.hframework.common.frame.cache.PropertyConfigurerUtils;
import com.hframework.common.util.FileUtils;
import com.hframework.common.util.StringUtils;
import com.hframework.common.util.message.XmlUtils;
import com.hframework.generator.web.bean.HfModelContainer;
import com.hframework.generator.web.bean.HfModelContainerUtil;
import com.hframework.generator.web.sql.HfModelService;
import com.hframework.generator.web.sql.SqlGeneratorUtil;
import com.hframework.web.config.bean.DataSet;
import com.hframework.web.config.bean.dataset.Entity;
import com.hframework.web.config.bean.dataset.EnumClass;
import com.hframework.web.config.bean.dataset.Fields;
import com.hframework.web.config.bean.dataset.Rel;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.*;

/**
 * Created by zhangquanhong on 2016/7/3.
 */
@Service()
public class ModelLoaderService {

    public String load(ServletContext servletContext) {

        String programCode = "hframe";
        String programeName = "框架";
        String moduleCode = "hframe";
        String moduleName = "框架";
        try {
            if(servletContext != null) {
                WebApplicationContext webappcontext = WebApplicationContextUtils
                        .getRequiredWebApplicationContext(servletContext);
                ServiceFactory.initContext(webappcontext);
            }

            load("hframe", programCode, programeName, moduleCode, moduleName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String load(String companyCode, String programCode, String programeName,String  moduleCode,String  moduleName) {
        try {
            HfModelContainer dbModelContainer = HfModelService.get().getModelContainerFromDB(
                    programCode, programeName, moduleCode, moduleName);

            HfModelContainer[] resultModelContainers =
                    HfModelContainerUtil.mergerModelContainer(HfModelContainerUtil.getInstance(), dbModelContainer);
            System.out.println("==>" + JSONObject.toJSONString(resultModelContainers));
            List<String> sql = HfModelContainerUtil.getSql(resultModelContainers[0], resultModelContainers[1]);
            String sqlFilePath = SqlGeneratorUtil.createSqlFile(companyCode, programCode, Joiner.on("\n\n\n").join(sql));
            System.out.println(" => fileName" +sqlFilePath);
            return sqlFilePath;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
