package com.hframe.tag.db;


import com.hframe.tag.bean.Column;
import com.hframe.tag.bean.TabGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 通用数据库操作接口定义
 * Created by zhangqh6 on 2015/10/20.
 */
public interface IDBOperator {

    /**
     * 通过数据集获取数据集字段信息
     * @param viewCode
     * @return
     */
    public List<Column> getColumnsList(String viewCode);

    /**
     * 通过数据集获取数据集字段信息
     * @param viewCode
     * @return
     */
    public List<Column> getTreeColumnsList(String viewCode);

    /**
     * 获取通过主键查询对象
     * @param objectId
     * @param dbObjectName
     * @param primaryKey
     * @return
     * @throws Exception
     */
    public List getObjectsByPk(String objectId, String dbObjectName, String primaryKey) throws Exception;

    /**
     * 通过条件查询对象集合
     * @param condition
     * @param dbObjectName
     * @param c
     * @return
     */
    public List getObjectsByCondition(String condition, String dbObjectName,Class c);

    /**
     * 通过条件查询对象集合
     * @param condition
     * @param dbObjectName
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List getObjectsByCondition(String condition, String dbObjectName,int pageIndex,int pageSize);


    /**
     *  通过条件查询对象集合条数
     * @param condition
     * @param dbObjectName
     * @return
     */
    public int getObjectsByConditionCount(String condition, String dbObjectName);

    /**
     * 通过查询条件查询树形数据
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
    public List getTreeObjectsByCondition(String condition, String dbObjectName, String id, String pid, String popath)
            throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, ClassNotFoundException;

    /**
     * 通过视图获取列信息
     * @param view
     * @return
     */
    public List getFieldsList(String view);


    /**
     * 通过sql获取查询对象
     * @param sql
     * @param param
     * @return
     */
    public List getObjectBySql(String sql,Object[] param);


    /**
     * 常用操作
     * @return
     */
    public TabGroup getBackOperation();


    /**
     * 通过sqlId查询sql信息
     * @param sqlId
     * @return
     */
    public String[] getSqlBySqlId(String sqlId);


    /**
     * 通过url以及类型获取系统地址
     * @param id
     * @param type
     * @return
     */
    public String[]  getSystemUrlAndSql(String id,String type);
}
