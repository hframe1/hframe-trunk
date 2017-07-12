package com.hframe.tag;

import com.hframe.tag.bean.TabGroup;
import com.hframe.tag.bean.TabItem;
import com.hframe.tag.db.DBOperatorProxy;

import java.io.IOException;
import java.io.Writer;
import java.util.List;


public class TabImpl {


	private String tabId;
	private String width;//tree的宽度
	private String height;//tree高度
	private String view;//dbObject 对象table 或者view view 分为form list grid tree menu 等几种类型
	
	
	private List objects;//直接传入对象
	private String condition;//查询条件
	private TabGroup tabGroup;
	
	
	public TabImpl() {
		super();
	}
	public TabImpl(String tabId, String width, String height, String view,
			List objects, String condition, TabGroup tabGroup) {
		super();
		this.tabId = tabId;
		this.width = width;
		this.height = height;
		this.view = view;
		this.objects = objects;
		this.condition = condition;
		this.tabGroup = tabGroup;
	}
	
	public int doStart(Writer out){
		tabGroup= DBOperatorProxy.getInstance().getBackOperation();
		 try {
				out.write("<div  class='tab_title_div' style=\"\">\n");
				out.write("<ul class=\"nav nav-tabs\" role=\"tablist\">\n");
		    	int i=1;
				for (TabItem tabItem : tabGroup.getTabItems()) {
					out.write("<li role=\"presentation\"  class=' tab_title " + (i==1?"active":"")+ "' id='tab_title_"+i+"'>" +
							"<a href=\"javascript:void(0);\" aria-controls=\"111\" role=\"tab\" data-toggle=\"tab\">"
							+tabItem.getTitle()+"</a></li>\n");
					i++;
				}
				out.write("</ul>\n");
				out.write("	<div class=\"tab-content\">\n");
				
				i=1;
				for (TabItem tabItem : tabGroup.getTabItems()) {
					
					out.write("<div role=\"tabpanel\" class='tab_content_current tab-pane fade " + (i==1?" in active":"")+ "' id='tab_content_"+i+"' >\n");
					
					if("false".equals(tabItem.getIsIframe())){
						if(tabItem.getJsp().startsWith("$(")){
							
							out.write("<div id='"+tabItem.getJsp().substring(4,tabItem.getJsp().length()-2)+"_TABDIV"+"'></div>");
							
							out.write("<script type=\"text/javascript\">$(function(){"+tabItem.getJsp().substring(0,tabItem.getJsp().length()-2)+"_TABDIV')"+".html("+tabItem.getJsp()+".html());"+tabItem.getJsp()+".remove(); });	</script>");
						}else {
							out.write("<jsp:include page=\""+tabItem.getJsp()+"\"></jsp:include>");
						}
					}else{
						
						new FrameImpl("HFRAME_IFRAME_"+i,"false",tabItem.getWidth()+"",tabItem.getHeight()+"",null,tabItem.getJsp(),tabItem.getStyle(),null).doStart(out);
						
//						out.write("<zqh:frame height=\""+tabItem.getHeight()+"\" style=\""+tabItem.getStyle()+"\" width=\""+tabItem.getWidth()+"\" frameId=\"myframe2\" src=\""+tabItem.getJsp()+"\"></zqh:frame>\n");
					}
					out.write("</div>\n");
					i++;
				}
				out.write(	"</div>");
				out.write("</div>");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		

		return 1;
	}
	
	public String getTabId() {
		return tabId;
	}
	public void setTabId(String tabId) {
		this.tabId = tabId;
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
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
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
	public TabGroup getTabGroup() {
		return tabGroup;
	}
	public void setTabGroup(TabGroup tabGroup) {
		this.tabGroup = tabGroup;
	}
	
	
	
	
}
