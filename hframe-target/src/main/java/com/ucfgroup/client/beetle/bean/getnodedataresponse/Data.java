package com.ucfgroup.client.beetle.bean.getnodedataresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hframework.common.util.message.XmlUtils;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * generated by hframework on 2016-04-22.
 */
public class Data   {

	@JsonProperty("info")
	private Info info;
	@JsonProperty("invest")
	private Invest invest;
	@JsonProperty("groups")
	private java.util.Map<String, GroupItem> groupitem;
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

  
 	 	 
     public Info getInfo(){
     	return info;
     }

     public void setInfo(Info info){
     	this.info = info;
     }
	 	 	 
     public Invest getInvest(){
     	return invest;
     }

     public void setInvest(Invest invest){
     	this.invest = invest;
     }
	 	 	 
     public java.util.Map<String, GroupItem> getGroups(){
     	return groupitem;
     }

     public void setGroups(java.util.Map<String, GroupItem> groupitem){
     	this.groupitem = groupitem;
     }
	 	 
}
