package com.hframe.tag.db;

import com.hframework.common.util.ExampleUtils;
import com.hframework.common.util.ReflectUtils;
import com.hframe.tag.bean.*;
import com.hframe.tag.util.ClassDeclaredUtils;
import com.hframe.tag.util.ReflectUtil;
import com.hframework.common.frame.ServiceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangqh6 on 2015/10/20.
 */
public class DefaultDBOperatorImpl implements IDBOperator{


    /**
     * 通过数据集获取数据集字段信息
     * @param view2
     * @return
     */
    public List getColumnsList(String view2) {
        List columnsList=new ArrayList();

        Column userIdColumn=new Column();
        userIdColumn.setId("1");
        userIdColumn.setName("ID");
        userIdColumn.setDisplayName("用户标记");
        userIdColumn.setDefaultValue("  ");
        userIdColumn.setShowType(new ShowType("input"));
        columnsList.add(userIdColumn);

        Column userNameColumn=new Column();
        userNameColumn.setId("2");
        userNameColumn.setName("Name");
        userNameColumn.setDisplayName("名称");
        userNameColumn.setDefaultValue("  ");
        userNameColumn.setShowType(new ShowType("input"));
        columnsList.add(userNameColumn);

        Column genderColumn=new Column();
        genderColumn.setId("3");
        genderColumn.setName("Gender");
        genderColumn.setDisplayName("性别");
        genderColumn.setDefaultValue("  ");

        ShowType showType=new ShowType("select");

        List optionList=new ArrayList();
        optionList.add(new Option("1","男"));
        optionList.add(new Option("0","女"));

        showType.setOptionList(optionList);

        genderColumn.setShowType(showType);
        columnsList.add(genderColumn);


        //年下拉框
        List yearList=new ArrayList();
        yearList.add(new Option("1987","1987"));
        yearList.add(new Option("1988","1988"));

        ShowType yearSelect=new ShowType("select");
        yearSelect.setOptionList(yearList);
        yearSelect.setAfterStr("年");

        //月下拉框
        List monthList=new ArrayList();
        monthList.add(new Option("1","1"));
        monthList.add(new Option("2","2"));

        ShowType monthSelect=new ShowType("select");
        monthSelect.setOptionList(monthList);
        monthSelect.setAfterStr("月");

        //日下拉框
        List dayList=new ArrayList();
        dayList.add(new Option("1","1"));
        dayList.add(new Option("2","2"));

        ShowType daySelect=new ShowType("select");
        daySelect.setOptionList(dayList);
        daySelect.setAfterStr("日");

        List<ShowType> showTypes=new ArrayList();
        showTypes.add(yearSelect);
        showTypes.add(monthSelect);
        showTypes.add(daySelect);

        Column birthColumn=new Column();
        birthColumn.setId("5");
        birthColumn.setName("BirthDate");
        birthColumn.setDisplayName("出生年月");
        birthColumn.setDefaultValue("  ");
        birthColumn.setShowTypes(showTypes);
        birthColumn.setFiledWidth(200);

        columnsList.add(birthColumn);


        Column addrColumn=new Column();
        addrColumn.setId("4");
        addrColumn.setName("Addr");
        addrColumn.setDefaultValue("  ");
        addrColumn.setShowType(new ShowType("input",3,450));
        columnsList.add(addrColumn);

        Column developerColumn=new Column();
        developerColumn.setId("4");
        developerColumn.setName("Developer");
        developerColumn.setDefaultValue("  ");
        developerColumn.setShowType(new ShowType("openwin"));
        columnsList.add(developerColumn);

        Column priceColumn=new Column();
        priceColumn.setId("4");
        priceColumn.setName("Rank");
        priceColumn.setDefaultValue("  ");

        ShowType st=new ShowType("input");
        st.setPreStr("第");
        st.setAfterStr("名");
        st.setWidth(40);
        priceColumn.setShowType(st);
        columnsList.add(priceColumn);

        Column likeColumn=new Column();
        likeColumn.setId("4");
        likeColumn.setName("Hob");
        likeColumn.setDisplayName("爱好");
        likeColumn.setDefaultValue("  ");

        List likeList=new ArrayList();
        ShowType st1=new ShowType("checkbox");
        st1.setAfterStr("篮球");
        st1.setValue("1");
        likeList.add(st1);

        ShowType st2=new ShowType("checkbox");
        st2.setValue("2");
        st2.setAfterStr("足球");
        likeList.add(st2);

        ShowType st3=new ShowType("checkbox");
        st3.setAfterStr("读书");
        st3.setValue("3");

        likeList.add(st3);

        likeColumn.setShowTypes(likeList);
        likeColumn.setFiledWidth(200);
        columnsList.add(likeColumn);
        return columnsList;
    }

    /**
     * 通过数据集获取数据集字段信息
     * @param view2
     * @return
     */
    public List getTreeColumnsList(String view2) {
		List menus=new ArrayList();

//	 	menus.add(new MenuVO("0","-1","四川","index.jsp"));
//	 	menus.add(new MenuVO("1","0","成都","index.jsp"));
//	 	menus.add(new MenuVO("2","0","南充","index.jsp"));
//	 	menus.add(new MenuVO("3","0","宜宾","index.jsp"));
//	 	menus.add(new MenuVO("4","0","眉山","index.jsp"));
//
//	 	menus.add(new MenuVO("11","1","华阳","index.jsp"));
//	 	menus.add(new MenuVO("12","1","武侯","index.jsp"));
//	 	menus.add(new MenuVO("13","1","温江","index.jsp"));
//	 	menus.add(new MenuVO("14","1","郫县","index.jsp"));
//
//
//	 	menus.add(new MenuVO("21","2","南部","index.jsp"));
//	 	menus.add(new MenuVO("22","2","西充","index.jsp"));
//	 	menus.add(new MenuVO("23","2","仪陇","index.jsp"));
//	 	menus.add(new MenuVO("24","2","阆中","index.jsp"));


	 	return menus;

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
        String svName = ClassDeclaredUtils.getServiceNameFromTableName(dbObjectName);
        String methodName = "get" + ClassDeclaredUtils.getJavaClassName(dbObjectName)+"ByPK";
        Object service = ServiceFactory.getService(svName);
        Object result = null ;

        result = ReflectUtils.invokeMethod(service,methodName,new Class[]{long.class},new Object[]{Long.valueOf(objectId)});

        if(result == null) {
            return null;
        }else {
            return Arrays.asList(new Object[]{result}) ;
        }
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

        return getObjectsByCondition(condition,dbObjectName,-1,-1);
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

        String svName = ClassDeclaredUtils.getServiceNameFromTableName(dbObjectName);
        String methodName = "get" + ClassDeclaredUtils.getJavaClassName(dbObjectName)+"ListByExample";
        Object service = ServiceFactory.getService(svName);
        Object result = null ;
        Class<?> poExampleClass = null;
        try {
            poExampleClass = ClassDeclaredUtils.getPoExampleObjFromTableName(dbObjectName);

            Object poExampleObj = poExampleClass.newInstance();
            ExampleUtils.parseExample(condition, poExampleObj);
            if(pageIndex > 0 && pageSize > 0) {
                ReflectUtils.invokeMethod(
                        poExampleObj, "setLimitStart", new Class[]{Integer.class}, new Object[]{(pageIndex - 1) * pageSize});
                ReflectUtils.invokeMethod(
                        poExampleObj, "setLimitEnd", new Class[]{Integer.class}, new Object[]{pageIndex * pageSize});
            }
            result = ReflectUtils.invokeMethod(
                service,methodName,new Class[]{poExampleClass},new Object[]{poExampleObj});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if(result == null) {
            return null;
        }else {
            return (List) result;
        }
    }

    /**
     * 通过条件查询对象集合条数
     *
     * @param condition
     * @param dbObjectName
     * @return
     */
    public int getObjectsByConditionCount(String condition, String dbObjectName) {

        String svName = ClassDeclaredUtils.getServiceNameFromTableName(dbObjectName);
        String methodName = "get" + ClassDeclaredUtils.getJavaClassName(dbObjectName)+"CountByExample";
        Object service = ServiceFactory.getService(svName);
        try {
            Class<?> poExampleClass = ClassDeclaredUtils.getPoExampleObjFromTableName(dbObjectName);
            Object poExampleObj = poExampleClass.newInstance();
            ExampleUtils.parseExample(condition, poExampleObj);
            int result = (Integer)ReflectUtils.invokeMethod(
                    service,methodName,new Class[]{poExampleClass},new Object[]{poExampleObj});
            return result;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 通过查询条件查询树形数据
     *
     * @param condition
     * @param dbObjectName
     * @param id
     * @param pid
     * @param poPath
     * @return
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     */
    public List getTreeObjectsByCondition(String condition, String dbObjectName, String id, String pid, String poPath)
            throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, ClassNotFoundException {

        List result=new ArrayList();

        List lastList = getObjectsByCondition(condition, dbObjectName, null);
        result.addAll(lastList);

        if(result!=null&&result.size()==1){
            condition = "";
            findTreeObjects(result,lastList,dbObjectName,id,pid,condition,poPath);
        }

        return result;
    }

    private void findTreeObjects(List result, List lastList, String dbObjectName,String id, String pid,
                                 String condition, String poPath) throws SecurityException, IllegalArgumentException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        for (Object object : lastList) {
            Object parValue= ReflectUtil.invokeGetMethod(id, object, object.getClass());
            String parName=ClassDeclaredUtils.getJavaVarName(pid);
            List tempResult = getObjectsByCondition(condition + "&" + parName + " = " + parValue, dbObjectName, null);
            result.addAll(tempResult);
            findTreeObjects(result,tempResult,dbObjectName,id,pid,condition,poPath);
        }
    }

    /**
     * 通过视图获取列信息
     *
     * @param view
     * @return
     */
    public List getFieldsList(String view) {
        List fieldList=new ArrayList();

        if("menu".equals(view)){
            fieldList.add(new Field("100","virtual_pid","","pid"));
            fieldList.add(new Field("101","virtual_id","","id"));
            fieldList.add(new Field("102","text","${field:name}","${field:url}"));

        }else if("treeDemo".equals(view)){
            fieldList.add(new Field("100","virtual_pid","","pid"));
            fieldList.add(new Field("101","virtual_id","","id"));
            fieldList.add(new Field("100","virtual_value","checkbox","","${column:id}"));//该字段暂时未用，默认为virtual_id到时候可以修改
            fieldList.add(new Field("103","href","${field:name}","${field:url}"));
        }else if("SYS_FILE_TREE".equals(view)){
            fieldList.add(new Field("100","virtual_pid","","pid"));
            fieldList.add(new Field("101","virtual_id","","id"));
            fieldList.add(new Field("100","virtual_value","checkbox","","${column:id}"));//该字段暂时未用，默认为virtual_id到时候可以修改
            fieldList.add(new Field("103","href","${field:filename}","${field:fullname}"));
        }else{//这是一个列表的view   口全选     标题   最后更新时间
            fieldList.add(new Field("100","全选${sys:allSelect}","checkbox","","${column:id}"));
            fieldList.add(new Field("101","标题","href","${column:name}","${href:1751}${column:id}"));
            fieldList.add(new Field("102","最后更新时间","text","${column:modify_date}",""));

        }
        return fieldList;
    }

    /**
     * 通过sql获取查询对象
     *
     * @param sql
     * @param param
     * @return
     */
    public List getObjectBySql(String sql, Object[] param) {

        JdbcTemplate jdbcTempalte = (JdbcTemplate) ServiceFactory.getService("jdbcTempalte");
        if(param ==null) {
            return jdbcTempalte.queryForList(sql);
        }
        return jdbcTempalte.queryForList(sql,param);
    }

    /**
     * 通过sqlId查询sql信息
     *
     * @param sqlId
     * @return
     */
    public String[] getSqlBySqlId(String sqlId) {
        List sqlList = getObjectBySql("select * from core_element where core_element_id='" + sqlId + "'", null);
        String[] sqlParts=new String[4];
        if(sqlList != null && sqlList.size() > 0) {
            Map map=(Map)sqlList.get(0);
            sqlParts[0]=(String) map.get("content");
            sqlParts[1]=(String) map.get("condition");
            sqlParts[2]=(String) map.get("ext1");
            sqlParts[3]=(String) map.get("ext2");
        }
        return sqlParts;
    }

    /**
     * 常用操作
     *
     * @return
     */
    public TabGroup getBackOperation() {
        //title jsp hight width style isIframe


        TabGroup tabGroup=new TabGroup("123","demo");

        TabItem item1=new TabItem("11","数据库","design/backstage/coredblist.jsp",800,550,"true",null,"1");
        TabItem item2=new TabItem("12","数据表","design/backstage/coretablelist.jsp",800,550,"true",null,"1");
        TabItem item3=new TabItem("13","数据列","design/backstage/coretablecolumnlist.jsp",800,550,"true",null,"1");
        TabItem item20=new TabItem("20","展示方式","design/backstage/coreshowtypelist.jsp",800,550,"true",null,"1");

        TabItem item4=new TabItem("14","外键","design/backstage/coretablefklist.jsp",800,550,"true",null,"1");



        TabItem item5=new TabItem("15","连表对象","design/backstage/corecomplexlist.jsp",800,550,"true",null,"1");
        TabItem item6=new TabItem("16","枚举值","design/backstage/coreenumlist.jsp",800,550,"true",null,"1");
        TabItem item7=new TabItem("17","元素管理","design/backstage/coreelementlist.jsp",800,550,"true",null,"1");
        TabItem item8=new TabItem("18","数据集管理","design/backstage/coresetlist.jsp",800,550,"true",null,"1");
        TabItem item9=new TabItem("19","缓存刷新","design/backstage/corecacherefresh.jsp",800,550,"true",null,"1");
        TabItem item23=new TabItem("23","代码生成","design/backstage/corecodeautocreate.jsp",800,550,"true",null,"1");

        List<TabItem> tabItems=new ArrayList<TabItem>();
        tabItems.add(item1);
        tabItems.add(item2);
        tabItems.add(item3);
        tabItems.add(item20);
        tabItems.add(item4);
        tabItems.add(item5);
        tabItems.add(item6);
        tabItems.add(item7);
        tabItems.add(item8);
        tabItems.add(item9);
        tabItems.add(item23);

        tabGroup.setTabItems(tabItems);

        return tabGroup;

    }



    /**
     * 通过url以及类型获取系统地址
     *
     * @param id
     * @param type
     * @return
     */
    public String[] getSystemUrlAndSql(String id, String type) {
        String[] result = getSqlBySqlId(id);
        if(result[0]==null){
            return null;
        }
        return result;
    }

}
