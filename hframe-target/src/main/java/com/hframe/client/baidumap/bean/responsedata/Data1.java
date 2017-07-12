package com.hframe.client.baidumap.bean.responsedata;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Data1   {

	@JsonProperty("createTime")
	private String createTime;
	@JsonProperty("realName")
	private String realName;
	@JsonProperty("userName")
	private String userName;
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("referRealName")
	private String referRealName;
	@JsonProperty("referUserName")
	private String referUserName;
	@JsonProperty("referUserId")
	private String referUserId;
	@JsonProperty("referShortAlias")
	private String referShortAlias;

    public Data1() {
    	}
   
 
 	
	public String getCreateTime(){
		return createTime;
	}

	public void setCreateTime(String createTime){
    	this.createTime = createTime;
    }

 	
	public String getRealName(){
		return realName;
	}

	public void setRealName(String realName){
    	this.realName = realName;
    }

 	
	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName){
    	this.userName = userName;
    }

 	
	public String getUserId(){
		return userId;
	}

	public void setUserId(String userId){
    	this.userId = userId;
    }

 	
	public String getReferRealName(){
		return referRealName;
	}

	public void setReferRealName(String referRealName){
    	this.referRealName = referRealName;
    }

 	
	public String getReferUserName(){
		return referUserName;
	}

	public void setReferUserName(String referUserName){
    	this.referUserName = referUserName;
    }

 	
	public String getReferUserId(){
		return referUserId;
	}

	public void setReferUserId(String referUserId){
    	this.referUserId = referUserId;
    }

 	
	public String getReferShortAlias(){
		return referShortAlias;
	}

	public void setReferShortAlias(String referShortAlias){
    	this.referShortAlias = referShortAlias;
    }
}
