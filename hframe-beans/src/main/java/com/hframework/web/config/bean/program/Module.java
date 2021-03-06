package com.hframework.web.config.bean.program;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * generated by hframework on 2016.
 */@XStreamAlias("module")
public class Module   {

	@XStreamAsAttribute
    @XStreamAlias("code")
	private String code;
	@XStreamAsAttribute
    @XStreamAlias("name")
	private String name;

    public Module() {
    }
   
 	 	 
     public String getCode(){
     	return code;
     }

     public void setCode(String code){
     	this.code = code;
     }
	 	 	 
     public String getName(){
     	return name;
     }

     public void setName(String name){
     	this.name = name;
     }
	 
}
