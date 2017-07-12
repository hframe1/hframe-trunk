package com.hframe.tag;

import com.hframework.common.frame.cache.CacheFactory;
import com.hframework.common.frame.cache.CacheKeyEnum;
import com.hframework.common.util.StringUtils;
import com.hframe.tag.bean.Field;
import com.hframe.tag.bean.TreeItem;
import com.hframe.tag.db.DBOperatorProxy;
import com.hframe.tag.util.DHtmlTreeUtil;
import com.hframe.tag.util.TagUtils;
import com.hframe.tag.util.TransObjUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DtreeImpl extends CommonTagImpl{
	
	private String treeId;//tree的id
	private String width;//tree的宽度
	private String height;//tree高度
	private String title; //tree是否带有标题
	private String view;//dbObject 对象table 或者view view 分为form list grid tree menu 等几种类型
	
	private String checkbox;
	
	private String editable;//是否可以编辑
	private String initial;//是否初始化
	
	private List objects;//直接传入对象
	private String rootId;//传入对象id
	private String condition;//查询条件
	
	private String defaultBtn;

	private String onClick;

	public DtreeImpl() {
		super();
	}
	public DtreeImpl(String treeId, String width, String height, String title,
			String view, String checkbox, String editable, String initial,
			List objects, String rootId, String condition,String defaultBtn,String onClick) {
		super();
		this.treeId = treeId;
		this.width = width;
		this.height = height;
		this.title = title;
		this.view = view;
		this.checkbox = checkbox;
		this.editable = editable;
		this.initial = initial;
		this.objects = objects;
		this.rootId = rootId;
		this.condition = condition;
		this.defaultBtn=defaultBtn;
		
		this.onClick=onClick;
	}
	
	public int doStart(Writer out) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException {


		//获取获取视图缓存对象
		Map<String,Object> map = (Map<String, Object>) CacheFactory.get(CacheKeyEnum.DS_TREE_CACHE.getName(), view);
		if(map == null) {
			map = new HashMap();
			map.put(FIELDS_LIST, DBOperatorProxy.getInstance().getTreeColumnsList(view));
			CacheFactory.put(CacheKeyEnum.DS_TREE_CACHE.getName(), view, map);
		}

//
//		Map map= SetCacheFactory.get(view);//menuTree
//		    /*
//		     * view data example:
//		     *   口全选     标题   最后更新时间
//		     * checkbox   href   text
//		     *
//		     *
//		     */
//		    if(map==null){//tree view 主要是有id，pid，title，href,如果是checkbox，那么还有value
//		    	map=new HashMap();
////		    	List columnsList=new CommonServ().getColumnsList(view);
////		    	map.put("columnsList", columnsList);
//		    	List fieldsList= DBOperatorProxy.getInstance().getFieldsList(view);
//		    	map.put("fieldsList", fieldsList);
//		    	SetCacheFactory.setCacheMap(map);
//		    }
		    List fieldsList=(List) map.get("fieldsList");

		    if(StringUtils.isNotBlank(condition)){
		    	//objects=new CommonServ().getTreeElement();
		    	String pid = null;
		    	String id = null;
		    	for (Object o : fieldsList) {
					Field f=(Field)o;
					 if("virtual_pid".equals(f.getType())){
						pid=f.getHiddenExp();
					}else if("virtual_id".equals(f.getType())){
						id=f.getHiddenExp();
					}
				}
//				String popath=(String) SetCacheFactory.get((String) map.get("CoreSetTables")).get("PoPath");//menuTree
				objects= DBOperatorProxy.getInstance().getTreeObjectsByCondition(condition, (String) map.get("CoreSetTables"), id, pid, null);
		    }

		    //将对象的值拼接到表达式中，最终获取field的值
		    List objectFieldsList= TransObjUtil.transObjToFields(objects, fieldsList);

		    String id = null;
		    String pid = null;
		    String name = null;
		    String url = null;
		    String value=null;
		    List<TreeItem> items=new ArrayList<TreeItem>();
		    	
		    try {
		    	//out.write(" <script type=\"text/javascript\">\n");
		    	//out.write("    var "+treeId+" = new dTree('"+treeId+"');\n ");

		    	for (Object object : objectFieldsList) {
					List fsList=(List) object;
					for (Object object2 : fsList) {
						Field f=(Field) object2;

						if("checkbox".equals(f.getType())){
							//out.write("    "+treeId+".config.check=true;\n");
						}else if("virtual_pid".equals(f.getType())){
							pid=f.getHiddenExp();
						}else if("virtual_id".equals(f.getType())){
							id=f.getHiddenExp();
						}else if("virtual_value".equals(f.getType())){
							value=f.getHiddenExp();
						}else{
							name=f.getShowExp();
							url=f.getHiddenExp();
						}
					}
					
					if(url==null||"".equals(url)){
						url="javascript:void(0);";
					}
					
					//注意，这里的value我暂时没有放进去以后有时间补上//TODO
					//out.write(treeId+".add("+id+","+pid+",'"+name+"','"+url+"');\n");
					TreeItem item=new TreeItem(id,pid,id,value,name,"1","0",url,null,null,null,null);
					items.add(item);
				}

		    //	out.write("    document.write("+treeId+");\n");
		    //	out.write(" </script>\n");
		    	
		    	/*
		    	out.write("<div id=\""+this.treeId+"1\" setImagePath=\"third/dhtmlxTree/imgs/\" class=\"dhtmlxTree_zqh\" style=\"width:250px; height:218px; background-color:#f5f5f5;border :1px solid Silver;; overflow:auto;text-align:left;\">");
		    	out.write("<xmp>");
		    	out.write(XmlUtil.getDhtmlTreeXml(items,title));
		    	out.write("</xmp>");
		    	out.write("</div>");
		    	out.write(" <script type=\"text/javascript\">\n");
		    	out.write("var "+this.treeId+"1_Obj=document.getElementById(\""+this.treeId+"1\");");
		    	out.write("var "+this.treeId+"1=dhtmlXTreeFromHTML("+this.treeId+"1_Obj);");
		    	out.write("alert("+this.treeId+"1);");
		    	out.write(" </script>\n");

		    	*/
		    	
		    	if(width==null||"".equals(width)){
		    		width="218";
		    	}
		    	
		    	if(height==null||"".equals(height)){
		    		height="218";
		    	}
		    	
		    	out.write("<input id='"+treeId+"_TAGINFO'  name='"+treeId+"_TAGINFO' type='hidden' value='"+getTagInfoStr()+"'/>");
		    	out.write("<div id=\""+this.treeId+"\"  class=\"dhtmlxTree_zqh\" style=\"width:"+width+"x; height:"+height+"px; ; overflow:auto;text-align:left;padding:10px;\"></div>");
		    	
		    	out.write(" <script type=\"text/javascript\">\n");
		    	out.write("     var "+this.treeId+"=new dhtmlXTreeObject(\""+this.treeId+"\",\"100%\",\"100%\",0);\n");
		    	out.write("     "+this.treeId+".setImagePath(\"third/dhtmlxTree/imgs/\");\n");

		    	if("true".equals(checkbox)){
		    		out.write("     "+this.treeId+".enableCheckBoxes(1);\n");
//					out.write("     " + this.treeId + ".enableTreeImages('-Icons');\n");
//					out.write("     " + this.treeId + ".enableMultiLineItems('30px');\n");


		    	}
		    	out.write("     "+this.treeId+".enableDragAndDrop(1);\n");
		    	if(onClick==null||"".equals(onClick)){
		    		onClick="TreeClick_Sys";
		    	}
		    	out.write("     "+this.treeId+".setOnClickHandler("+onClick+");\n");
		    	

		    	out.write("     "+this.treeId+".loadXMLString(\"<tree id='0'>"+DHtmlTreeUtil.
						getDhtmlTreeXml(items, title, condition).replaceAll("\n", "").replace("\"", "'")+"</tree>\");\n");
		    	

		    	out.write(" </script>\n");
		    	out.write(" <script type=\"text/javascript\">\n");

		    	out.write("     createContextMenu_Sys(\"#"+this.treeId+" span\");");
		    	
		    	out.write(" </script>\n");

		    	out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    
		
		return 1;
	}
	
	public int getDhtmTreeXml(Writer out) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException {

		//获取获取视图缓存对象
		Map<String,Object> map = (Map<String, Object>) CacheFactory.get(CacheKeyEnum.DS_TREE_CACHE.getName(), view);
		if(map == null) {
			map = new HashMap();
			map.put(FIELDS_LIST, DBOperatorProxy.getInstance().getTreeColumnsList(view));
			CacheFactory.put(CacheKeyEnum.DS_TREE_CACHE.getName(), view, map);
		}
		List fieldsList=(List) map.get("fieldsList");
		if(StringUtils.isNotBlank(condition)){
			//objects=new CommonServ().getTreeElement();
			String pid = null;
			String id = null;
			for (Object o : fieldsList) {
				Field f=(Field)o;
				if("virtual_pid".equals(f.getType())){
					pid=f.getHiddenExp();
				}else if("virtual_id".equals(f.getType())){
					id=f.getHiddenExp();
				}
			}
//				String popath=(String) SetCacheFactory.get((String) map.get("CoreSetTables")).get("PoPath");//menuTree
			objects= DBOperatorProxy.getInstance().getTreeObjectsByCondition(condition, (String) map.get("CoreSetTables"), id, pid, null);
		}

		    //将对象的值拼接到表达式中，最终获取field的值
		    List objectFieldsList=TransObjUtil.transObjToFields(objects,fieldsList);


		String id = null;
		String pid = null;
		String name = null;
		String url = null;
		String value=null;
		List<TreeItem> items=new ArrayList<TreeItem>();

		try {
			//out.write(" <script type=\"text/javascript\">\n");
			//out.write("    var "+treeId+" = new dTree('"+treeId+"');\n ");

			for (Object object : objectFieldsList) {
				List fsList=(List) object;
				for (Object object2 : fsList) {
					Field f=(Field) object2;

					if("checkbox".equals(f.getType())){
						//out.write("    "+treeId+".config.check=true;\n");
					}else if("virtual_pid".equals(f.getType())){
						pid=f.getHiddenExp();
					}else if("virtual_id".equals(f.getType())){
						id=f.getHiddenExp();
					}else if("virtual_value".equals(f.getType())){
						value=f.getHiddenExp();
					}else{
						name=f.getShowExp();
						url=f.getHiddenExp();
					}
				}

				if(url==null||"".equals(url)){
					url="javascript:void(0);";
				}

				//注意，这里的value我暂时没有放进去以后有时间补上//TODO
				//out.write(treeId+".add("+id+","+pid+",'"+name+"','"+url+"');\n");
				TreeItem item=new TreeItem(id,pid,id,value,name,"1","0",url,null,null,null,null);
				items.add(item);
			}

			out.write("     document.getElementById(id).innerHTML='';");
		    	out.write("     var "+this.treeId+"=new dhtmlXTreeObject(\""+this.treeId+"\",\"100%\",\"100%\",0);");
		    	out.write("     "+this.treeId+".setImagePath(\"third/dhtmlxTree/imgs/\");");
		    	
		    	if("true".equals(checkbox)){
		    		out.write("     "+this.treeId+".enableCheckBoxes(1);");
		    	}
		    	out.write("     "+this.treeId+".enableDragAndDrop(1);");
		    	if(onClick==null||"".equals(onClick)){
		    		onClick="TreeClick_Sys";
		    	}
		    	out.write("     "+this.treeId+".setOnClickHandler("+onClick+");");
		    	
		    	out.write("     "+this.treeId+".loadXMLString(\"<tree id='0'>"+
						DHtmlTreeUtil.getDhtmlTreeXml(items, title, condition).replaceAll("\n", "").replace("\"", "'")+"</tree>\");\n");
		    	//out.write("     createContextMenu_Sys(\"#"+this.treeId+" span\");");
		    	out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    
		
		return 1;
	}
	
	public String getTagInfoStr() {
//encodeURI(encodeURI(" + title + "))
		String encodeTitle=title;
		try {
			if(title!=null) encodeTitle=URLEncoder.encode(title,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
			return "tag_Type=dtree&tag_Id=" + treeId + "&tag_Width=" + width + "&tag_Height=" + height
					+ "&tag_Title=" + title  + "&tag_View=" + view + "&tag_Editable=" + editable
					+ "&tag_Initial=" + initial + "&tag_Condition=" + condition+"&tag_DefaultBtn="+defaultBtn+"&tag_Checkbox="+checkbox;

	}

	
	public void doEnd(Writer out) {
		if(defaultBtn!=null&&!"".equals(defaultBtn)){
			TagUtils.writeDefaultButton(defaultBtn, out);
		}
	}
	
	public String getTreeId() {
		return treeId;
	}
	public void setTreeId(String treeId) {
		this.treeId = treeId;
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
	public String getCheckbox() {
		return checkbox;
	}
	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
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
	public String getRootId() {
		return rootId;
	}
	public void setRootId(String rootId) {
		this.rootId = rootId;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getDefaultBtn() {
		return defaultBtn;
	}
	public void setDefaultBtn(String defaultBtn) {
		this.defaultBtn = defaultBtn;
	}
	public String getOnClick() {
		return onClick;
	}
	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
	

}
