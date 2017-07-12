package com.hframe.tag;


import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.velocity.Template;

public class AutoGridTag extends BodyTagSupport
{
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
	private String isAjax;
	private String defaultBtn;
	private String param;
	
	private AutoGridImpl autoGridImpl;
	
	private static Template template;

	  public int doStartTag()
	    throws JspException
	  {
	  //  JSHelper.processJS(this.pageContext, (HttpServletRequest)this.pageContext.getRequest(), "AIContractFrame_Tag_Js");
	    
	    Writer out=this.pageContext.getOut();
	    
	    autoGridImpl=new AutoGridImpl(gridId,width,height,title,view,editable,initial,objects,condition,oldObjectsValue,newObjectsValue,url,isAjax,defaultBtn,param);

		  try {
			  autoGridImpl.doStart(out);
		  } catch (ClassNotFoundException e) {
			  e.printStackTrace();
		  } catch (InvocationTargetException e) {
			  e.printStackTrace();
		  } catch (IllegalAccessException e) {
			  e.printStackTrace();
		  }

		  return 1;
	  }

	

	public int doEndTag() throws JspException {
	    Writer out=this.pageContext.getOut();
	    autoGridImpl.doEnd(out);

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
		AutoGridTag.template = template;
	}

	public String getGridId() {
		return gridId;
	}

	public void setGridId(String gridId) {
		this.gridId = gridId;
	}

	public List getObjects() {
		return objects;
	}

	public void setObjects(List objects) {
		this.objects = objects;
	}

	
	  static
	  {
	    try
	    {
	    	//template = Velocity.getTemplate("com/hframe/tag/vm/autogridtemplate.vm", "GBK");
	    } catch (Throwable ex) {
	      throw new RuntimeException(ex);
	    }
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



	public AutoGridImpl getAutoGridImpl() {
		return autoGridImpl;
	}



	public void setAutoGridImpl(AutoGridImpl autoGridImpl) {
		this.autoGridImpl = autoGridImpl;
	}



	public String getParam() {
		return param;
	}



	public void setParam(String param) {
		this.param = param;
	}

	 
	
	
	  
}