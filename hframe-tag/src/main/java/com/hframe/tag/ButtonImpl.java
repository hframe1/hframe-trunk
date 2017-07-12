package com.hframe.tag;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.hframework.common.util.message.VelocityUtil;
import org.apache.commons.lang.StringUtils;


public class ButtonImpl {
	
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
	
	//按钮的大小1:btn-lg; 2:默认; 3:btn-sm; 4:btn-xs;
	private String size;
	//按钮的背景颜色 "default","primary","success","info","warning","danger"
	private String color;

	private String width;//tree的宽度
	private String height;//tree高度
		
	private String priv;
	
	
	public ButtonImpl() {
		super();
	}
	public ButtonImpl(String buttonId, String buttonName, String src,
			String model, String title, String icon, String param,
			String targetObj, String targetType, String isAjax) {
		super();
		this.buttonId = buttonId;
		this.buttonName = buttonName;
		this.src = src;
		this.model = model;
		this.title = title;
		this.icon = icon;
		this.param = param;
		this.targetObj = targetObj;
		this.targetType = targetType;
		this.isAjax = isAjax;
	}
	
	public ButtonImpl(String buttonId, String buttonName, String src,
			String model, String title, String icon, String param,
			String targetObj, String targetType, String isAjax, String width,
			String height, String priv) {
		super();
		this.buttonId = buttonId;
		this.buttonName = buttonName;
		this.src = src;
		this.model = model;
		this.title = title;
		this.icon = icon;
		this.param = param;
		this.targetObj = targetObj;
		this.targetType = targetType;
		this.isAjax = isAjax;
		this.width = width;
		this.height = height;
		this.priv = priv;
	}
	

	
	public ButtonImpl(String buttonId, String buttonName, String src,
			String model, String title, String icon, String param,String srcObj, 
			String targetObj, String targetType, String isAjax, String width,
			String height, String priv) {
		super();
		this.buttonId = buttonId;
		this.buttonName = buttonName;
		this.src = src;
		this.model = model;
		this.title = title;
		this.icon = icon;
		this.param = param;
		this.srcObj = srcObj;
		this.targetObj = targetObj;
		this.targetType = targetType;
		this.isAjax = isAjax;
		this.width = width;
		this.height = height;
		this.priv = priv;
	}
	

	public void doStart(Writer out) {
		
		String content = getContent();
		
		try {
			out.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getContent() {
		if(src==null||"".equals(src)){
			src="test/createDialog.jsp";
		}
		this.targetObj=(this.targetObj==null?"":this.targetObj);
//		try {
		if(StringUtils.isBlank(color)){
			color = "default";
		}
			Map map = new HashMap();
			map.put("Id", buttonId);
			map.put("Class", "btn btn-" + color 
					+ " " + (StringUtils.isBlank(size)?"":("btn-"+size)));
			map.put("Name", title);
			map.put("LoadingFlag", 0);
			map.put("CollapseId", "");
			map.put("OnClick", "btnClk_Sys(this,'"+this.model+"','"+this.src+"',0,'{param="+this.param+"&isAjax="+this.isAjax+"&priv="+this.priv+"&srcObj="+this.srcObj+"&targetObj="+this.targetObj+"&targetType="+this.targetType+"}')");
			
			
		    String contentString = VelocityUtil.produceTemplateContent("com/hframe/tag/vm/button.vm", map);
			
//			out.write(" <button  id='"+this.buttonId+"'  onclick=\"btnClk_Sys(this,'"+this.model+"','"+this.src+"',0,'{param="+this.param+"&isAjax="+this.isAjax+"&priv="+this.priv+"&targetObj="+this.targetObj+"&targetType="+this.targetType+"}')\"                "
//					+"  class=\"btn1_mouseout\" onmouseover=\"this.className='btn1_mouseover'\" onmouseout=\"this.className='btn1_mouseout'\" style='width:"+this.width+";height:"+this.height+"' title='"+this.title+"' >"+this.title+"</button>");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return contentString;
	}
	private String getSizeDisplay() {
		if(StringUtils.isBlank(size)) {
			return "";
		}
		
		return "btn-"+size;
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
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSrcObj() {
		return srcObj;
	}
	public void setSrcObj(String srcObj) {
		this.srcObj = srcObj;
	}
	
	
}
