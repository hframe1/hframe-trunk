package com.hframe.tag;

import com.hframework.common.frame.cache.SetCacheFactory;
import com.hframework.common.util.message.VelocityUtil;
import com.hframe.tag.bean.EnumDyn;
import com.hframe.tag.db.DBOperatorProxy;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SelectImpl {

	private String selectId;
	private String width;
	private String title; //
	private String view;//
	private String condition;//查询条件
	private String editable;//是否可以编辑  true(默认:可以编辑，按照showType展现)，false(全部变灰)，text(是false的一种扩展:纯文本问模式，不能修改）
	private String visible;
	
	private String onChange;
	
	private String initial;//是否初始化
	
	private List objects;//直接传入对象
	
	private Map oldObjectValue;
	private Map newObjectValue;
	
	
	
	public SelectImpl() {
		super();
	}

	public SelectImpl(String selectId, String width, String title, String view,
			String condition, String editable, String visible, String onChange,
			String initial, List objects, Map oldObjectValue,
			Map newObjectValue) {
		super();
		this.selectId = selectId;
		this.width = width;
		this.title = title;
		this.view = view;
		this.condition = condition;
		this.editable = editable;
		this.visible = visible;
		this.onChange = onChange;
		this.initial = initial;
		this.objects = objects;
		this.oldObjectValue = oldObjectValue;
		this.newObjectValue = newObjectValue;
	}


	public String getTagInfoStr() {
		return "tag_Type=select&tag_Id=" + selectId + "&tag_Width=" + width 
				+ "&tag_Title=" + title + "&tag_View=" + view 
				+ "&tag_Editable" + editable + "&initial=" + initial+ "&tag_Condition=" + condition ;
		

	}




	public int doStart(Writer out) {

		oldObjectValue = new HashMap();// map初始化 不然是上一个map里面的数据

		Map map = SetCacheFactory.get(view, "element");

		if(title==null){
			title=(String) map.get("Title");
		}
		map.put("View", view);
		map.put("Title", title);
		map.put("selectId", selectId);
		map.put("Width", width);
		map.put("Editable", editable);
		map.put("optionList", map.get("optionList"));
		map.put("oldObjectValue", oldObjectValue);
		if(map.get("EnumDyn")!=null){
			if(initial==null){
				initial="false";
			}
			
			if(initial.equals("false")){
				map.put("onclick", "refresh_Sys('','"+selectId+"')");
			}else{
				map.put("Onload", "<script type=\"text/javascript\">refresh_Sys('','"+selectId+"');</script>");
			}
		}

		if(onChange!=null){
			map.put("onchange", onChange);
		}

		map.put("TagInfo", getTagInfoStr());


		VelocityUtil.produceTemplateContent(
				"com/hframe/tag/vm/select.vm", map,
				out);

		return 1;

	}
	
	public int getAjaxContent(Writer out)  {
		Map viewMap = SetCacheFactory.get(view, "element");
		EnumDyn coreEnumDyn=(EnumDyn) viewMap.get("EnumDyn");
		String keyStr,valStr;
		String sqlStr=coreEnumDyn.getSql();
		
		keyStr=sqlStr.substring(sqlStr.toLowerCase().indexOf("select")+7,sqlStr.toLowerCase().indexOf("from")).split(",")[0].trim();
		valStr=sqlStr.substring(sqlStr.toLowerCase().indexOf("select")+7,sqlStr.toLowerCase().indexOf("from")).split(",")[1].trim();

		
		if(coreEnumDyn.getCondition()!=null){
			sqlStr+=" where "+coreEnumDyn.getCondition()+" = "+condition;
		}
		List result = DBOperatorProxy.getInstance().getObjectBySql(sqlStr, null);
		for (Object object : result) {
			Map map=(Map)object;
			String value=(String) map.get(keyStr);
			String text=(String) map.get(valStr);

			try {
				out.write("<option value='"+value+"'>"+text+"</option>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 1;
	
	}

	public int doEnd(Writer out) {

		return 1;

	}

	public String getSelectId() {
		return selectId;
	}

	public void setSelectId(String selectId) {
		this.selectId = selectId;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
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




	public String getVisible() {
		return visible;
	}




	public void setVisible(String visible) {
		this.visible = visible;
	}




	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
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

	public Map getOldObjectValue() {
		return oldObjectValue;
	}

	public void setOldObjectValue(Map oldObjectValue) {
		this.oldObjectValue = oldObjectValue;
	}

	public Map getNewObjectValue() {
		return newObjectValue;
	}

	public void setNewObjectValue(Map newObjectValue) {
		this.newObjectValue = newObjectValue;
	}


	
}
