package com.hframe.tag;

import com.hframework.common.util.StringUtils;
import com.hframework.common.util.message.VelocityUtil;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


public class FrameImpl {

	private String frameId;//frame的id
	private String isframe="false";
		
	private String width;//tree的宽度
	private String height;//tree高度
	private String title; //tree是否带有标题

	private String src;
	private String style;
	private String isTab;
	
	
	public FrameImpl(String frameId, String isframe, String width,
			String height, String title, String src, String style,String isTab) {
		super();
		this.frameId = frameId;
		this.isframe = isframe;
		this.width = width;
		this.height = height;
		this.title = title;
		this.src = src;
		this.style = style;
		this.isTab=isTab;
	}



	public int doStart(Writer out){
		
		if(src!=null&&!"".equals(src)){
		    if(src.indexOf("?")==-1){
		    	src+="?_FRAMEID="+frameId;
		    }else{
		    	src+="&_FRAMEID="+frameId;
		    }
	    }
		
		
		if("true".equals(isTab)){
			Map map=new HashMap();
			map.put("id", frameId);
			map.put("width", width);
			map.put("height", height);
			map.put("style", style);
			map.put("title", "首页");
			if(StringUtils.isBlank(src)) {
				src ="welcome.jsp";
			}
			map.put("iframeHeight", Long.valueOf(height)-20);
			map.put("src", src);
			
			VelocityUtil.produceTemplateContent(
					"com/hframe/tag/vm/tab.vm", map,
					out);
			
			return 1;
		}
		
		
    	try { 
    		if("false".equals(isframe)){
    			out.write("  <iframe id=\""+frameId+"\"  src=\""+src+"\" frameborder=\"0\" style=\"height:"+height+"px;width:"+width+"px;"+style+"\"></iframe>");
    		}
    		
    		
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
//	    	try {
//				out.flush();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}

		
		
		return 1;
		
	}
	
	
	
	public String getFrameId() {
		return frameId;
	}
	public void setFrameId(String frameId) {
		this.frameId = frameId;
	}
	public String getIsframe() {
		return isframe;
	}
	public void setIsframe(String isframe) {
		this.isframe = isframe;
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
	
	
}
