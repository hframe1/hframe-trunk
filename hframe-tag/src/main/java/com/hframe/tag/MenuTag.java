package com.hframe.tag;


import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.velocity.Template;

/**
 * 目前只针对于单表
 * @author zqh
 *
 */
public class MenuTag extends BodyTagSupport
{
	
	
	
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
	
	private static Template template;
	
	private String onClick;

	private MenuImpl menuImpl;

	  public int doStartTag()
	    throws JspException
	  {
//	    JSHelper.processJS(this.pageContext, (HttpServletRequest)this.pageContext.getRequest(), "AIContractFrame_Tag_Js");
	    Writer out=this.pageContext.getOut();
	    
	    menuImpl=new MenuImpl(menuId,width,height,title,view,canClick,checkbox,objects,condition,direction,onClick);
	    
	    menuImpl.doStart(out);
	    
	    return 1;
	  }

	 

	public int doEndTag() throws JspException {
		  
		  try {
			//this.pageContext.getOut().write("haaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			this.pageContext.getOut().flush();
//			this.pageContext.getOut().clear();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    return 6;
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


	public static Template getTemplate() {
		return template;
	}


	public static void setTemplate(Template template) {
		MenuTag.template = template;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}


	public MenuImpl getMenuImpl() {
		return menuImpl;
	}


	public void setMenuImpl(MenuImpl menuImpl) {
		this.menuImpl = menuImpl;
	}



	public String getOnClick() {
		return onClick;
	}



	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}


	
	  
}