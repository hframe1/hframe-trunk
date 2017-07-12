package com.hframework.reconciliation.core;

import com.google.common.base.Enums;
import com.google.common.collect.Lists;
import com.hframework.common.util.DateUtils;
import com.hframework.common.util.RegexUtils;
import com.hframework.reconciliation.bean.GlobalConst;
import com.hframework.reconciliation.bean.RData;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by zhangquanhong on 2016/5/4.
 */
public class DBWriter extends AbstractWriter implements Writer {

    private static final Logger logger = LoggerFactory.getLogger(DBWriter.class);

    private String sql = null;
    NamedParameterJdbcTemplate jdbcTemplate = null;

    public DBWriter(ServiceContext context, HostHolder hostHolder, String sql, String scope, String dataItem, String originId, String targetId) {
        super(context, hostHolder, scope, dataItem, originId, targetId);
        this.sql = sql;
        jdbcTemplate = hostHolder.getJdbcTemplate();
    }

    public int write(RData rData) {
        logger.debug("write info : {}", rData);

        StringBuffer sb = new StringBuffer();
        SCOPE scopeEnum = Enums.getIfPresent(SCOPE.class, scope).get();

        int count = 0;
        //如果仅有汇总信息，只写一条
        if(onlyStatistics()){
            count += write(Arrays.asList(new String[]{""}), new HashMap<String, Map<String, Object>>(), RESULT.SINGLE, dataItems, sb);
        }else {
            count += write(rData.getCompareDiffData(), rData.getDiffDataCacheMap(), RESULT.SINGLE, dataItems, sb);
            count += write(rData.getSameKeyDiffList(), rData.getDiffDataCacheMap(), RESULT.DIFF, dataItems, sb);
            if(scopeEnum == SCOPE.all) {
                count +=  write(rData.getSameDataList(), rData.getCompareDataCacheMap(), RESULT.SAME, dataItems, sb);
            }
        }
        return count;
    }


    private int write(final List<String> keyList, final Map<String, Map<String, Object>> dataMap, final RESULT result,
                                    final List<String> dataItem, StringBuffer sb) {

        logger.debug("write info : {}|{}|{}|{}", keyList,dataMap, result, dataItem);

        if(sql.trim().toUpperCase().startsWith("SELECT")) {
            List params = new ArrayList();
            for (int j = 0; j < dataItem.size(); j++) {
                String[] strings = RegexUtils.find(dataItem.get(j), "[\\$]\\{[ a-zA-Z:0-9_]+\\}");
                if (strings != null && strings.length > 0) {
                    String paramName = strings[0].substring(2, strings[0].length() - 1);
                    params.add(context.getGlobalInfo().get(paramName));
                } else {
                    if (dataItem.get(j).endsWith("]") && dataItem.get(j).startsWith("[")) {
                        params.add(dataItem.get(j).substring(1, dataItem.get(j).length() - 1).trim());
                    }
                }
            }

            Boolean exists = (Boolean) jdbcTemplate.getJdbcOperations().query(sql, params.toArray(new String[0]), new ResultSetExtractor() {
                public Boolean extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                    return resultSet.next();
                }
            });
            logger.debug("db query: {}|{}|{}", sql, params, exists);
            return exists ? 1 : 0;
        }


        int[] ints = jdbcTemplate.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter() {
            public int getBatchSize() {
                //这个方法设定更新记录数，通常List里面存放的都是我们要更新的，所以返回list.size();
                return keyList.size();
            }

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                String rowKey = keyList.get(i);
                Map<String, Object> map = dataMap.get(rowKey);

                for (int j = 0; j < dataItem.size(); j++) {
                    if (("${" + GlobalConst.RECORD_STATUS + "}").equals(dataItem.get(j))) {
                        ps.setString(j + 1, String.valueOf(result.getCode()));
                    } else if (("${ " + GlobalConst.RECORD_REMARK + "}").equals(dataItem.get(j))) {
                        ps.setString(j + 1, String.valueOf(result.getDescription()));
                    }else if (("${" + GlobalConst.NOW + "}").equals(dataItem.get(j))) {
                        ps.setString(j + 1, context.getCurDate());
                    } else {
                        String[] strings = RegexUtils.find(dataItem.get(j), "[\\$]\\{[ a-zA-Z:0-9_]+\\}");
                        if (strings != null && strings.length > 0) {
                            String paramName = strings[0].substring(2, strings[0].length() - 1);
                            ps.setString(j + 1, context.getGlobalInfo().get(paramName));
                        } else {
                            if (dataItem.get(j).endsWith("]") && dataItem.get(j).startsWith("[")) {
                                ps.setString(j + 1, dataItem.get(j).substring(1, dataItem.get(j).length() - 1).trim());
                            } else {
                                ps.setString(j + 1, String.valueOf(map.get(dataItem.get(j))));
                            }
                        }
                    }
                }
            }
        });

        logger.debug("db update: {}", ints);

//        List<SqlParameterSource> params = Lists.newArrayList();
//        params.add(new MapSqlParameterSource("id", msgId));
//        return this.jdbcTemplate.batchUpdate(sql, params.toArray(new SqlParameterSource[0]));

        return ints.length;
    }

    private boolean onlyStatistics() {

        String tmpDataItem = dataItem.replaceAll("[\\$]\\{[ a-zA-Z:0-9_]+\\}", "").replaceAll("\\[[ a-zA-Z:0-9_]+\\]", "").replaceAll(",","");

        if(StringUtils.isNotBlank(tmpDataItem)) {
            return false;
        }else {
            return true;
        }

    }
}
