package com.hframework.reconciliation.core;

import com.hframework.common.util.StringUtils;
import com.hframework.reconciliation.bean.RData;
import com.hframework.reconciliation.bean.config.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangquanhong on 2016/5/4.
 */
public class ExporterHandler  extends DefaultLifeCycle implements LifeCycle{

    private Exporter exporter;
    private HostHolder hostHolder;
    private Writer writer;
    private ServiceContext serviceContext;


    public ExporterHandler(Exporter exporter, ServiceContext context) {
        super(context.getServerContext());
        serviceContext = context;
        this.exporter = exporter;
        String hostId = exporter.getHost().getRel();
        hostHolder = context.getServerContext().getHostHolder(hostId);
        File file = exporter.getFile();
        if(file != null) {
            writer = new FileWriter(context,hostHolder,file, exporter.getScope(),exporter.getDataitem(),exporter.getLeftId(),exporter.getRightId());
        }
        String sql = exporter.getSql();
        if(sql != null) {
            writer = new DBWriter(context,hostHolder, sql, exporter.getScope(),exporter.getDataitem(),exporter.getLeftId(),exporter.getRightId());
        }
    }


    public int exportData(Map<String, RData> leftData, Map<String, RData> rightData){
        if(StringUtils.isNotBlank(exporter.getLeftId())) {
            return writer.write(leftData.get(exporter.getLeftId()));
        }else if(StringUtils.isNotBlank(exporter.getRightId())) {
            return writer.write(rightData.get(exporter.getRightId()));
        }else {
            //如果没有设置获取的数据ID，传null
            return writer.write(null);
        }
    }

    public void init() {

    }

    public void start() {

    }

    public void stop() {

    }

    public void suspend() {

    }

    public void resumed() {

    }

    public void execute() {

    }

    public Exporter getExporter() {
        return exporter;
    }

    public void setExporter(Exporter exporter) {
        this.exporter = exporter;
    }
}
