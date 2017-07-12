package com.hframe.tag;

import com.hframework.common.util.message.VelocityUtil;
import com.hframe.tag.util.TagUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


public class ModelImpl {

	private String id;//frame的id
	
	private String size;
	
	private String title; //tree是否带有标题

	private String defaultBtn;
	
	private String style;
	
	private String src;
	

	public ModelImpl(String id, String size, String title, String style,
			String src, String defaultBtn) {
		super();
		this.id = id;
		this.size = size;
		this.title = title;
		this.style = style;
		this.src = src;
		this.defaultBtn = defaultBtn;
	}



	public int doStart(Writer out){
		
		Map map=new HashMap();
		map.put("id", id);
		map.put("size", size==null?"modal-lg":"modal"+size);
		map.put("title", title);
		map.put("defaultBtn", defaultBtn);
		map.put("style", style);
		map.put("src", src);
		
		VelocityUtil.produceTemplateContent(
				"com/hframe/tag/vm/model_startpart.vm", map,
				out);
		
		return 1;
		
	}

	public int doEnd(Writer out) throws IOException {
		Map map = new HashMap();
		if (defaultBtn != null && !"".equals(defaultBtn)) {
			map.put("buttonInfo", 
					TagUtils.getDefaultButtonHtmlStr(defaultBtn));
		}
		
		VelocityUtil
				.produceTemplateContent(
						"com/hframe/tag/vm/model_endpart.vm",
						map, out);

		return 1;

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



	public String getDefaultBtn() {
		return defaultBtn;
	}



	public void setDefaultBtn(String defaultBtn) {
		this.defaultBtn = defaultBtn;
	}



	
	
	
	
}
