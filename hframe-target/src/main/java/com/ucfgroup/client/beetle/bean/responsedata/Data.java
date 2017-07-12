package com.ucfgroup.client.beetle.bean.responsedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hframework.common.util.message.XmlUtils;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


public class Data   {

	@JsonProperty("id")
	private String id;
	@JsonProperty("res_name")
	private String resName;
	@JsonProperty("title")
	private String title;
	@JsonProperty("subtitle")
	private String subtitle;
	@JsonProperty("url")
	private String url;
	@JsonProperty("content")
	private String content;
	@JsonProperty("allow")
	private String allow;
	@JsonProperty("create_time")
	private String createTime;
	@JsonProperty("pic")
	private String pic;
	@XStreamOmitField
	private boolean converted;

    public Data() {
    }
 
	public Data convert()  throws Exception{
			if(!converted) {
			   String beforeInfo = XmlUtils.writeValueAsString(this);
			   System.out.println(beforeInfo);
			   converted = true;
			   String afterInfo = XmlUtils.writeValueAsString(this);
			   System.out.println(afterInfo);
			}
			return this;
	}

  
 	 	 
     public String getId(){
     	return id;
     }

     public void setId(String id){
     	this.id = id;
     }
	 	 	 
     public String getResName(){
     	return resName;
     }

     public void setResName(String resName){
     	this.resName = resName;
     }
	 	 	 
     public String getTitle(){
     	return title;
     }

     public void setTitle(String title){
     	this.title = title;
     }
	 	 	 
     public String getSubtitle(){
     	return subtitle;
     }

     public void setSubtitle(String subtitle){
     	this.subtitle = subtitle;
     }
	 	 	 
     public String getUrl(){
     	return url;
     }

     public void setUrl(String url){
     	this.url = url;
     }
	 	 	 
     public String getContent(){
     	return content;
     }

     public void setContent(String content){
     	this.content = content;
     }
	 	 	 
     public String getAllow(){
     	return allow;
     }

     public void setAllow(String allow){
     	this.allow = allow;
     }
	 	 	 
     public String getCreateTime(){
     	return createTime;
     }

     public void setCreateTime(String createTime){
     	this.createTime = createTime;
     }
	 	 	 
     public String getPic(){
     	return pic;
     }

     public void setPic(String pic){
     	this.pic = pic;
     }
	 	 
}
