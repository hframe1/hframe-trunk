package com.hframework.reconciliation.bean;

import com.hframework.reconciliation.bean.config.Datanode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangquanhong on 2016/5/11.
 */
public class RData {

    //是否为左侧数据集
    private boolean isLeftData;
    //比对规则
    private List<Datanode> dataNodes;
    //比对原始数据
    private List<Map<String, Object>> originalData;
    //比对字符串
    private List<String> compareData;
    //相同数据list
    private List<String> sameDataList;
    //比对字符串与原始数据关系
    private Map<String, Map<String, Object>> compareDataCacheMap;

    private String diffDesc;

    //差异数据（Key值不同）
    private List<String> compareDiffData;
    //比对字符串与原始数据关系
    private Map<String, Map<String, Object>> diffDataCacheMap;
    //差异数据（Key值相同）
    private List<String> sameKeyDiffList;

    private String singleValue;

    public RData() {

    }
    public RData(boolean isLeftData) {
        this.isLeftData = isLeftData;
    }
    public RData(String singleValue, boolean isLeftData) {
        this.singleValue = singleValue;
        this.isLeftData = isLeftData;
    }

    public RData(List<Map<String, Object>> data,boolean isLeftData) {
        this.originalData = data;
        this.isLeftData = isLeftData;
    }

    public List<Map<String, Object>> getOriginalData() {
        return originalData;
    }

    public void setOriginalData(List<Map<String, Object>> originalData) {
        this.originalData = originalData;
    }

    public List<Datanode> getDataNodes() {
        return dataNodes;
    }

    public void setDataNodes(List<Datanode> dataNodes) {
        this.dataNodes = dataNodes;

    }

    private List<String> calculateCompareData() {
        compareData = new ArrayList<String>();
        compareDataCacheMap = new HashMap<String, Map<String, Object>>();
        for (Map<String, Object> originalMap : originalData) {
            StringBuffer sb = new StringBuffer();
            for (Datanode datanode : dataNodes) {
                String originName = isLeftData ? datanode.getLeftName() : datanode.getRightName();
                sb.append(originalMap.get(originName).toString().trim()).append("|");
            }
            compareData.add(sb.toString());
            compareDataCacheMap.put(sb.toString(), originalMap);
        }
        return compareData;
    }

    public List<String> getCompareData() {
        return compareData;
    }

    public List<String> setDataNodeAndReturnCompareDataString(List<Datanode> dataNodes) {
        setDataNodes(dataNodes);
        return calculateCompareData();
    }

    public void setCompareData(List<String> compareData) {
        this.compareData = compareData;
    }

    public boolean isLeftData() {
        return isLeftData;
    }

    public void setLeftData(boolean isleftData) {
        this.isLeftData = isleftData;
    }

    public void setIsLeftData(boolean isLeftData) {
        this.isLeftData = isLeftData;
    }

    public Map<String, Map<String, Object>> getCompareDataCacheMap() {
        return compareDataCacheMap;
    }

    public void setCompareDataCacheMap(Map<String, Map<String, Object>> compareDataCacheMap) {
        this.compareDataCacheMap = compareDataCacheMap;
    }

    public List<String> getCompareDiffData() {
        return compareDiffData;
    }

    public void setCompareDiffData(List<String> compareDiffData) {
        this.compareDiffData = compareDiffData;
    }

    public Map<String, Map<String, Object>> getDiffDataCacheMap() {
        return diffDataCacheMap;
    }

    public void setDiffDataCacheMap(Map<String, Map<String, Object>> diffDataCacheMap) {
        this.diffDataCacheMap = diffDataCacheMap;
    }

    public List<String> setSameAndReturnDiffData(List<String> sameList) {
        this.sameDataList = sameList;
        compareData.removeAll(this.sameDataList);
        compareDiffData = new ArrayList<String>();
        diffDataCacheMap = new HashMap<String, Map<String, Object>>();
        for (String compareString : compareData) {
            Map<String, Object> originalMap = compareDataCacheMap.get(compareString);
            StringBuffer sb = new StringBuffer();
            for (Datanode datanode : dataNodes) {
                if("true".equals(datanode.getIsKey())) {
                    String originName = isLeftData ? datanode.getLeftName() : datanode.getRightName();
                    sb.append(originalMap.get(originName).toString().trim()).append("|");
                }
            }
            compareDiffData.add(sb.toString());
            diffDataCacheMap.put(sb.toString(), originalMap);
        }
        return compareDiffData;
    }

    public void setSameKeyDiffDataList(List<String> diffSameKeyList) {
        this.sameKeyDiffList = diffSameKeyList;
        this.compareDiffData.removeAll(diffSameKeyList);
    }

    public List<String> getSameKeyDiffList() {
        return sameKeyDiffList;
    }

    public void setSameKeyDiffList(List<String> sameKeyDiffList) {
        this.sameKeyDiffList = sameKeyDiffList;
    }

    public boolean isDiff() {
        if((this.compareData != null && this.compareData.size() > 0) || this.diffDesc != null) {
            return true;
        }else {
            return false;
        }
    }

    public List<String> getSameDataList() {
        return sameDataList;
    }

    public void setSameDataList(List<String> sameDataList) {
        this.sameDataList = sameDataList;
    }

    public String getSingleValue() {
        return singleValue;
    }

    public void setSingleValue(String singleValue) {
        this.singleValue = singleValue;
    }

    public String getDiffDesc() {
        return diffDesc;
    }

    public void setDiffDesc(String diffDesc) {
        this.diffDesc = diffDesc;
    }


    @Override
    public String toString() {
        return "RData{" +
                "isLeftData=" + isLeftData +
                ", dataNodes=" + dataNodes +
                ", originalData=" + originalData +
                ", compareData=" + compareData +
                ", sameDataList=" + sameDataList +
                ", compareDataCacheMap=" + compareDataCacheMap +
                ", diffDesc='" + diffDesc + '\'' +
                ", compareDiffData=" + compareDiffData +
                ", diffDataCacheMap=" + diffDataCacheMap +
                ", sameKeyDiffList=" + sameKeyDiffList +
                ", singleValue='" + singleValue + '\'' +
                '}';
    }
}
