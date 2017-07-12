package com.hframework.reconciliation.core;

import com.hframework.common.util.message.XmlUtils;
import com.hframework.reconciliation.bean.Config;
import com.hframework.reconciliation.bean.config.Host;
import com.hframework.reconciliation.bean.config.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by zhangquanhong on 2016/5/3.
 */
public class Server implements LifeCycle{
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    private ServerContext context = null;

    private static final String RECONCILIATION_ROOT_PATH = "reconciliation" + "/";

    public static void main(String[] args) {
        logger.info("Main程序启动：{}",args);
        Server server = new Server();
        server.load(args != null && args.length > 0? args[1] : "ucfpayorder.xml",
                args != null && args.length > 1 ? args[1] : null,
                args != null && args.length > 2 ? args[2] : null);
        server.start();
    }

    public void load(String configName, String startDate, String endDate) {
        logger.info("进程加载：{}",configName,startDate,endDate);
        try {
            Config config = XmlUtils.readValueFromFile(RECONCILIATION_ROOT_PATH + configName, Config.class);
            List<Host> hosts = config.getGlobal().getHostList();
            List<Service> serviceList = config.getServices().getServiceList();

            context = new ServerContext();
            context.loadHosts(hosts);

            context.loadServices(serviceList);

            logger.info("进程加载完毕！");
        } catch (IOException e) {
            logger.error("进程加载出错", e);
        }

    }

    public void init() {

    }

    public void start() {
        logger.info("进程启动");
        List<ServiceProcessor> serviceProcessors = context.getServiceProcessors();
        for (ServiceProcessor serviceProcessor : serviceProcessors) {
            logger.info("启动服务：{}",serviceProcessor.getService().getId(), serviceProcessor);
            serviceProcessor.start();
        }
    }

    public void stop() {

    }

    public void suspend() {

    }

    public void resumed() {

    }

    public void process() {
        logger.info("进程开始处理");
        List<ServiceProcessor> serviceProcessors = context.getServiceProcessors();
        for (ServiceProcessor serviceProcessor : serviceProcessors) {
            logger.info("启动服务：{}", serviceProcessor.getService().getId(), serviceProcessor);
            serviceProcessor.fixedProcess();
            try {
                serviceProcessor.process();
            } catch (Exception e) {
                logger.error("处理失败！", e);
            }
        }
    }
}
