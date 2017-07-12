package com.ucfgroup.client.beetle.bean.getnodeinforesponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hframework.common.util.message.XmlUtils;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * generated by hframework on 2016-04-22.
 */
public class Secondids   {

	@JsonProperty("secondlevel")
	private String secondlevel;
	@JsonProperty("secondname")
	private String secondname;
	@JsonProperty("secondid")
	private String secondid;
	@XStreamOmitField
	private boolean converted;

    public Secondids() {
    }
 	
	public Secondids convert()  throws Exception{
			if(!converted) {
			   String beforeInfo = XmlUtils.writeValueAsString(this);
			   System.out.println(beforeInfo);
			   converted = true;
			   String afterInfo = XmlUtils.writeValueAsString(this);
			   System.out.println(afterInfo);
			}
			return this;
	}

  
 	 	 
     public String getSecondlevel(){
     	return secondlevel;
     }

     public void setSecondlevel(String secondlevel){
     	this.secondlevel = secondlevel;
     }
	 	 	 
     public String getSecondname(){
     	return secondname;
     }

     public void setSecondname(String secondname){
     	this.secondname = secondname;
     }
	 	 	 
     public String getSecondid(){
     	return secondid;
     }

     public void setSecondid(String secondid){
     	this.secondid = secondid;
     }
	 	 
}