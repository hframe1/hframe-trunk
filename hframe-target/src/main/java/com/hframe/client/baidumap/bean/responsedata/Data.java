package com.hframe.client.baidumap.bean.responsedata;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class Data   {

	@JsonProperty("page")
	private Page page;
	@JsonProperty("total")
	private String total;
	@JsonProperty("data")
	private List<Data1> data1List;

    public Data() {
    	}
   
 
 	
	public Page getPage(){
		return page;
	}

	public void setPage(Page page){
    	this.page = page;
    }

 	
	public String getTotal(){
		return total;
	}

	public void setTotal(String total){
    	this.total = total;
    }

 	
	public List<Data1> getData1List(){
		return data1List;
	}

	public void setData1List(List<Data1> data1List){
    	this.data1List = data1List;
    }
}
