package com.hframe.client.baidumap.bean.responsedata;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Page   {

	@JsonProperty("pageNo")
	private String pageNo;
	@JsonProperty("pageSize")
	private String pageSize;

    public Page() {
    	}
   
 
 	
	public String getPageNo(){
		return pageNo;
	}

	public void setPageNo(String pageNo){
    	this.pageNo = pageNo;
    }

 	
	public String getPageSize(){
		return pageSize;
	}

	public void setPageSize(String pageSize){
    	this.pageSize = pageSize;
    }
}
