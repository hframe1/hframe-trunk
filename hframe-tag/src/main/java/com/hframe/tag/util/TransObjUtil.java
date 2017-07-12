package com.hframe.tag.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hframe.tag.bean.*;
import com.hframe.tag.db.DBOperatorProxy;

public class TransObjUtil {

	public static Map  transObjToMap(String view,List columnsList, Object object,String param) throws SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		Map map=new HashMap();

//		Class c=ReflectUtil.getClassFromDBObject(view);//通过前台传入的view（table）找对对应的Class
		Class c = object.getClass();
		if(c==null){
			return map;
		}

		for (Object columnObj : columnsList) {//循环每一列属性

			if (columnObj instanceof Column) {
				Column column = (Column) columnObj;
				String columnName=column.getName();
				ShowType showType=column.getShowType();
				try {
					Object valueObj = ReflectUtil.invokeGetMethod(column, object, c);


					if(column.getShowTypes()!=null&&column.getShowTypes().size()>0){

						String valueStr=(String) valueObj;
						String[] values=valueStr.split(",");

						List<ShowType> sts=column.getShowTypes();

						Map parts=new HashMap();
						for (int i = 0; i < sts.size(); i++) {
							if("select".equals(sts.get(i).getType())){
								parts.put(i+1, values[i]);
							}
						}
						if(parts.size()>0){
							pushToMap(map,columnName,parts);//将对应的值放到map中
						}else{
							pushToMap(map,columnName,values);//将对应的值放到map中
						}
					}else{

						if("tipinput".equals(showType.getType())){
							String elementId=showType.getElementId();
							String[] sql= DBOperatorProxy.getInstance().getSqlBySqlId(elementId);
							String sqlStr=sql[0];
							String key=sql[2];
							String text=sql[3];
							if(!sql[0].contains(" where ")){
								sqlStr+=" where 1=1";
							}
							sqlStr+=(" and "+key+" = '"+valueObj+"' ");
							List result = DBOperatorProxy.getInstance().getObjectBySql(sqlStr, null);
							if(result!=null&&result.size()==1){
								pushToMap(map,columnName+"_DISPNAME",(String)(((Map)result.get(0)).get(text)));//将对应的值放到map中
							}else{
								pushToMap(map,columnName+"_DISPNAME","");
							}

						}else if("openwin".equals(showType.getType())){
							pushToMap(map,columnName+"_DISPNAME","["+valueObj+"]"+showType.getText());
						}

						pushToMap(map,columnName,valueObj);//将对应的值放到map中
					}

				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}

		}

		return map;
	}

	public static void reBuildColumnsList(List columnsList,String param){
		for (Object columnObj : columnsList) {//循环每一列属性
			if (columnObj instanceof Column) {
				Column column = (Column) columnObj;
				String columnName=column.getName();
				ShowType showType=column.getShowType();
				try {

					if(column.getShowTypes()!=null&&column.getShowTypes().size()>0){
					}else{

						if("select".equals(showType.getType())){
							if(showType.getCoreEnumDyn()!=null){
								EnumDyn coreEnumDyn = showType.getCoreEnumDyn();

								String keyStr,valStr;
								String sqlStr=coreEnumDyn.getSql();

								keyStr=sqlStr.substring(sqlStr.toLowerCase().indexOf("select")+7,sqlStr.toLowerCase().indexOf("from")).split(",")[0].trim();
								valStr=sqlStr.substring(sqlStr.toLowerCase().indexOf("select")+7,sqlStr.toLowerCase().indexOf("from")).split(",")[1].trim();


								if(coreEnumDyn.getCondition() != null && param != null){
									sqlStr+=" where "+coreEnumDyn.getCondition()+" = "+param;
								}

								List result = DBOperatorProxy.getInstance().getObjectBySql(sqlStr, null);

								List optionList=new ArrayList();
								for (Object object2 : result) {
									Map map2=(Map)object2;
									String value=(String) map2.get(keyStr);
									String text=(String) map2.get(valStr);
									optionList.add(new Option(value,text));
								}
								showType.setOptionList(optionList);
							}

						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}


	public static Map  transObjToMap(String view,List columnsList, Object object) throws SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		return transObjToMap(view,columnsList,object,null);
	}
	public static Map  transObjsToMap(List columnsList, List objects) throws SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		Map map=new HashMap();

		for (Object columnObj : columnsList) {//循环每一列属性

			if (columnObj instanceof Column) {
				Column column = (Column) columnObj;
				String columnName=column.getName();

				try {
					Object valueObj = ReflectUtil.invokeGetMethod(column, objects);
					pushToMap(map,columnName,valueObj);//将对应的值放到map中
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}

		}

		return map;
	}

	private static void pushToMap(Map map, String columnName, Object valueObj) {
		if (valueObj instanceof String) {
			String value = (String) valueObj;
			map.put(columnName, value);
		}else if (valueObj instanceof Integer) {
			Integer value = (Integer) valueObj;
			map.put(columnName, value);
		}else{
			map.put(columnName, valueObj);
		}

	}

//	public void transHttpParamToObj(List<Column> columnList,
//									HttpServletRequest request, User user) {
//
//
//
//	}
//
//
//
//
//	public static  List<Object> transHttpParamToObj(List<Column> columnList,
//													HttpServletRequest request) {
//		try {
//
//			Map map=new HashMap();
//			for (Column column : columnList) {
//				String tableName=column.getTableName();
//				if(!map.containsKey(tableName)){
//
//					map.put(tableName, ReflectUtil.invokeSetMethod(column, null, request));
//				}else{
//					Object o=map.get(tableName);
//					map.put(tableName, ReflectUtil.invokeSetMethod(column, o, request));
//				}
//			}
//
//			autoFullObjects(map);
//
//			Collection conllection=(Collection) map.values();
//
//			List list=new ArrayList();
//			for (Object object : conllection) {
//				list.add(object);
//			}
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

//
//	private static void autoFullObjects(Map map) {
//
//		Set keySet=map.keySet();
//
//		for (Object tableName : keySet) {
//			List columnList=(List) SetCacheFactory.get((String) tableName).get("columnsList");
//			for (Object column : columnList) {
////				TODO if(column.isPK){
//				map.put(tableName, ReflectUtil.invokeSetMethod((Column)column, null, "111111111"));
////				}
//
//			}
//		}
//
//
//	}
	public static List transObjToFields(List objects, List fieldsList) {

		List resultList=new ArrayList();

		if(objects == null) {
			return resultList;
		}

		for (Object object : objects) {

			List fieldsListCP=new ArrayList();
//			Collections.copy(fieldsListCP, fieldsList);
//			fieldsListCP.addAll(fieldsList);

			//复制生成
			for(Object fieldObj:fieldsList){
				fieldsListCP.add(((Field)fieldObj).clone());
			}
			//在这里由于是树，所以我们只有一列，外加一个复选框，没有其他的
			for (Object fieldObj : fieldsListCP) {
				Field field=(Field)fieldObj;

				autofullFieldByObject(object,field);

//				field.setShowExp("aaaaaaaaaa");//TODO
//				field.setHiddenExp("bbbbbbb");
			}
			resultList.add(fieldsListCP);
		}

		return resultList;

	}
	private static void autofullFieldByObject(Object object, Field field) {
		if("virtual_pid".equals(field.getType())||"virtual_id".equals(field.getType())){
			try {
				String showExp=field.getShowExp();
				if(!"".equals(showExp)&&showExp!=null)
					field.setShowExp((String) ReflectUtil.invokeGetMethod(showExp, object, null));

				String hiddenExp=field.getHiddenExp();
				if(!"".equals(hiddenExp)&&hiddenExp!=null)

					field.setHiddenExp(String.valueOf(ReflectUtil.invokeGetMethod(hiddenExp, object, null)));

			} catch (Exception e) {
				e.printStackTrace();
			}

			return ;
		}


		//将showExp转化为对应的字符串
		String showExp=field.getShowExp();//${field:name}+"("+${sql:511}+")/("+${sql:511}+")"

		Pattern p = Pattern.compile("\\$\\{[ a-zA-Z:0-9_\\]\\[]+\\}");
		Matcher m=null;
		String type="";
		String name="";
		String value = null;
		if(showExp!=null&&!showExp.equals("")){

			m=p.matcher(showExp);

			Set<String> expSet=new HashSet<String>();

			while(m.find()){
				expSet.add(m.group());
			}



			Map<String, Object> expMap=new HashMap<String, Object>();

			for (String exp : expSet) {// ${href:1751}
				type=exp.substring(2, exp.indexOf(":"));
				name=exp.substring( exp.indexOf(":")+1,exp.length()-1);
				if("field".equals(type)){ //field中套用field

					try {
						value = (String) ReflectUtil.invokeGetMethod(name, object, null);
						//					expMap.put(exp, value);
					}  catch (Exception e) {
						e.printStackTrace();
					}
				}else if("column".equals(type)){

					try {

						if (ReflectUtil.invokeGetMethod(name, object, null) instanceof Date) {
							value = ((Date)ReflectUtil.invokeGetMethod(name, object, null)).toLocaleString();
						}else{
							value = String.valueOf(ReflectUtil.invokeGetMethod(name, object, null));
						}

						//					expMap.put(exp, value);
					}  catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					Object[] param=null;
					Pattern p1 = Pattern.compile("\\[[ a-zA-Z:0-9_]+\\]");
					Matcher m1=null;
					m1=p1.matcher(name);
					Set<String> expSet1=new HashSet<String>();
					while(m1.find()){
						expSet1.add(m1.group());
					}
					String paramVal=null;

					for (String exp1 : expSet1) {// ${href:1751}
						String paramType=exp1.substring(1, exp1.indexOf(":"));
						String paramName=exp1.substring( exp1.indexOf(":")+1,exp1.length()-1);

						if("column".equals(paramType)){
							try {
								if (ReflectUtil.invokeGetMethod(paramName, object, null) instanceof Date) {
									paramVal = ((Date)ReflectUtil.invokeGetMethod(paramName, object, null)).toLocaleString();
								}else{
									paramVal = (String) ReflectUtil.invokeGetMethod(paramName, object, null);
								}
							}  catch (Exception e) {
								e.printStackTrace();
							}

							name=name.replace(exp1, "");
						}
					}
					String[] sqlStr = DBOperatorProxy.getInstance().getSystemUrlAndSql(name, type);
					if(sqlStr!=null){
						List list = null;
						if(paramVal!=null&& sqlStr[2]!=null){
							param = new Object[]{paramVal};
							list = DBOperatorProxy.getInstance().getObjectBySql(sqlStr[0] + " where " + sqlStr[2] + " =  " + paramVal, null);

						}else if(paramVal!=null){
							param = new Object[]{paramVal};
							list = DBOperatorProxy.getInstance().getObjectBySql(sqlStr[0], param);
						}
						for (int i = 0; list != null && i < list.size(); i++) {
							Map map = (Map)list.get(i);
							Collection vals = map.values();
							for (Object object2 : vals) {
								value=(String) object2;
							}
						}
					}

					//				 expMap.put(exp, value);
				}
				if(value==null){
					value="";
				}
				showExp=showExp.replace(exp, value);
			}

			field.setShowExp(showExp);

		}

		//将hiddenExp转化为对应的字符串
		String hiddenExp=field.getHiddenExp(); // ${href:1751}+"${column:274}
		if(hiddenExp!=null&&!hiddenExp.equals("")){

			m=p.matcher(hiddenExp);

			Set<String> hiddenSet=new HashSet<String>();

			while(m.find()){
				hiddenSet.add(m.group());
			}

			for (String exp : hiddenSet) {
				type=exp.substring(2, exp.indexOf(":"));
				name=exp.substring( exp.indexOf(":")+1,exp.length()-1);
				if("field".equals(type)){

					try {
						value = (String) ReflectUtil.invokeGetMethod(name, object, null);
						//					expMap.put(exp, value);
					}  catch (Exception e) {
						e.printStackTrace();
					}
				}else if("column".equals(type)){

					try {
						value =String.valueOf(ReflectUtil.invokeGetMethod(name, object, null));
						//					expMap.put(exp, value);
					}  catch (Exception e) {
						e.printStackTrace();
					}
				}else {

					String[] sqlStr = DBOperatorProxy.getInstance().getSystemUrlAndSql(name, type);
					Object[] param=null;

					if(sqlStr!=null){
						//List list = new CommonServ().getObjectBySql(sqlStr[0]+" where "+ sqlStr[1]+" = ? ",param);
					}

					//				 expMap.put(exp, value);
				}
				if(value==null){
					value="";
				}
				hiddenExp=hiddenExp.replace(exp, value);
			}

			field.setHiddenExp(hiddenExp);
		}

	}

}
