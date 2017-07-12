package com.hframe.tag.util;

import com.hframework.common.frame.cache.CacheFactory;
import com.hframework.common.frame.cache.CacheKeyEnum;
import com.hframe.tag.bean.Column;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;


public class ReflectUtil {
	
//	public static Object invokeSetMethod(Column column,Object object,HttpServletRequest request){
//
//		try {
//		String tableName=column.getTableName();
//		String className=ClassDeclaredUtils.getClassNameFromDBObject(tableName);
//		Class c=Class.forName(className);
//
//		String setMehodName=ClassDeclaredUtils.getSetMethodFromColumnName(column.getName());
//
//		Method method=c.getMethod(setMehodName, String.class);
//
//		if(object==null){
//			object=c.newInstance();
//		}
//			method.invoke(object, request.getParameter(column.getName()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return object;
//	}
//
//	public static Object invokeSetMethod(Column column,Object object,String value){
//
//		try {
//			String tableName=column.getTableName();
//			String className=ClassDeclaredUtils.getClassNameFromDBObject(tableName);
//			Class c=Class.forName(className);
//
//			String setMehodName=ClassDeclaredUtils.getSetMethodFromColumnName(column.getName());
//
//			Method method=c.getMethod(setMehodName, String.class);
//
//			if(object==null){
//				object=c.newInstance();
//			}
//			method.invoke(object, value);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return object;
//	}

	public static Object invokeGetMethod(Column column,Object object,Class c) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{


		if(c==null){//如果没有传入class对象，及页面没有传入动态的view对象，我们会取出该列的对象
			c=getClassFromDBObject(column.getTableName());
		}

		String columnName=column.getName();

		String columnToMethodName= ClassDeclaredUtils.getGetMethodFromColumnName(columnName);

		Method method = null;
		method = c.getDeclaredMethod(columnToMethodName, null);
		Object valueObj=method.invoke(object, null);

		return valueObj;
	}

	public static Object invokeGetMethod(String columnName,Object object,Class c) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{


		if(c==null){//如果没有传入class对象，及页面没有传入动态的view对象，我们会取出该列的对象
			c=object.getClass();
		}

		String columnToMethodName= ClassDeclaredUtils.getGetMethodFromColumnName(columnName);

		Method method = null;
		method = c.getDeclaredMethod(columnToMethodName, null);
		Object valueObj=method.invoke(object, null);

		return valueObj;
	}

	public static Object invokeGetMethod(Column column,List objects) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{


		Class c=getClassFromDBObject(column.getTableName());

		String columnName=column.getName();

		String columnToMethodName= ClassDeclaredUtils.getGetMethodFromColumnName(columnName);

		Method method = null;
		method = c.getDeclaredMethod(columnToMethodName, null);

		for (Object o : objects) {
			if(o.getClass().getName().equals(c.getName())){
				Object valueObj=method.invoke(o, null);
				return valueObj;
			}

		}

	   return null;
	}



//
//	public static Object getObjectFromDBObject(String dbObject) {
//		try {
//			//TODO
//			return Class.forName("com.hframework.common.bean.User").newInstance();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	public static Class getClassFromDBObject(String dbObject) {
		try {
			//TODO
			 Map map= (Map) CacheFactory.get(CacheKeyEnum.DS_CREATE_CACHE.getName(), dbObject);
				return Class.forName((String)map.get("PoPath"));
			//return Class.forName("com.hframework.common.bean.User");
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return null;
	}

}
