package com.hframework.web.config.bean.component;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * generated by hframework on 2016.
 */@XStreamAlias("attach")
public class Attach   {

	@XStreamImplicit
    @XStreamAlias("appendElement")
	private List<AppendElement> appendElementList;
	@XStreamAsAttribute
    @XStreamAlias("anchor")
	private String anchor;

    public Attach() {
    }
   
 	 	 
     public List<AppendElement> getAppendElementList(){
     	return appendElementList;
     }

     public void setAppendElementList(List<AppendElement> appendElementList){
     	this.appendElementList = appendElementList;
     }
	 	 	 
     public String getAnchor(){
     	return anchor;
     }

     public void setAnchor(String anchor){
     	this.anchor = anchor;
     }
	 
}
