package com.hframework.reconciliation.core;

import com.google.common.base.Enums;
import com.hframework.common.util.DateUtils;
import com.hframework.common.util.StringUtils;
import com.hframework.reconciliation.bean.GlobalConst;
import com.hframework.reconciliation.bean.RData;
import com.hframework.reconciliation.bean.config.*;
import com.hframework.reconciliation.enums.BalanceStateEnum;
import com.hframework.reconciliation.enums.CompareStateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * Created by zhangquanhong on 2016/5/3.
 */
public class ServiceProcessor extends DefaultLifeCycle implements LifeCycle, Processable{

    private static final Logger logger = LoggerFactory.getLogger(ServiceProcessor.class);

    private Service service;
    private List<ImporterHandler> importerHandlers;
    private List<ExporterHandler> exporterHandlers;
    private List<Rule> rules;

    //当前服务上下文
    private ServiceContext serviceContext;


    private Trigger trigger ;



    public ServiceProcessor(Service service, ServerContext context){
        super(context);
        this.service = service;
        serviceContext =  new ServiceContext(context);
        importerHandlers = new ArrayList<ImporterHandler>();
        exporterHandlers = new ArrayList<ExporterHandler>();

        List<Importer> importers = service.getImporters().getImporterList();
        for (Importer importer : importers) {
            ImporterHandler importerHandler = new ImporterHandler(importer, serviceContext);
            importerHandlers.add(importerHandler);
        }

        List<Exporter> exporterList = service.getExporters().getExporterList();
        for (Exporter exporter : exporterList) {
            exporterHandlers.add(new ExporterHandler(exporter,serviceContext));
        }

        this.rules = service.getRuleCenter().getRuleList();
        this.trigger = service.getTrigger();


    }


    public void init() {
        for (ImporterHandler importerHandler : importerHandlers) {
            importerHandler.init();
        }
    }

    public void start() {
        //TODO 转化为时钟进行处理
        while (true) {
            try {
                fixedProcess();
                try {
                    process();
                } catch (Exception e) {
                    logger.error("处理失败！", e);
                }
                Thread.sleep(1000 * 60 * 3);
            } catch (InterruptedException e) {
                logger.error("error", e);
            }
        }
    }

    public void fixedProcess() {
        Stack<String[]> preProcessStack = serviceContext.getPreProcessStack();
        while (preProcessStack != null && !preProcessStack.empty()) {
            try {
                process();
            } catch (Exception e) {
                logger.error("error:", e);
            }
        }

    }

    public void stop() {

    }

    public void suspend() {

    }

    public void resumed() {

    }

    public void process() throws Exception {
        logger.info("服务{}开始处理..",service.getId());
        serviceContext.fetchNewContext();
        Map<String, RData> leftData = null;
        Map<String, RData> rightData = null;
        try {

            if(importerHandlers.get(0).isLeftImporter()) {
                leftData = importerHandlers.get(0).read();
                rightData = importerHandlers.get(1).read();
            }else {
                leftData = importerHandlers.get(1).read();
                rightData = importerHandlers.get(0).read();
            }
        } catch (Exception e) {
            logger.error("error:",e);
            throw e;
        }

        DataComparator.compare(leftData, rightData, rules);

        setGlobalInfo(leftData,rightData);


        for (ExporterHandler exporterHandler : exporterHandlers) {
            int effectCount = exporterHandler.exportData(leftData, rightData);
            String type = exporterHandler.getExporter().getType();
            logger.debug("{}",type, effectCount);
            if(StringUtils.isNotBlank(type) && "RIT".equals(type.trim().toUpperCase()) && effectCount > 0) {
                break;
            }
        }

        logger.info("服务{}处理结束..",service.getId());
    }

    private void setGlobalInfo(Map<String, RData> baseData, Map<String, RData> otherData) {
        RData otherData1 = null;
        for (RData data : otherData.values()) {
            if (data.getOriginalData() != null) {
                otherData1 = data;
            }
        }

        for (RData rData : baseData.values()) {
            if(rData.getOriginalData() != null) {
                //全局信息
                Map<String, String> globalInfo = new HashMap<String, String>();
                globalInfo.put(GlobalConst.BATCH_ID,serviceContext.getBatchId());
                globalInfo.put(GlobalConst.COMPARE_STATE, String.valueOf(rData.isDiff() ?
                        CompareStateEnum.FAIL.getId() : CompareStateEnum.SUCCESS.getId()));
                globalInfo.put(GlobalConst.DIFF_RECORD_COUNT,rData.getSameKeyDiffList().size() + "");
                globalInfo.put(GlobalConst.SAME_RECORD_COUNT,rData.getSameDataList().size() + "");
                globalInfo.put(GlobalConst.SELF_SINGLE_COUNT, rData.getCompareDiffData().size() + "");
                globalInfo.put(GlobalConst.THIRD_SINGLE_COUNT, otherData1.getCompareDiffData().size() + "");
                globalInfo.put(GlobalConst.BALANCE_STATE,String.valueOf(rData.isDiff() ?
                        BalanceStateEnum.UNDO.getId() : BalanceStateEnum.NONE.getId()));
                globalInfo.put(GlobalConst.COMPARE_DATETIME, serviceContext.getCurDate());
                globalInfo.put(GlobalConst.BALANCE_DATETIME,rData.isDiff()? null : serviceContext.getCurDate());
                serviceContext.setGlobalInfo(globalInfo);
            }
        }
    }

    public enum RuleType{
        RIT
    }

    public static  class DataComparator {

        public static void compare(Map<String, RData> leftData, Map<String, RData> rightData ,List<Rule> rules) {
            logger.debug("比对：{}|{}|{}",leftData, rightData, rules);
            boolean result = true;
            for (Rule rule : rules) {
                if(rule.getType() != null && Enums.getIfPresent(RuleType.class,rule.getType()).get() == RuleType.RIT) {
                    if(result) {
                        break;
                    }else {
                        continue;
                    }
                }
                String originId = rule.getLeftId();
                String targetId = rule.getRightId();
                List<Datanode> datanodeList = rule.getDatanodeList();
                result = compare(leftData.get(originId), rightData.get(targetId),datanodeList);
            }
        }

        private static boolean compare(RData leftData, RData rightData, List<Datanode> dataNodeList) {
            logger.debug("比对：{}|{}|{}", leftData, rightData, dataNodeList);
            if(leftData.getOriginalData() == null && rightData.getOriginalData() == null) {
                if(!leftData.getSingleValue().equals(rightData.getSingleValue())) {
                    leftData.setDiffDesc("目标值为：" + rightData.getSingleValue());
                    rightData.setDiffDesc("目标值为：" + leftData.getSingleValue());
                }
            }else {
                List<String> leftStringList = leftData.setDataNodeAndReturnCompareDataString(dataNodeList);
                List<String> rightStringList = rightData.setDataNodeAndReturnCompareDataString(dataNodeList);

                List<String> sameList = new ArrayList<String>(leftStringList);
                sameList.retainAll(rightStringList);

                leftStringList = leftData.setSameAndReturnDiffData(sameList);
                rightStringList = rightData.setSameAndReturnDiffData(sameList);


                List<String> sameKeyDiffList = new ArrayList<String>(leftStringList);
                sameKeyDiffList.retainAll(rightStringList);

                leftData.setSameKeyDiffDataList(sameKeyDiffList);
                rightData.setSameKeyDiffDataList(sameKeyDiffList);
            }

            logger.debug("比对结果：{}|{}|{}|{}", !(leftData.isDiff() || rightData.isDiff()),leftData, rightData, dataNodeList);

            if(leftData.isDiff() || rightData.isDiff()) {
                return false;
            }
            return true;
        }

    }

    public static void main(String[] args) {
        List list1 =new ArrayList();
        list1.add("1111");
        list1.add("2222");
        list1.add("3333");

        List list2 =new ArrayList();
        list2.add("3333");
        list2.add("4444");
        list2.add("5555");

        //并集
        //list1.addAll(list2);
        //交集
//        list1.retainAll(list2);
        //差集
        list1.removeAll(list2);


        list1.removeAll(list2);

        //无重复并集
//        list2.removeAll(list1);
//        list1.addAll(list2);

        Iterator<String> it=list1.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }

        //System.out.println("-----------------------------------\n");
        //printStr(list1);

    }

    public static void printStr(List list1){
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
