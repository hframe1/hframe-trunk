package com.hframe.tag;


import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.velocity.Template;

public class SelectTag extends BodyTagSupport
{
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
	
	private SelectImpl selectImpl;
	
	private static Template template;

	  public int doStartTag()
	    throws JspException
	  {
	    
		selectImpl=new SelectImpl(selectId,width,title,view,condition,editable,visible,onChange,initial,objects,oldObjectValue,newObjectValue);
	    Writer  out=this.pageContext.getOut();

	    selectImpl.doStart(out);

	    
	    return 1;
	  }

	 
	  public int doEndTag() throws JspException {
		  
		  try {
			    Writer  out=this.pageContext.getOut();
			    selectImpl.doEnd(out);
			this.pageContext.getOut().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		    return 6;
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


	public SelectImpl getSelectImpl() {
		return selectImpl;
	}


	public void setSelectImpl(SelectImpl selectImpl) {
		this.selectImpl = selectImpl;
	}


	public static Template getTemplate() {
		return template;
	}


	public static void setTemplate(Template template) {
		SelectTag.template = template;
	}




	
	

	
	  
}