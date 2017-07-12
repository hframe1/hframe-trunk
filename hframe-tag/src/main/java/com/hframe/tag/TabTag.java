package com.hframe.tag;

import com.hframe.tag.bean.TabGroup;

import java.io.Writer;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;


public class TabTag extends BodyTagSupport{
	
	private String tabId;
	private String width;//tree的宽度
	private String height;//tree高度
	private String view;//dbObject 对象table 或者view view 分为form list grid tree menu 等几种类型
	
	
	private List objects;//直接传入对象
	private String condition;//查询条件
	private TabGroup tabGroup;
	
	private TabImpl tabImpl;
	
	
	
	@Override
	public int doStartTag() throws JspException {

	    Writer out=this.pageContext.getOut();
	    
	    tabImpl=new TabImpl(tabId,width,height,view,objects,condition,tabGroup);
	    
	    tabImpl.doStart(out);
	   
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
	public TabImpl getTabImpl() {
		return tabImpl;
	}
	public void setTabImpl(TabImpl tabImpl) {
		this.tabImpl = tabImpl;
	}
	

	

}
