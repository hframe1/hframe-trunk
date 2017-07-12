package com.ucfgroup.client.beetle.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import com.hframework.common.util.message.XmlUtils;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.ucfgroup.client.beetle.bean.responsedata.*;


public class ResponseData   {

	@JsonProperty("error_code")
	private String errorCode;
	@JsonProperty("error_msg")
	private String errorMsg;
	@JsonProperty("data")
	private List<Data> dataList;
	@XStreamOmitField
	private boolean converted;

    public ResponseData() {
    }
 
	public ResponseData convert()  throws Exception{
			if(!converted) {
			   String beforeInfo = XmlUtils.writeValueAsString(this);
			   System.out.println(beforeInfo);
			   converted = true;
			   String afterInfo = XmlUtils.writeValueAsString(this);
			   System.out.println(afterInfo);
			}
			return this;
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
	 	 	 
     public List<Data> getDataList(){
     	return dataList;
     }

     public void setDataList(List<Data> dataList){
     	this.dataList = dataList;
     }
	 	 
}
