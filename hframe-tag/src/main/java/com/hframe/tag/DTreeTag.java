package com.hframe.tag;


import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.velocity.Template;

/**
 * 目前只针对于单表
 * @author zqh
 *
 */
public class DTreeTag extends BodyTagSupport
{
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

	private DtreeImpl dtreeImpl;
	
	private String onClick;
	
	private static Template template;

	  public int doStartTag()
	    throws JspException
	  {
	   // JSHelper.processJS(this.pageContext, (HttpServletRequest)this.pageContext.getRequest(), "AIContractFrame_Tag_Js");
	    
	    dtreeImpl=new DtreeImpl(treeId,width,height,title,view,checkbox,editable,initial,objects,rootId,condition,defaultBtn,onClick);
	    Writer out=this.pageContext.getOut();

		  try {
			  dtreeImpl.doStart(out);
		  } catch (InvocationTargetException e) {
			  e.printStackTrace();
		  } catch (NoSuchMethodException e) {
			  e.printStackTrace();
		  } catch (ClassNotFoundException e) {
			  e.printStackTrace();
		  } catch (IllegalAccessException e) {
			  e.printStackTrace();
		  }


		  return 1;
	  }

	 
	  public int doEndTag() throws JspException {
		    Writer out=this.pageContext.getOut();
		    dtreeImpl.doEnd(out);

		  try {
			this.pageContext.getOut().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    return 6;
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


	public DtreeImpl getDtreeImpl() {
		return dtreeImpl;
	}


	public void setDtreeImpl(DtreeImpl dtreeImpl) {
		this.dtreeImpl = dtreeImpl;
	}


	public static Template getTemplate() {
		return template;
	}


	public static void setTemplate(Template template) {
		DTreeTag.template = template;
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