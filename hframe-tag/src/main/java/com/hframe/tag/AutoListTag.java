package com.hframe.tag;


import java.io.Writer;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.velocity.Template;

public class AutoListTag extends BodyTagSupport
{
	private String listId;//form的id
	private String width;//form的宽度
	private String height;//form高度
	private String title; //form是否带有标题
	private String view;//table 或者试图
	
	
	private String editable;//是否可以编辑  true表示可以复选框选择，false表明不能选择，只能查看
	private String initial;//是否初始化
	private String defaultBtn;
	
	private List objects;//直接传入对象
	private String condition;//查询条件
	
	private List oldObjectsValue;
	private List newObjectsValue; 

	
	private AutoListImpl autoListImpl;
	
	private static Template template;

	  public int doStartTag()
	    throws JspException
	  {
//	    JSHelper.processJS(this.pageContext, (HttpServletRequest)this.pageContext.getRequest(), "AIContractFrame_Tag_Js");
	    
	    Writer out=this.pageContext.getOut();
	    
	    autoListImpl=new AutoListImpl(listId,width,height,title,view,editable,initial,objects,condition,oldObjectsValue,newObjectsValue,defaultBtn);
	    
	    autoListImpl.doStart(out);
	    
	    
	    return 1;
	  }

	

		public int doEndTag() throws JspException {
		    Writer out=this.pageContext.getOut();
		    autoListImpl.doEnd(out);

		    return 6;
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


	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public static Template getTemplate() {
		return template;
	}

	public static void setTemplate(Template template) {
		AutoListTag.template = template;
	}

	

	public String getListId() {
		return listId;
	}



	public void setListId(String listId) {
		this.listId = listId;
	}



	public List getObjects() {
		return objects;
	}

	public void setObjects(List objects) {
		this.objects = objects;
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






	public AutoListImpl getAutoListImpl() {
		return autoListImpl;
	}



	public void setAutoListImpl(AutoListImpl autoListImpl) {
		this.autoListImpl = autoListImpl;
	}


	public String getDefaultBtn() {
		return defaultBtn;
	}

	public void setDefaultBtn(String defaultBtn) {
		this.defaultBtn = defaultBtn;
	}
	
	
	
	
	  
}