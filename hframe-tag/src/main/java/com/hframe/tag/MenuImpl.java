package com.hframe.tag;

import com.hframework.common.frame.cache.SetCacheFactory;
import com.hframe.tag.bean.Field;
import com.hframe.tag.db.DBOperatorProxy;
import com.hframe.tag.util.ReflectUtil;
import com.hframe.tag.util.TransObjUtil;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MenuImpl {
	
	private String menuId;//frame的id
	private String width;//tree的宽度
	private String height;//tree高度
	private String title; //tree是否带有标题
	private String view;//dbObject 对象table 或者view view 分为form list grid tree menu 等几种类型
	
	private String canClick;
	private String checkbox;
	
	private List objects;//直接传入对象
	private String condition;//查询条件
	
	private String direction;//direction  1:表示从上到下  2：表示从下到上 3：表示从左到右  4：表示从右到左  默认为1
	
	private String onClick;

	
	

	public MenuImpl() {
		super();
	}

	public MenuImpl(String menuId, String width, String height, String title,
			String view, String canClick, String checkbox, List objects,
			String condition, String direction,String onClick) {
		super();
		this.menuId = menuId;
		this.width = width;
		this.height = height;
		this.title = title;
		this.view = view;
		this.canClick = canClick;
		this.checkbox = checkbox;
		this.objects = objects;
		this.condition = condition;
		this.direction = direction;
		this.onClick=onClick;
	}

	public int doStart(Writer out){
		
		Map map= SetCacheFactory.get(view);//menuTree
	    if(map==null){//tree view 主要是有id，pid，title，href,如果是checkbox，那么还有value
	    	map=new HashMap();
//	    	List columnsList=new CommonServ().getColumnsList(view);
//	    	map.put("columnsList", columnsList);
	    	List fieldsList= DBOperatorProxy.getInstance().getFieldsList(view);
	    	map.put("fieldsList", fieldsList);
	    	SetCacheFactory.setCacheMap(map);
	    }
	    List fieldsList=(List) map.get("fieldsList");
	    
	    //获取该树型节点的连接字段一般是parent_id
	    String virtual_pid = null;
	    for (Object object : fieldsList) {
	    	Field f=(Field) object;
	    	 if("virtual_pid".equals(f.getType())){
	    		virtual_pid=f.getHiddenExp();
	    	}
		}
	    
	    Map<String, List> maps=new HashMap<String, List>();
	    
	    if(condition!=null&&!"".equals(condition)&&objects==null){
	    	//objects=new CommonServ().getTreeElement();
	    	objects= DBOperatorProxy.getInstance().getObjectsByCondition(condition, (String) map.get("CoreSetTables"), null);
	    	
	    }
	    
	    
	    if(objects!=null){
	    	 for (Object object2 : objects) {
	    		 String parValue = null;
				try {
					parValue = (String) ReflectUtil.invokeGetMethod(virtual_pid, object2, null);
				} catch (Exception e) {
					e.printStackTrace();
				} 
	    		 List list=maps.get(parValue);
	    		 if(list==null){
	    			 list=new ArrayList();
	    		 }
	    		 list.add(object2);
	    		 maps.put(parValue, list);
	 		}
	    }
	    
	  
	    
	    try {
			out.write("<div id=\""+menuId+"\" class=\"menu\">");
			printTheMenu(out,maps,fieldsList);
			out.write("<script type=\"text/javascript\">injectMenuEvent(\""+this.menuId+"\");</script>");
			out.write("</div>");

			map.put("MenuId", this.menuId);

		    //VelocityUtil.produceTemplateContent("com/hframe/tag/vm/js_menutemplate.vm",map,out);

		} catch (IOException e) {
			e.printStackTrace();
		}


		
		return 1;
	}
	
	private void printTheMenu(Writer out, Map<String, List> maps,
			List fieldsList) {
		  
		  List level1List=maps.get("-1");
		  
		    
		    
		    String id = null;
		    String pid = null;
		    String name = null;
		    String url = null;
		    
		    List objectFieldsList= TransObjUtil.transObjToFields(level1List, fieldsList);
		    
		    int i=0;
		    try {
		    	for (Object object : objectFieldsList) {
		    		i++;
					List fsList=(List) object;
					for (Object object2 : fsList) {
						Field f=(Field) object2;
						
						if("virtual_pid".equals(f.getType())){
							pid=f.getHiddenExp();
						}else if("virtual_id".equals(f.getType())){
							id=f.getHiddenExp();
						}else{
							name=f.getShowExp();
							url=f.getHiddenExp();
						}
						
					}
					if(onClick!=null&&!onClick.equals("")){
						
						url="javascript:"+onClick+"('"+id+"','"+url+"');";
					}
					out.write("<div id=\""+menuId+"_menu"+i+"\" class=\"menusel\">\n");
					if(!"2".equals(direction)){
						out.write("<h2><a href=\""+url+"\">"+name+"</a></h2>\n");
					}
					int subEle=0;
					if(maps.get(id)!=null) subEle=maps.get(id).size();
					
					if(subEle!=0){
						
					
					out.write("<div class=\"position\">\n");
					out.write("<ul class=\"clearfix typeul\">\n");
					
				
					
					//下面开始处理第二级以及以后级别数据处理
					
					printTheMenuCascade(out,id,maps,fieldsList);
					
					
//					<li><a href="">我是菜单选项3-2</a></li>
//					<li><a href="">我是菜单选项3-2</a>
//						<ul>
//							<li class="fli"><a href="">我是菜单选项3-2-1</a></li>
//							<li class="lli"><a href="">我是菜单选项3-2-2</a>
//								<ul>
//									<li class="fli"><a href="">我是菜单选项3-2-1</a></li>
//									<li class="lli"><a href="">我是菜单选项3-2-2</a>
//										<ul>
//											<li class="fli"><a href="">我是菜单选项3-2-1</a></li>
//											<li class="lli"><a href="">我是菜单选项3-2-2</a></li>
//										</ul>
//									</li>
//								</ul>
//							</li>
//						</ul>
//					</li>
//					<li><a href="">我是菜单选项3-2</a></li>
//					<li class="lli"><a href="">我是菜单选项3-2</a></li>
					
					out.write("</ul>\n");
					out.write("</div>\n");
					
					}
					if("2".equals(direction)){
						out.write("<h2><a href=\""+url+"\">"+name+"</a></h2>\n");
					}
					
					out.write("</div>\n");
					
				}
		    	
		    }catch (Exception e) {
				e.printStackTrace();
			}
		    	
		  
		  
	}


	private void printTheMenuCascade(Writer out, String pid,
			Map<String, List> maps, List fieldsList) {

		  List levelList=maps.get(pid);
		  
		  if(levelList==null||levelList.size()==0){
			  return ;
		  }
		  
		    String id = null;
		    String name = null;
		    String url = null;
		    
		    List objectFieldsList=TransObjUtil.transObjToFields(levelList,fieldsList);
		    
		    int i=0;
		    try {
		    	for (Object object : objectFieldsList) {
		    		i++;
					List fsList=(List) object;
					for (Object object2 : fsList) {
						Field f=(Field) object2;
						
						if("virtual_id".equals(f.getType())){
							id=f.getHiddenExp();
						}else{
							name=f.getShowExp();
							url=f.getHiddenExp();
						}
						
					}
					
					String className="";
					if(i==1){
						className="fli";
					}
					
					if(maps.containsKey(id)){
						className="lli";
					}
					
					
					
					out.write("<li class=\""+className+"\"><a href=\""+url+"\">"+name+"</a>\n");
					
					if(className=="fli"){
						out.write("</li>\n");
					}else if(className=="lli"){
						//out.write("</li>\n");
						out.write("<ul>\n");
						printTheMenuCascade(out,id,maps,fieldsList);
						out.write("	</ul>\n");
						out.write("</li>\n");
					}else{
						out.write("</li>\n");
					}
				}
		    	
		    }catch (Exception e) {
			}

		
		
	}
	
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
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

	public String getCanClick() {
		return canClick;
	}

	public void setCanClick(String canClick) {
		this.canClick = canClick;
	}

	public String getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
	
	
	
	

}
