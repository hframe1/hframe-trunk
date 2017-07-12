package com.hframe.tag;


import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.velocity.Template;

/**
 *
 * @author zqh
 *
 */
public class ButtonTag extends BodyTagSupport
{
	
	private String buttonId;
	private String buttonName;
	private String src;
	private String model;
	private String title;
	private String icon;
	private String param;
	private String targetObj;
	private String targetType;
	private String isAjax;
	private String srcObj;
	
	private String width;//tree的宽度
	private String height;//tree高度
		
	private String priv;
	
	private ButtonImpl buttonImpl;
	
	private static Template template;

	  public int doStartTag()
	    throws JspException
	  {
	   // JSHelper.processJS(this.pageContext, (HttpServletRequest)this.pageContext.getRequest(), "AIContractFrame_Tag_Js");
	    
		  
		  
		  Writer out=this.pageContext.getOut();
		  buttonImpl=new ButtonImpl(buttonId,buttonName,src,model,title,icon,param,srcObj,targetObj,targetType,isAjax,width,height,priv);
	    
	    buttonImpl.doStart(out);
	    
	    
	    return 1;
	  }

	 
	  public int doEndTag() throws JspException {
		  
//		  try {
//			this.pageContext.getOut().clear();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		    return 6;
		  }


	public String getButtonId() {
		return buttonId;
	}


	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}


	public String getButtonName() {
		return buttonName;
	}


	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}


	public String getSrc() {
		return src;
	}


	public void setSrc(String src) {
		this.src = src;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public String getParam() {
		return param;
	}


	public void setParam(String param) {
		this.param = param;
	}


	public String getTargetObj() {
		return targetObj;
	}


	public void setTargetObj(String targetObj) {
		this.targetObj = targetObj;
	}


	public String getTargetType() {
		return targetType;
	}


	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}


	public String getIsAjax() {
		return isAjax;
	}


	public void setIsAjax(String isAjax) {
		this.isAjax = isAjax;
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


	public String getPriv() {
		return priv;
	}


	public void setPriv(String priv) {
		this.priv = priv;
	}


	public static Template getTemplate() {
		return template;
	}


	public static void setTemplate(Template template) {
		ButtonTag.template = template;
	}


	public ButtonImpl getButtonImpl() {
		return buttonImpl;
	}


	public void setButtonImpl(ButtonImpl buttonImpl) {
		this.buttonImpl = buttonImpl;
	}


	public String getSrcObj() {
		return srcObj;
	}


	public void setSrcObj(String srcObj) {
		this.srcObj = srcObj;
	}


	

}