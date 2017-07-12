package com.hframework.reconciliation.core;

import com.hframework.common.util.DateUtils;
import com.hframework.reconciliation.bean.config.Host;
import com.hframework.reconciliation.bean.config.Service;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by zhangquanhong on 2016/5/3.
 */
public class ServiceContext {

    private static final Logger logger = LoggerFactory.getLogger(ServerContext.class);

    private ServerContext serverContext;

    private List<ServiceInstContext> instContexts = new ArrayList<ServiceInstContext>();

    private ServiceInstContext instContext;

    private Stack<String[]> preProcessStack = null;

    public ServiceContext(ServerContext serverContext) {
        this.serverContext = serverContext;
        preProcessStack = serverContext.getPreProcessStack();
    }

    public boolean fetchNewContext(){
        instContext = new ServiceInstContext();
        instContexts.add(instContext);
        return true;
    }

    public ServerContext getServerContext() {
        return serverContext;
    }

    public void setServerContext(ServerContext serverContext) {
        this.serverContext = serverContext;
    }

    public String getBatchId() {
        return instContext.getBatchId();
    }

    public Date getCompareDate() {
        return DateUtils.parse(instContext.getBatchId(), DateUtils.YYYY_MM_DD);
    }


    public String getStartDate() {
        return instContext.getStartDate();
    }


    public String getEndDate() {
        return instContext.getEndDate();
    }

    public String getCurDate() {
        return instContext.getCurDate();
    }

    public Map<String, String> getGlobalInfo() {
        return instContext.getGlobalInfo();
    }

    public void setGlobalInfo(Map<String, String> globalInfo) {
        logger.debug("set:{}", globalInfo);
        instContext.setGlobalInfo(globalInfo);
    }


    public class ServiceInstContext{
        //对账批次号，根据对账的频率生成唯一的一个批次号
        private String batchId ;
        //同一个对账批次下数据检索开始时间
        private String startDate;
        //同一个对账批次下数据检索结束时间
        private String endDate;
        //当前时间
        private String curDate;
        //全局信息
        private Map<String, String> globalInfo;

        public ServiceInstContext() {
            if (preProcessStack != null && !preProcessStack.empty()) {
                String[] preProcess = preProcessStack.pop();
                batchId = preProcess[0];
                startDate = preProcess[1];
                endDate = preProcess[2];
            }

            if(StringUtils.isBlank(batchId)) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                batchId = DateUtils.getDateYYYYMMDD(calendar.getTime());
                startDate = DateUtils.getDateYYYYMMDD(calendar.getTime()) + " 00:00:00";
                endDate = DateUtils.getDateYYYYMMDD(calendar.getTime()) + " 23:59:59";
            }
            curDate = DateUtils.getCurrentDateYYYYMMDDHHMMSS();
            logger.info("create new ServiceInstContext:{}",batchId, startDate, endDate, curDate);

        }

        public String getBatchId() {
            return batchId;
        }

        public void setBatchId(String batchId) {
            this.batchId = batchId;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getCurDate() {
            return curDate;
        }

        public void setCurDate(String curDate) {
            this.curDate = curDate;
        }

        public Map<String, String> getGlobalInfo() {
            return globalInfo;
        }

        public void setGlobalInfo(Map<String, String> globalInfo) {
            this.globalInfo = globalInfo;
        }
    }

    public Stack<String[]> getPreProcessStack() {
        return preProcessStack;
    }

    public void setPreProcessStack(Stack<String[]> preProcessStack) {
        this.preProcessStack = preProcessStack;
    }
}
