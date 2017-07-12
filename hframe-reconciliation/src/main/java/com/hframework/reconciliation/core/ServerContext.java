package com.hframework.reconciliation.core;

import com.hframework.common.util.DateUtils;
import com.hframework.common.util.StringUtils;
import com.hframework.reconciliation.bean.config.Host;
import com.hframework.reconciliation.bean.config.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by zhangquanhong on 2016/5/3.
 */
public class ServerContext {

    private static final Logger logger = LoggerFactory.getLogger(ServerContext.class);

    private Map<String, HostHolder> hostMap = new HashMap<String, HostHolder>();
    private Map<String, ServiceProcessor> serviceMap = new HashMap<String, ServiceProcessor>();


    private Stack<String[]> preProcessStack;

    //对账批次号，根据对账的频率生成唯一的一个批次号
    private String batchId ;
    //同一个对账批次下数据检索开始时间
    private String startDate;
    //同一个对账批次下数据检索结束时间
    private String endDate;

    public void loadHosts(List<Host> hosts) {
        logger.info("加载主机：{}",hosts);
        if(hosts != null) {
            for (Host host : hosts) {
                hostMap.put(host.getId(),new HostHolder(host));
            }
        }
    }

    public void loadServices(List<Service> services) {
        logger.info("加载服务：{}",services);
        if(services != null) {
            for (Service service : services) {
                ServiceProcessor serviceProcessor = new ServiceProcessor(service, this);
                serviceMap.put(service.getId(),serviceProcessor);
            }
        }

    }

    public List<ServiceProcessor> getServiceProcessors(){
        return new ArrayList<ServiceProcessor>(serviceMap.values());
    }

    public HostHolder getHostHolder(String hostId) {
        return hostMap.get(hostId);
    }

    public Map<String, ServiceProcessor> getServiceMap() {
        return serviceMap;
    }

    public void setServiceMap(Map<String, ServiceProcessor> serviceMap) {
        this.serviceMap = serviceMap;
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

    public Stack<String[]> getPreProcessStack() {
        return preProcessStack;
    }

    public void setPreProcessStack(Stack<String[]> preProcessStack) {
        this.preProcessStack = preProcessStack;
    }

    public void setPreProcessStack(String startDateStr, String endDateStr) {
        if(StringUtils.isNotBlank(startDateStr)) {
            Date startDate = DateUtils.parse(startDateStr, DateUtils.YYYY_MM_DD);
            Date endDate = startDate;
            if(StringUtils.isNotBlank(startDateStr)) {
                endDate = DateUtils.parse(endDateStr, DateUtils.YYYY_MM_DD);
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);

            this.preProcessStack = new Stack<String[]>();
            while (calendar.getTime().compareTo(endDate) <= 0) {
                preProcessStack.add(new String[]{DateUtils.getDateYYYYMMDD(calendar.getTime()),
                        DateUtils.getDateYYYYMMDD(calendar.getTime()) + " 00:00:00",
                        DateUtils.getDateYYYYMMDD(calendar.getTime()) + " 23:59:59"});
                calendar.add(Calendar.DAY_OF_YEAR, 1);

            }

            logger.debug("添加预处理信息：{}", preProcessStack);

        }


    }
}
