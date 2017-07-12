package com.hframe.tag;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hframework.common.frame.cache.CacheFactory;
import com.hframework.common.frame.cache.CacheKeyEnum;
import com.hframework.common.util.StringUtils;
import com.hframe.tag.util.ClassDeclaredUtils;
import com.hframe.tag.db.DBOperatorProxy;
import com.hframe.tag.util.TagUtils;
import com.hframe.tag.util.TransObjUtil;
import com.hframework.common.util.message.VelocityUtil;

public class AutoFormImpl extends CommonTagImpl{

	private String formid;// form的id
	private String width;// form的宽度
	private String height;// form高度
	private String title; // form是否带有标题
	private String view;// table 或者试图

	private String colNum;

	private String editable;// 是否可以编辑
							// true(默认:可以编辑，按照showType展现)，false(全部变灰)，text(是false的一种扩展:纯文本问模式，不能修改）
	private String initial;// 是否初始化

	private Object object;// 直接传入对象
	private String objectId;// 传入对象id
	private String condition;// 查询条件

	private Map oldObjectValue;
	private Map newObjectValue;

	private String url;
	private String isAjax = "false";
	private String defaultBtn;

	public AutoFormImpl(String formid, String width, String height,
			String title, String view, String colNum, String editable,
			String initial, Object object, String objectId, String condition,
			Map oldObjectValue, Map newObjectValue, String url, String isAjax,
			String defaultBtn) {
		super();
		this.formid = formid;
		this.width = width;
		this.height = height;
		this.title = title;
		this.view = view;
		this.colNum = colNum;
		this.editable = editable;
		this.initial = initial;
		this.object = object;
		this.objectId = objectId;
		this.condition = condition;
		this.oldObjectValue = oldObjectValue;
		this.newObjectValue = newObjectValue;
		this.url = url;
		this.isAjax = isAjax;
		this.defaultBtn = defaultBtn;
	}

	public int doStart(Writer out) throws Exception {

		String cacheName;
		if(!"true".equals(editable)) {
			cacheName = CacheKeyEnum.DS_SHOW_CACHE.getName();
		}else if(object != null || StringUtils.isNotBlank(objectId)){
			cacheName = CacheKeyEnum.DS_MODIFY_CACHE.getName();
		}else {
			cacheName = CacheKeyEnum.DS_CREATE_CACHE.getName();
		}

		//获取获取视图缓存对象
		Map<String,Object> map = (Map<String, Object>) CacheFactory.get(cacheName, view);
		if(map == null) {
			map = new HashMap();
			map.put(COLUMNS_LIST, DBOperatorProxy.getInstance().getColumnsList(view));
			CacheFactory.put(CacheKeyEnum.DS_CREATE_CACHE.getName(),view,map);
			CacheFactory.put(CacheKeyEnum.DS_MODIFY_CACHE.getName(),view,map);
			CacheFactory.put(CacheKeyEnum.DS_SHOW_CACHE.getName(),view,map);
		}

		map.put("Title", title);
		map.put("ColNum", Integer.parseInt(colNum));
		map.put("FormId", formid);
		map.put("Editable", editable);
		map.put("IsAjax", isAjax);
		map.put("RandomNum", Math.random()*1000);

		url = initUrl(view);
		map.put("Url", url);

		if (StringUtils.isNotBlank(objectId )) {
			List objects = DBOperatorProxy.getInstance().getObjectsByPk(
					objectId, (String)map.get(REL_ENTITYS),(String) map.get(PRIMARY_KEY));
			object = objects != null && objects.size() > 0 ? objects.get(0) : object;
		}

		if (object != null) {// 如果前台默认有值，那么我们需要初始化
			List columnsList = (List) map.get(COLUMNS_LIST);
			oldObjectValue = parseObject2Map(view,columnsList,object);
		}

		map.put("oldObjectValue", oldObjectValue);
		map.put("TagInfo", getTagInfoStr());

		VelocityUtil.produceTemplateContent(
				"com/hframe/tag/vm/autoformtemplate_startpart.vm", map,
				out);

		return 1;

	}

	private String initUrl(String view) {
		if (url == null || "".equals(url)) {
			url = "Hframe/"
					+ ClassDeclaredUtils.getJavaVarName(view).toLowerCase()
					+ "/_"
					+ ClassDeclaredUtils.getJavaVarName(view).toLowerCase()
					+ "_create";
		}
		return url;
	}

	private Map parseObject2Map(String view, List columnsList, Object object) {
		try {
			oldObjectValue = TransObjUtil.transObjToMap(view, columnsList,
					object);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return oldObjectValue;
	}

	public String getTagInfoStr() {
		return "tag_Type=myform&tag_Id=" + formid + "&tag_Width=" + width + "&tag_Height=" + height
				+ "&tag_Title=" + title + "&tag_View=" + view + "&tag_ColNum=" + colNum
				+ "&tag_Editable" + editable + "&initial=" + initial + "&tag_ObjectId="
				+ objectId + "&tag_Condition=" + condition + "&tag_Url=" + url
				+ "&tag_DefaultBtn=" + defaultBtn;
		

	}

	public int doEnd(Writer out) throws IOException {
		Map map = new HashMap();
		if (defaultBtn != null && !"".equals(defaultBtn)) {
			map.put("buttonInfo",
					TagUtils.getDefaultButtonHtmlStr(defaultBtn));
		}
		
		VelocityUtil
				.produceTemplateContent(
						"com/hframe/tag/vm/autoformtemplate_endpart.vm",
						map, out);

		return 1;

	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
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

	public String getColNum() {
		return colNum;
	}

	public void setColNum(String colNum) {
		this.colNum = colNum;
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

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIsAjax() {
		return isAjax;
	}

	public void setIsAjax(String isAjax) {
		this.isAjax = isAjax;
	}

	public String getDefaultBtn() {
		return defaultBtn;
	}

	public void setDefaultBtn(String defaultBtn) {
		this.defaultBtn = defaultBtn;
	}

}
