package com.hframe.tag;

import com.hframework.common.frame.cache.CacheFactory;
import com.hframework.common.frame.cache.CacheKeyEnum;
import com.hframework.common.util.StringUtils;
import com.hframe.tag.util.ClassDeclaredUtils;
import com.hframework.common.util.message.VelocityUtil;
import com.hframe.tag.db.DBOperatorProxy;
import com.hframe.tag.util.TagUtils;
import com.hframe.tag.util.TransObjUtil;

import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class AutoGridImpl extends  CommonTagImpl{

	private String gridId;//form的id
	private String width;//form的宽度
	private String height;//form高度
	private String title; //form是否带有标题
	private String view;//table 或者试图
	
	
	private String editable;//是否可以编辑  true(默认:可以编辑，按照showType展现)，false(全部变灰)，text(是false的一种扩展:纯文本问模式，不能修改）
	private String initial;//是否初始化
	
	private List objects;//直接传入对象
	private String condition;//查询条件
	
	private List oldObjectsValue;
	private List newObjectsValue;
	
	private String url;
	private String isAjax="false";
	private String defaultBtn;
	
	private String param;

	
	
	public AutoGridImpl() {
		super();
	}
	public AutoGridImpl(String gridId, String width, String height,
			String title, String view, String editable, String initial,
			List objects, String condition, List oldObjectsValue,
			List newObjectsValue) {
		super();
		this.gridId = gridId;
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
	}
	
	
	public AutoGridImpl(String gridId, String width, String height,
			String title, String view, String editable, String initial,
			List objects, String condition, List oldObjectsValue,
			List newObjectsValue, String url, String isAjax,String defaultBtn) {
		super();
		this.gridId = gridId;
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
		this.url = url;
		this.isAjax = isAjax;
		this.defaultBtn=defaultBtn;
	}
	
	
	public AutoGridImpl(String gridId, String width, String height,
			String title, String view, String editable, String initial,
			List objects, String condition, List oldObjectsValue,
			List newObjectsValue, String url, String isAjax,String defaultBtn,String param) {
		super();
		this.gridId = gridId;
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
		this.url = url;
		this.isAjax = isAjax;
		this.defaultBtn=defaultBtn;
		this.param = param;
	}
	
	
	public int doStart(Writer out) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {


		String cacheName;
		if(!"true".equals(editable)) {
			cacheName = CacheKeyEnum.DS_SHOW_CACHE.getName();
		}else if(objects != null || StringUtils.isNotEmpty(condition)){
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
	    map.put("GridId", gridId);
	    map.put("Width", width);
	    map.put("Editable", editable);
	    map.put("IsAjax", isAjax);
		map.put("RandomNum", Math.random()*1000);

		url = initUrl(view);
		map.put("Url", url);

		List columnsList=(List) map.get("columnsList");
		if(StringUtils.isNotEmpty(condition)) {
			String poPath=(String) map.get("PoPath");
			Class c = Class.forName(poPath);
			TransObjUtil.reBuildColumnsList(columnsList, param);
			objects = DBOperatorProxy.getInstance().getObjectsByCondition(condition, view, c);
		}

	    if(objects!=null){//如果前台默认有值，那么我们需要初始化
	    	oldObjectsValue=new ArrayList();
			for (Object object : objects) {
				Map oldObjectValue= TransObjUtil.transObjToMap(view, columnsList, object, param);
				if(oldObjectValue!=null) {
					oldObjectsValue.add(oldObjectValue);
				}
			}
	    }
		if(oldObjectsValue == null || oldObjectsValue.size() == 0) {
			oldObjectsValue = new ArrayList();
			oldObjectsValue.add(new HashMap());
		}

		map.put("oldObjectsValue", oldObjectsValue);//map初始化 不然是上一个map里面的数据

		map.put("TagInfo", getTagInfoStr());
		map.put("GlobalParam", condition);
		map.put("Param", param);
	    VelocityUtil.produceTemplateContent("com/hframe/tag/vm/autogridtemplate_startpart.vm", map, out);
		
		return 1;
	}

	private String initUrl(String view) {
		if(url==null||"".equals(url)){
			url="Hframe/"+ ClassDeclaredUtils.getJavaVarName(view).toLowerCase()+"/_"+ ClassDeclaredUtils.getJavaVarName(view).toLowerCase()+"_batchCreate";
		}
		return url;
	}

	public String getTagInfoStr() {
		return "tag_Type=mygrid&tag_Id=" + gridId + "&tag_Width=" + width + "&tag_Height=" + height
				+ "&tag_Title=" + title + "&tag_View=" + view 
				+ "&tag_Param=" + param
				+ "&tag_Editable=" + editable + "&initial=" + initial 
				+ "&tag_Condition=" + condition + "&tag_Url=" + url
				+ "&tag_DefaultBtn=" + defaultBtn;
		

	}
	
	
	public int doEnd(Writer out){
		
		Map map=new HashMap();
		
		if("true".equals(editable)){
//			try {
//				out.write("</td></tr><tr><td colspan=100% align=\"right\">	<input class=\"zqh_form_add\" type=\"button\" value=\"复制一行\" onclick=\"btnClk_Sys(this,'current-copyOneRow','test/createDialog.jsp',0,'{param=?&targetObj=&targetType=}')\">	<input class=\"zqh_form_add\" type=\"button\" value=\"添加一行\" onclick=\"btnClk_Sys(this,'current-addOneRow','test/createDialog.jsp',0,'{param=?&targetObj=&targetType=}')\"></td></tr><tr><td class='zqh_form_footer' colspan=100%>");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		
		if(defaultBtn!=null&&!"".equals(defaultBtn)){
			TagUtils.writeDefaultButton(defaultBtn, out);
		}
		VelocityUtil.produceTemplateContent("com/hframe/tag/vm/autogridtemplate_endpart.vm",map,out);
		
		return 1;

	}
	
	public String getGridId() {
		return gridId;
	}
	public void setGridId(String gridId) {
		this.gridId = gridId;
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
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	
	
	
}
