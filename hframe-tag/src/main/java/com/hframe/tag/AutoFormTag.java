package com.hframe.tag;


import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.velocity.Template;

public class AutoFormTag extends BodyTagSupport
{
	private String formid;//form的id
	private String width;//form的宽度
	private String height;//form高度
	private String title; //form是否带有标题
	private String view;//table 或者试图
	
	private String colNum;
	
	private String editable;//是否可以编辑  true(默认:可以编辑，按照showType展现)，false(全部变灰)，text(是false的一种扩展:纯文本问模式，不能修改）
	private String initial;//是否初始化
	
	private Object object;//直接传入对象
	private String objectId;//传入对象id
	private String condition;//查询条件
	
	private Map oldObjectValue;
	private Map newObjectValue;
	
	private String url;
	private String isAjax;
	private String defaultBtn;

	
	
	private AutoFormImpl autoFormImpl;
	
	private static Template template;

	  public int doStartTag()
	    throws JspException
	  {
	 //   JSHelper.processJS(this.pageContext, (HttpServletRequest)this.pageContext.getRequest(), "AIContractFrame_Tag_Js");
	    
	    
	    autoFormImpl=new AutoFormImpl(formid,width,height,title,view,colNum,editable,initial,object,objectId,condition,oldObjectValue,newObjectValue,url,isAjax,defaultBtn);
	    Writer  out=this.pageContext.getOut();
		  try {
			  autoFormImpl.doStart(out);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }


		  return 1;
	  }

	 
	  public int doEndTag() throws JspException {
		  
		  try {
			    Writer  out=this.pageContext.getOut();
			    autoFormImpl.doEnd(out);
			this.pageContext.getOut().flush();
			//this.pageContext.getOut().clear();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    return 6;
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

	public static Template getTemplate() {
		return template;
	}

	public static void setTemplate(Template template) {
		AutoFormTag.template = template;
	}
	public String getColNum() {
		return colNum;
	}

	public void setColNum(String colNum) {
		this.colNum = colNum;
	}

	  static
	  {
	    try
	    {
	    //	template = Velocity.getTemplate("com/hframe/tag/vm/autoformtemplate.vm", "GBK");
	    } catch (Throwable ex) {
	      throw new RuntimeException(ex);
	    }
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


	public AutoFormImpl getAutoFormImpl() {
		return autoFormImpl;
	}


	public void setAutoFormImpl(AutoFormImpl autoFormImpl) {
		this.autoFormImpl = autoFormImpl;
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