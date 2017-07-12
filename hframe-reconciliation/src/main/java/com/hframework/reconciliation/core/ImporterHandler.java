package com.hframework.reconciliation.core;

import com.hframework.reconciliation.bean.RData;
import com.hframework.reconciliation.bean.config.Dataset;
import com.hframework.reconciliation.bean.config.File;
import com.hframework.reconciliation.bean.config.Importer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangquanhong on 2016/5/3.
 */
public class ImporterHandler extends DefaultLifeCycle implements LifeCycle{

    private Importer importer;
    private HostHolder hostHolder;
    private Reader reader;
    private ServiceContext serviceContext;


    public ImporterHandler(Importer importer, ServiceContext context) {
        super(context.getServerContext());
        this.serviceContext = context;
        this.importer = importer;
        String hostId = importer.getHostList().get(0).getRel();
        hostHolder = context.getServerContext().getHostHolder(hostId);
        Dataset dataset = importer.getDataset();
        boolean isLeftData = "left".equals(importer.getType())? true:false;
        if(dataset != null) {
            reader = new DBReader(context,hostHolder, dataset.getSqlList(),isLeftData);
        }
        File file = importer.getFile();
        if(file != null) {
            reader = new FileReader(context,hostHolder, file, isLeftData);
        }
    }

    public Map<String,RData> read() throws Exception {
        return reader.read();
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

    public boolean isLeftImporter() {
        return "left".equals(importer.getType())? true : false;
    }


}
