package com.hframework.web.config.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.List;
import com.hframework.web.config.bean.datasetruler.*;

/**
 * generated by hframework on 2016.
 */@XStreamAlias("ruler")
public class DataSetRuler   {

	@XStreamAlias("module")
	private String module;
	@XStreamAlias("entity")
	private String entity;
	@XStreamImplicit
    @XStreamAlias("rule")
	private List<Rule> ruleList;

    public DataSetRuler() {
    }
   
 	 	 
     public String getModule(){
     	return module;
     }

     public void setModule(String module){
     	this.module = module;
     }
	 	 	 
     public String getEntity(){
     	return entity;
     }

     public void setEntity(String entity){
     	this.entity = entity;
     }
	 	 	 
     public List<Rule> getRuleList(){
     	return ruleList;
     }

     public void setRuleList(List<Rule> ruleList){
     	this.ruleList = ruleList;
     }
	 
}
