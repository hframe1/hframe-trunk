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
public class FrameTag extends BodyTagSupport
{
	
	private String frameId;//frame的id
	private String isframe="false";
		
	private String width;//tree的宽度
	private String height;//tree高度
	private String title; //tree是否带有标题

	private String src;
	private String style;
	
	private String isTab;
	
	private FrameImpl frameImpl;
	
	private static Template template;

	  public int doStartTag()
	    throws JspException
	  {
	  //  JSHelper.processJS(this.pageContext, (HttpServletRequest)this.pageContext.getRequest(), "AIContractFrame_Tag_Js");
	    Writer out=this.pageContext.getOut();
	    
	    frameImpl=new FrameImpl(frameId,isframe,width,height,title,src,style,isTab);
	    
	    frameImpl.doStart(out);
	    
	    return 1;
	  }

	 
	  public int doEndTag() throws JspException {
		  
		  try {
			//this.pageContext.getOut().write("haaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			this.pageContext.getOut().flush();
		//	this.pageContext.getOut().clear();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    return 6;
		  }


	public String getFrameId() {
		return frameId;
	}


	public void setFrameId(String frameId) {
		this.frameId = frameId;
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


	public String getSrc() {
		return src;
	}


	public void setSrc(String src) {
		this.src = src;
	}


	public static Template getTemplate() {
		return template;
	}


	public static void setTemplate(Template template) {
		FrameTag.template = template;
	}


	public String getIsframe() {
		return isframe;
	}


	public void setIsframe(String isframe) {
		this.isframe = isframe;
	}


	public String getStyle() {
		return style;
	}


	public void setStyle(String style) {
		this.style = style;
	}


	public String getIsTab() {
		return isTab;
	}


	public void setIsTab(String isTab) {
		this.isTab = isTab;
	}


	public FrameImpl getFrameImpl() {
		return frameImpl;
	}


	public void setFrameImpl(FrameImpl frameImpl) {
		this.frameImpl = frameImpl;
	}


	
	  
}