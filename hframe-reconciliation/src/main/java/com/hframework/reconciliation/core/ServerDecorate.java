package com.hframework.reconciliation.core;

import com.hframework.common.util.message.XmlUtils;
import com.hframework.reconciliation.bean.Config;
import com.hframework.reconciliation.bean.config.Host;
import com.hframework.reconciliation.bean.config.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.util.List;

/**
 * Created by zhangquanhong on 2016/5/3.
 */
public class ServerDecorate implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(ServerDecorate.class);
    private String configName;
    private String startDate;
    private String endDate;

    private Server server = new Server();


    public void process(){
        logger.info("process");
        server.process();
    }


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public void afterPropertiesSet() throws Exception {
        logger.info("{}",configName,startDate,endDate);
        server.load(configName,startDate,endDate);
    }
}
