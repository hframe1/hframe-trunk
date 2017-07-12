package com.hframe.tag.db;

import com.hframe.tag.bean.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 数据库操作工具类
 * Created by zhangqh6 on 2015/10/20.
 */
public class DBOperatorProxy implements IDBOperator {

    private IDBOperator dbOperator = null;

    private DBOperatorProxy(){
        dbOperator = new DefaultDBOperatorImpl();
    }

    /**
     * 通过数据集获取数据集字段信息
     * @param view2
     * @return
     */
    public List getColumnsList(String view2) {
       return dbOperator.getColumnsList(view2);
    }

    /**
     * 通过数据集获取数据集字段信息
     * @param view2
     * @return
     */
    public List getTreeColumnsList(String view2) {
        return dbOperator.getTreeColumnsList(view2);
    }

    /**
     * TODO
     * 获取通过主键查询对象
     * @param objectId
     * @param dbObjectName
     * @param primaryKey
     * @return
     * @throws Exception
     */
    public List getObjectsByPk(String objectId, String dbObjectName, String primaryKey) throws Exception{
        return dbOperator.getObjectsByPk(objectId, dbObjectName, primaryKey);
    }

    /**
     * 通过条件查询对象集合
     *TODO
     * @param condition
     * @param dbObjectName
     * @param c
     * @return
     */
    public List getObjectsByCondition(String condition, String dbObjectName, Class c) {
        return dbOperator.getObjectsByCondition(condition, dbObjectName, c);
    }

    /**
     * 通过条件查询对象集合
     *
     * @param condition
     * @param dbObjectName
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List getObjectsByCondition(String condition, String dbObjectName, int pageIndex, int pageSize) {
        return dbOperator.getObjectsByCondition(condition, dbObjectName, pageIndex, pageSize);
    }

    /**
     * 通过条件查询对象集合条数
     *
     * @param condition
     * @param dbObjectName
     * @return
     */
    public int getObjectsByConditionCount(String condition, String dbObjectName) {
        return dbOperator.getObjectsByConditionCount(condition, dbObjectName);
    }

    /**
     * 通过查询条件查询树形数据
     *
     * @param condition
     * @param dbObjectName
     * @param id
     * @param pid
     * @param popath
     * @return
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     */
    public List getTreeObjectsByCondition(String condition, String dbObjectName, String id, String pid, String popath) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        return dbOperator.getTreeObjectsByCondition(condition, dbObjectName, id, pid, popath);
    }

    /**
     * 通过视图获取列信息
     *
     * @param view
     * @return
     */
    public List getFieldsList(String view) {
        return dbOperator.getFieldsList(view);
    }

    /**
     * 通过sql获取查询对象
     *
     * @param sql
     * @param param
     * @return
     */
    public List getObjectBySql(String sql, Object[] param) {
        return dbOperator.getObjectBySql(sql,param);
    }

    /**
     * 常用操作
     *
     * @return
     */
    public TabGroup getBackOperation() {
        return dbOperator.getBackOperation();

    }

    /**
     * 通过sqlId查询sql信息
     *
     * @param sqlId
     * @return
     */
    public String[] getSqlBySqlId(String sqlId) {
        return dbOperator.getSqlBySqlId(sqlId);
    }

    /**
     * 通过url以及类型获取系统地址
     * @param id
     * @param type
     * @return
     */
    public String[] getSystemUrlAndSql(String id, String type) {
        return dbOperator.getSystemUrlAndSql(id, type);
    }

    public static DBOperatorProxy getInstance() {
        return new DBOperatorProxy();
    }

}
