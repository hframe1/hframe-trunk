package com.hframework.reconciliation.core;

import com.hframework.reconciliation.bean.GlobalConst;
import com.hframework.reconciliation.bean.RData;
import com.hframework.reconciliation.bean.config.Sql;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangquanhong on 2016/5/4.
 */
public class DBReader extends AbstractReader implements Reader {
    private static final Logger logger = LoggerFactory.getLogger(DBReader.class);

    private List<Sql> sqlList = null;
    NamedParameterJdbcTemplate jdbcTemplate = null;
    boolean isLeftData;

    public DBReader(ServiceContext context, HostHolder hostHolder, List<Sql> sqlList, boolean isLeftData) {
        super(context, hostHolder);
        this.sqlList = sqlList;
        jdbcTemplate = hostHolder.getJdbcTemplate();
        this.isLeftData = isLeftData;
    }


    public  Map<String,RData>  read() {

        Map<String,RData> dataMap = new HashMap<String, RData>();
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(GlobalConst.START_DATE, context.getStartDate());
        paramMap.put(GlobalConst.END_DATE,context.getEndDate());
        for (Sql sql : sqlList) {
            logger.debug("数据查询:{}|{}|{}",sql.getId(), sql.getText(), paramMap);
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql.getText(), paramMap);
            logger.debug("数据查询结果:{}条|{}",maps.size(),maps);
            if(maps != null && maps.size() == 1 && maps.get(0).size() == 1) {
                dataMap.put(sql.getId(), new RData(String.valueOf(maps.get(0).values().iterator().next()), isLeftData));
            }else {
                dataMap.put(sql.getId(), new RData(jdbcTemplate.queryForList(sql.getText(),paramMap),isLeftData));
            }
        }

        return dataMap;
    }
}
