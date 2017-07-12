package com.ucfgroup.client.beetle.bean.getfirstinvestdetailresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hframework.common.util.message.XmlUtils;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * generated by hframework on 2016-04-22.
 */
public class Data   {

	@JsonProperty("consume_user_id")
	private String consumeUserId;
	@JsonProperty("consume_real_name")
	private String consumeRealName;
	@JsonProperty("money")
	private String money;
	@JsonProperty("mobile")
	private String mobile;
	@JsonProperty("create_time")
	private String createTime;
	@JsonProperty("refer_user_id")
	private String referUserId;
	@JsonProperty("refer_short_alias")
	private String referShortAlias;
	@JsonProperty("invest_money")
	private String investMoney;
	@JsonProperty("invest_year_money")
	private String investYearMoney;
	@JsonProperty("user_status")
	private String userStatus;
	@JsonProperty("refer_real_name")
	private String referRealName;
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

  
 	 	 
     public String getConsumeUserId(){
     	return consumeUserId;
     }

     public void setConsumeUserId(String consumeUserId){
     	this.consumeUserId = consumeUserId;
     }
	 	 	 
     public String getConsumeRealName(){
     	return consumeRealName;
     }

     public void setConsumeRealName(String consumeRealName){
     	this.consumeRealName = consumeRealName;
     }
	 	 	 
     public String getMoney(){
     	return money;
     }

     public void setMoney(String money){
     	this.money = money;
     }
	 	 	 
     public String getMobile(){
     	return mobile;
     }

     public void setMobile(String mobile){
     	this.mobile = mobile;
     }
	 	 	 
     public String getCreateTime(){
     	return createTime;
     }

     public void setCreateTime(String createTime){
     	this.createTime = createTime;
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
	 	 	 
     public String getInvestMoney(){
     	return investMoney;
     }

     public void setInvestMoney(String investMoney){
     	this.investMoney = investMoney;
     }
	 	 	 
     public String getInvestYearMoney(){
     	return investYearMoney;
     }

     public void setInvestYearMoney(String investYearMoney){
     	this.investYearMoney = investYearMoney;
     }
	 	 	 
     public String getUserStatus(){
     	return userStatus;
     }

     public void setUserStatus(String userStatus){
     	this.userStatus = userStatus;
     }
	 	 	 
     public String getReferRealName(){
     	return referRealName;
     }

     public void setReferRealName(String referRealName){
     	this.referRealName = referRealName;
     }
	 	 
}
