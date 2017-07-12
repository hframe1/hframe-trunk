package com.hframe.tag;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hframework.common.frame.cache.CacheFactory;
import com.hframework.common.frame.cache.CacheKeyEnum;
import com.hframework.common.util.message.VelocityUtil;
import com.hframe.tag.bean.Field;
import com.hframe.tag.bean.Pagination;
import com.hframe.tag.db.DBOperatorProxy;
import com.hframe.tag.util.TagUtils;
import com.hframe.tag.util.TransObjUtil;
import org.apache.velocity.Template;



public class AutoListImpl extends CommonTagImpl{

	private String listId;// form的id
	private String width;// form的宽度
	private String height;// form高度
	private String title; // form是否带有标题
	private String view;// table 或者试图

	private String editable;// 是否可以编辑 true表示可以复选框选择，false表明不能选择，只能查看
	private String initial;// 是否初始化

	private List objects;// 直接传入对象
	private int objectsCount;
	private String condition;// 查询条件

	private List oldObjectsValue;
	private List newObjectsValue;
	private String defaultBtn;


	private Pagination pagination ;
	
	private static Template template;
	

	public AutoListImpl() {
		super();
	}

	public AutoListImpl(String listId, String width, String height,
			String title, String view, String editable, String initial,
			List objects, String condition, List oldObjectsValue,
			List newObjectsValue,String defaultBtn) {
		super();
		this.listId = listId;
		this.width = width;
		this.height = height;
		this.title = title;
		this.view = view;
		this.editable = editable;
		this.initial = initial;
		this.objects = objects;
		this.condition = condition;
		this.oldObjectsValue = oldObjectsValue;
		this.newObjectsValue = newObjectsValue;
		this.defaultBtn=defaultBtn;
		pagination=new Pagination();
		pagination.setIndex(1);
		pagination.setPageSize(10);
		
	}
	
	public AutoListImpl(String listId, String width, String height,
			String title, String view, String editable, String initial,
			List objects, String condition, List oldObjectsValue,
			List newObjectsValue,int pageSize,int pageIndex) {
		super();
		this.listId = listId;
		this.width = width;
		this.height = height;
		this.title = title;
		this.view = view;
		this.editable = editable;
		this.initial = initial;
		this.objects = objects;
		this.condition = condition;
		this.oldObjectsValue = oldObjectsValue;
		this.newObjectsValue = newObjectsValue;
		pagination=new Pagination();
		pagination.setIndex(pageIndex);
		pagination.setPageSize(pageSize);
		
	}

	public int doStart(Writer out) {

		//获取获取视图缓存对象
		Map<String,Object> map = (Map<String, Object>) CacheFactory.get(CacheKeyEnum.DS_LIST_CACHE.getName(), view);
		if(map == null) {
			map = new HashMap();
			map.put(COLUMNS_LIST, DBOperatorProxy.getInstance().getColumnsList(view));
			CacheFactory.put(CacheKeyEnum.DS_LIST_CACHE.getName(), view, map);
		}

//		Map map = SetCacheFactory.get(view);
//		if (map == null) {
//			map = new HashMap();
//			List fieldsList = DBOperatorProxy.getInstance().getFieldsList(view);
//			map.put("fieldsList", fieldsList);
//			SetCacheFactory.setCacheMap(map);
//		}


		map.put("Title", title);
		map.put("ListId", listId);
		map.put("Width", width);
		map.put("Editable", editable);
		map.put("RandomNum", Math.random()*1000);

		List fieldsList = (List) map.get("fieldsList");

		if (condition != null && !condition.equals("")) {
			objects = DBOperatorProxy.getInstance().getObjectsByCondition(condition,
					(String) map.get("CoreSetTables"), pagination.getIndex(), pagination.getPageSize());
			objectsCount= DBOperatorProxy.getInstance().getObjectsByConditionCount(condition,
					(String) map.get("CoreSetTables"));
		}


		if (objects != null) {// 如果前台默认有值，那么我们需要初始化
//			if (oldObjectsValue == null) {
//				oldObjectsValue = new ArrayList();
//			}
			// 将对象的值拼接到表达式中，最终获取field的值
			oldObjectsValue = TransObjUtil.transObjToFields(objects,
					fieldsList);
			for (Object object : oldObjectsValue) {
				List fsList = (List) object;

				for (Object object2 : fsList) {
					Field f = (Field) object2;
					f.getShowExp();
					f.getHiddenExp();
				}
			}

			int blankRowCount = pagination.getPageSize() - oldObjectsValue.size();
			for(int i = 0; i <blankRowCount; i ++) {
				oldObjectsValue.add(new ArrayList());
			}

		}

		if (oldObjectsValue != null && oldObjectsValue.size() > 0) {
			map.put("oldObjectsValue", oldObjectsValue);
			map.put("errorMsg", null);
		}else{
			map.put("oldObjectsValue", oldObjectsValue);
			map.put("errorMsg", "<font color=red>没有找到记录！</font>");
		}

		dealFieldsList(fieldsList);
		
		pagination.setTotle(objectsCount);
		map.put("PaginationDisplay", pagination.getPageDisplay(listId));
		map.put("TagInfo", getTagInfoStr());

		VelocityUtil.produceTemplateContent(
				"com/hframe/tag/vm/autolisttemplate_startpart.vm", map, out);

		return 1;
	}
	public int doEnd(Writer out) {

		if(defaultBtn!=null&&!"".equals(defaultBtn)){
			TagUtils.writeDefaultButton(defaultBtn, out);
		}

		Map map=new HashMap();
		VelocityUtil.produceTemplateContent(
				"com/hframe/tag/vm/autolisttemplate_endpart.vm", map, out);
//		try {
//			out.write("dddddddddddddddddd");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return 1;
	}
	
	private void dealFieldsList(List fieldsList) {

		Pattern p = Pattern.compile("\\$\\{[ a-zA-Z:0-9_]+\\}");
		Matcher m=null;

		for (Object object : fieldsList) {
			Field f=(Field)object;
			String title=f.getTitle();
			if(title==null){
				continue;
			}
			m=p.matcher(title);
			
			String type="";
			String name="";
			String value = null;
			Set<String> expSet=new HashSet<String>(); 

			while(m.find()){
				expSet.add(m.group());
			}
			
			String temp="";
			for (String exp : expSet) {// ${href:1751}
				type=exp.substring(2, exp.indexOf(":"));
				name=exp.substring( exp.indexOf(":")+1,exp.length()-1);
				if("sys".equals(type)){ //field中套用field
					if("allSelect".equals(name)){
						temp="<input type='checkbox' onclick='allSelect_Sys(this)'/>";
						title=title.replace(exp, temp);
					}
				}
			}
			
			f.setTitle(title);
		}
	}

	public int getNextPageData(Writer out) {

		//获取获取视图缓存对象
		Map<String,Object> map = (Map<String, Object>) CacheFactory.get(CacheKeyEnum.DS_LIST_CACHE.getName(), view);
		if(map == null) {
			map = new HashMap();
			map.put(COLUMNS_LIST, DBOperatorProxy.getInstance().getColumnsList(view));
			CacheFactory.put(CacheKeyEnum.DS_LIST_CACHE.getName(), view, map);
		}

		map.put("Title", title);
		map.put("ListId", listId);
		map.put("Width", width);
		map.put("Editable", editable);

		if (condition != null && !condition.equals("")) {
			objects = DBOperatorProxy.getInstance().getObjectsByCondition(condition,
					(String) map.get("CoreSetTables"), pagination.getIndex(), pagination.getPageSize());
			objectsCount= DBOperatorProxy.getInstance().getObjectsByConditionCount(condition,
					(String) map.get("CoreSetTables"));
		}

		List fieldsList = (List) map.get("fieldsList");
		if (objects != null) {// 如果前台默认有值，那么我们需要初始化

			// 将对象的值拼接到表达式中，最终获取field的值
			oldObjectsValue = TransObjUtil.transObjToFields(objects,
					fieldsList);
			if (oldObjectsValue != null && oldObjectsValue.size() > 0) {
				map.put("oldObjectsValue", oldObjectsValue);
			}
		}

		if (oldObjectsValue != null && oldObjectsValue.size() > 0) {
			map.put("oldObjectsValue", oldObjectsValue);
			map.put("errorMsg", null);
		}else{
			map.put("oldObjectsValue", oldObjectsValue);
			map.put("errorMsg", "<font color=red>没有找到记录！</font>");
		}
//		dealFieldsList(fieldsList);
		pagination.setTotle(objectsCount);
//		map.put("PaginationDisplay", pagination.getPageDisplay(listId));
		map.put("TagInfo", getTagInfoStr());

		VelocityUtil.produceTemplateContent(
				"com/hframe/tag/vm/autolisttemplate_forpagination.vm", map, out);
		try {
			out.write("@@");
			out.write(pagination.getPageDisplay(listId));
			out.write("@@");
			out.write(getTagInfoStr());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 1;
	}

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public List getObjects() {
		return objects;
	}

	public void setObjects(List objects) {
		this.objects = objects;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public List getOldObjectsValue() {
		return oldObjectsValue;
	}

	public void setOldObjectsValue(List oldObjectsValue) {
		this.oldObjectsValue = oldObjectsValue;
	}

	public List getNewObjectsValue() {
		return newObjectsValue;
	}

	public void setNewObjectsValue(List newObjectsValue) {
		this.newObjectsValue = newObjectsValue;
	}

	public static Template getTemplate() {
		return template;
	}

	public static void setTemplate(Template template) {
		AutoListImpl.template = template;
	}

	public String getTagInfoStr() {

		return "tag_Type=mylist&tag_Id=" + listId + "&tag_Width=" + width + "&tag_Height=" + height
				+ "&tag_Title=" + title + "&tag_View=" + view + "&tag_Editable=" + editable
				+ "&tag_Initial=" + initial + "&tag_Condition=" + condition+"&tag_DefaultBtn="+defaultBtn;

	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public int getObjectsCount() {
		return objectsCount;
	}

	public void setObjectsCount(int objectsCount) {
		this.objectsCount = objectsCount;
	}

	public String getDefaultBtn() {
		return defaultBtn;
	}

	public void setDefaultBtn(String defaultBtn) {
		this.defaultBtn = defaultBtn;
	}
	
	

}
