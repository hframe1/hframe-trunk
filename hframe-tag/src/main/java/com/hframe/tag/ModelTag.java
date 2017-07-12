package com.hframe.tag;


import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.velocity.Template;

/**
 * 目前只针对于单表
 * @author zqh
 *
 */
public class ModelTag extends BodyTagSupport
{
	
	private String id;//frame的id
	
	private String size;
	
	private String title; //tree是否带有标题


	private String style;
	
	private String src;
	
	private String defaultBtn;
	
	private ModelImpl modelImpl;
	
	private static Template template;

	  public int doStartTag()
	    throws JspException
	  {
	    Writer out=this.pageContext.getOut();
	    
	    modelImpl = new ModelImpl(id,size,title,style,src,defaultBtn);
	    
	    modelImpl.doStart(out);
	    
	    return 1;
	  }

	 
	  public int doEndTag() throws JspException {
		  
		  try {
			  Writer  out=this.pageContext.getOut();
			  modelImpl.doEnd(out);
			this.pageContext.getOut().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    return 6;
		  }


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getStyle() {
		return style;
	}


	public void setStyle(String style) {
		this.style = style;
	}


	public String getSrc() {
		return src;
	}


	public void setSrc(String src) {
		this.src = src;
	}


	public ModelImpl getModelImpl() {
		return modelImpl;
	}


	public void setModelImpl(ModelImpl modelImpl) {
		this.modelImpl = modelImpl;
	}


	public static Template getTemplate() {
		return template;
	}


	public static void setTemplate(Template template) {
		ModelTag.template = template;
	}


	public String getDefaultBtn() {
		return defaultBtn;
	}


	public void setDefaultBtn(String defaultBtn) {
		this.defaultBtn = defaultBtn;
	}


	

	
	  
}