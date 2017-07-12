package com.hframework.reconciliation.core;

import com.google.common.base.Enums;
import com.hframework.reconciliation.bean.RData;
import com.hframework.reconciliation.bean.config.File;
import com.hframework.reconciliation.bean.config.Row;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangquanhong on 2016/5/4.
 */
public class FileWriter extends AbstractWriter  implements Writer{

    private List<Row> rowList = null;
    private HostHolder.FtpTemplate ftpTemplate = null;
    private FileParser fileParser;
    private File file;

    public FileWriter(ServiceContext context, HostHolder hostHolder, File file, String scope, String dataItem, String originId, String targetId) {
        super(context, hostHolder, scope, dataItem, originId, targetId);
        rowList = file.getRowList();
        this.file = file;

    }


    public  int  write(RData rData) {
        String content = fileParser.parse(rData, scope,dataItems);
        writeFile(content, null, null);
        return 0;
    }

    public String writeFile(String content, String fileName, String filePath) {
        return ftpTemplate.write(content, fileName, filePath);
    }

    public static class FileParser{
        List<Row> rowList = null;
        public FileParser(List<Row> rowList) {
            this.rowList = rowList;
        }

        public  String  parse(RData rData, String scope, List<String> dataItem) {

            StringBuffer sb = new StringBuffer();
            SCOPE scopeEnum = Enums.getIfPresent(SCOPE.class, scope).get();

            getContent(rData.getCompareDiffData(),rData.getDiffDataCacheMap(),RESULT.SINGLE,dataItem, sb);
            getContent(rData.getSameKeyDiffList(),rData.getDiffDataCacheMap(),RESULT.DIFF, dataItem,sb);
             if(scopeEnum == SCOPE.all) {
                 getContent(rData.getSameDataList(),rData.getCompareDataCacheMap(),RESULT.SAME, dataItem, sb);
             }
            return sb.toString();
        }

        private List<String> getContent(List<String> keyList, Map<String, Map<String, Object>> dataMap, RESULT result,
                                        List<String> dataItem, StringBuffer sb) {
            StringBuffer tempSb = new StringBuffer();
            for (String rowKey : keyList) {
                Map<String, Object> map = dataMap.get(rowKey);
                for (String columnItem : dataItem) {
                    if(tempSb.length() != 0) {
                        tempSb.append(",");
                    }

                    if("${status}".equals(columnItem)) {
                        tempSb.append(result);
                    }else {
                        tempSb.append(map.get(columnItem));
                    }
                }
                tempSb.append("\n");
            }
            sb.append(tempSb);
            return null;
        }
    }


}
