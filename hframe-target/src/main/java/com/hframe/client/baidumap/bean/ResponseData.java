package com.hframe.client.baidumap.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hframe.client.baidumap.bean.responsedata.*;


public class ResponseData   {

	@JsonProperty("err_code")
	private String errCode;
	@JsonProperty("err_msg")
	private String errMsg;
	@JsonProperty("data")
	private Data data;

    public ResponseData() {
    	}
   
 
 	
	public String getErrCode(){
		return errCode;
	}

	public void setErrCode(String errCode){
    	this.errCode = errCode;
    }

 	
	public String getErrMsg(){
		return errMsg;
	}

	public void setErrMsg(String errMsg){
    	this.errMsg = errMsg;
    }

 	
	public Data getData(){
		return data;
	}

	public void setData(Data data){
    	this.data = data;
    }
}
