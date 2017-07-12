package com.hframe.client.baidumap.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hframe.client.baidumap.bean.requestdata.*;


public class RequestData   {

	@JsonProperty("data")
	private Data data;
	@JsonProperty("errorCode")
	private String errorCode;
	@JsonProperty("errorMsg")
	private String errorMsg;

    public RequestData() {
    	}
   
 
 	
	public Data getData(){
		return data;
	}

	public void setData(Data data){
    	this.data = data;
    }

 	
	public String getErrorCode(){
		return errorCode;
	}

	public void setErrorCode(String errorCode){
    	this.errorCode = errorCode;
    }

 	
	public String getErrorMsg(){
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg){
    	this.errorMsg = errorMsg;
    }
}
