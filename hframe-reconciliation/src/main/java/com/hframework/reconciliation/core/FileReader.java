package com.hframework.reconciliation.core;

import com.hframework.common.util.DateUtils;
import com.hframework.common.util.RegexUtils;
import com.hframework.common.util.StringUtils;
import com.hframework.reconciliation.bean.RData;
import com.hframework.reconciliation.bean.config.File;
import com.hframework.reconciliation.bean.config.Item;
import com.hframework.reconciliation.bean.config.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by zhangquanhong on 2016/5/4.
 */
public class FileReader extends AbstractReader  implements Reader{

    private static final Logger logger = LoggerFactory.getLogger(FileReader.class);

    private List<Row> rowList = null;
    private File file = null;
    private HostHolder.FtpTemplate ftpTemplate = null;
    private FileParser fileParser;
    private boolean isLeftData;


    public FileReader(ServiceContext context, HostHolder hostHolder, File file, boolean isLeftData) {
        super(context, hostHolder);
        this.rowList = file.getRowList();
        this.file = file;
        fileParser = new FileParser(rowList);
        this.isLeftData = isLeftData;
        ftpTemplate = hostHolder.getFtpTemplate();
    }


    public  Map<String,RData>  read() throws Exception {
        return fileParser.parse(readFileContext(),isLeftData);
    }

    public List<String> readFileContext() throws Exception {
        String path = getRealPath(hostHolder.getHost().getCxtpath(), file.getPath());
        String fileName = getRealFileName(file.getName());
        return ftpTemplate.getContent(path, fileName, file.getTmpPath());
    }

    private String getRealPath(String cxtpath, String path) {
        if(StringUtils.isNotBlank(cxtpath)) {
            return getRealFileName(cxtpath + path);
        }
        return path;
    }

    private String getRealFileName(String name) {
        logger.debug("params:{}",name);
        String[] expresses = RegexUtils.find(name, "%-d\\{[ a-zA-Z:0-9_]+\\}");
        if(expresses != null && expresses.length > 0) {
            for (String express : expresses) {
                String timePattrn = express.substring(4, express.length() - 1).trim();
                String realTime = DateUtils.getDate(context.getCompareDate(), timePattrn);
                name = name.replace(express,realTime);
            }
        }
        logger.debug("result:{}",name);
        return name;
    }

    public static class FileParser{
        List<Row> rowList = null;
        public FileParser(List<Row> rowList) {
            this.rowList = rowList;
        }

        public  Map<String,RData>  parse(List<String> contentLines, boolean isLeftData) {
            Map<String,RData> data= new HashMap<String, RData>();

            Row mainDataRow = null;
            int mainDataEndExcludeRows = 0;
            for (Row row : rowList) {
                if(StringUtils.isBlank(row.getRowspan()) || "*".equals(row.getRowspan().trim())) {
                    mainDataRow = row;
                    continue;
                }
                if(mainDataRow != null) {
                    mainDataEndExcludeRows += StringUtils.isNotBlank(row.getRowspan()) ? Integer.valueOf(row.getRowspan().trim()) : 1;
                }
            }

            //根据数据行规则将数据分组<规则，数据组对应结束行号>
            Map<Row, Integer> rowRecordCounter = new LinkedHashMap<Row, Integer>();
            int count = 0;
            for (Row row : rowList) {
                if(mainDataRow != row) {
                    count += Integer.valueOf(row.getRowspan().trim());
                }else {
                    count = contentLines.size() - mainDataEndExcludeRows;
                }
                rowRecordCounter.put(row,count);
            }

            count = 0;
            for (Row row : rowList) {

                parseRData(row, contentLines, count, rowRecordCounter.get(row), data, isLeftData);
                count = rowRecordCounter.get(row);
            }
            return data;
        }

        private void parseRData(Row rowRule, List<String> lines, Integer beginIndex, Integer endIndex, Map<String, RData> resultMap, boolean isLeftData) {

            if(StringUtils.isNotBlank(rowRule.getId())) {
                RData rData = new RData(isLeftData);
                List<Map<String, Object>> originalDataList = new ArrayList<Map<String, Object>>();
                rData.setOriginalData(originalDataList);
                resultMap.put(rowRule.getId(), rData);
                for (int i = beginIndex; i < endIndex; i++) {
                    String line = lines.get(i);
                    Map<String, Object> lineMap = new HashMap<String, Object>();
                    String[] split = line.split(getJavaSeparator(rowRule.getSeparator()));
                    for (int j = 0; j < split.length; j++) {
                        lineMap.put("{" + String.valueOf(j+1) + "}",split[j]);
                    }
                    originalDataList.add(lineMap);
                }
            }else if(rowRule.getItemList() != null && rowRule.getItemList().size() > 0){
                String line = lines.get(beginIndex);//获得第一条数据
                String express = rowRule.getExpress();
                String[] splits = express.split("\\{[ ]*\\}");
                String regexExpress = "";
                for (String split : splits) {
                    if(StringUtils.isNotBlank(split)) {
                        regexExpress += "[^(" + split.trim()+ ")]+";
                    }
                }
                String[] values = RegexUtils.find(line, regexExpress);
                for (Item item : rowRule.getItemList()) {
                    if(StringUtils.isNotBlank(item.getId())) {
                        int index = Integer.valueOf(item.getText().replaceAll("\\{", "").replaceAll("\\}", "").trim());
                        RData rData = new RData(isLeftData);
                        rData.setSingleValue(values[index -1].trim());
                        resultMap.put(item.getId(), rData);
                    }
                }
            }
        }

        private String getJavaSeparator(String separator) {

            Arrays.asList(new String[]{"|"}).contains(separator);

            return Arrays.asList(new String[]{"|"}).contains(separator) ? "\\" + separator : separator;
        }
    }
}
