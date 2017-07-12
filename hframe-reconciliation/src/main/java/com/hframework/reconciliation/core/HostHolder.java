package com.hframework.reconciliation.core;

import com.google.common.base.Enums;
import com.hframework.common.util.FileUtils;
import com.hframework.common.util.FtpClient;
import com.hframework.reconciliation.bean.config.Datasource;
import com.hframework.reconciliation.bean.config.File;
import com.hframework.reconciliation.bean.config.Host;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangquanhong on 2016/5/3.
 */
public class HostHolder {
    private static final Logger logger =
            LoggerFactory.getLogger(HostHolder.class);

    private Host host;
    private DataSourceType type;

    private NamedParameterJdbcTemplate jdbcTemplate;

    private FtpTemplate ftpTemplate;

    public HostHolder(Host host) {
        this.host = host;

        Datasource datasource = host.getDatasource();
        type = Enums.getIfPresent(DataSourceType.class, datasource.getType()).get();
        if(type == DataSourceType.ftp) {
            ftpTemplate = new FtpTemplate();
        }else if(type == DataSourceType.db) {
            jdbcTemplate = new NamedParameterJdbcTemplate(initDataSource());
        }
    }

    private  DataSource initDataSource() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl(host.getDatasource().getText());
        cpds.setUser(host.getUsername());
        cpds.setPassword(host.getPassword());

        logger.info("DataSource firstp2p inited.|{}|{}|{}|{}", cpds.getJdbcUrl(), cpds.getUser(), cpds.getMaxPoolSize(),
                cpds.getProperties());
        return cpds;
    }


    public enum DataSourceType{
        ftp,db
    }

    public class FtpTemplate{

        public List<String> getContent(String filePath, String fileName, String tmpPath) throws Exception {
            FtpClient.download(host.getDatasource().getText(),21,host.getUsername(),host.getPassword(),filePath,fileName,tmpPath);
            return FileUtils.readFileToArray(tmpPath + java.io.File.separatorChar + fileName);
        }

        public String write(String content, String fileName, String filePath) {
            return null;
        }
    }

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public FtpTemplate getFtpTemplate() {
        return ftpTemplate;
    }

    public void setFtpTemplate(FtpTemplate ftpTemplate) {
        this.ftpTemplate = ftpTemplate;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public DataSourceType getType() {
        return type;
    }

    public void setType(DataSourceType type) {
        this.type = type;
    }
}
