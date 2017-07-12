package com.hframework.reconciliation.bean;

import com.hframework.reconciliation.enums.BalanceStateEnum;
import com.hframework.reconciliation.enums.CompareStateEnum;

/**
 * Created by zhangquanhong on 2016/5/13.
 */
public class GlobalConst {

    //开始时间
    public  static  final String START_DATE = "beginDate";
    //结束时间
    public  static  final String END_DATE = "endDate";


    //批次号
    public  static  final String BATCH_ID = "BATCH_ID";
    //对账状态
    public  static  final String COMPARE_STATE = "COMPARE_STATE";
    //不同记录数
    public  static  final String DIFF_RECORD_COUNT = "DIFF_RECORD_COUNT";
    //相同记录数
    public  static  final String SAME_RECORD_COUNT = "SAME_RECORD_COUNT";
    //我方多出记录数
    public  static  final String SELF_SINGLE_COUNT = "SELF_SINGLE_COUNT";
    //三方多出记录数
    public  static  final String THIRD_SINGLE_COUNT = "THIRD_SINGLE_COUNT";
    //平账状态
    public  static  final String BALANCE_STATE = "BALANCE_STATE";
    //对账时间
    public  static  final String COMPARE_DATETIME = "COMPARE_DATETIME";
    //平账时间
    public  static  final String BALANCE_DATETIME = "BALANCE_DATETIME";

    //对账记录状态
    public  static  final String RECORD_STATUS = "status";
    //对账记录描述
    public  static  final String RECORD_REMARK = "remark";
    //当前时间
    public  static  final String NOW = "now";

}
