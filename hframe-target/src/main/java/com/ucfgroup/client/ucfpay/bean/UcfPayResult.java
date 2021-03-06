package com.ucfgroup.client.ucfpay.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hframework.common.util.message.*;

/**
 * generated by hframework on 2016-04-27.
 */
public class UcfPayResult   {

	@JsonProperty("resCode")
	private String resCode;
	@JsonProperty("resMessage")
	private String resMessage;
	@JsonProperty("merchantId")
	private String merchantId;
	@JsonProperty("merchantNo")
	private String merchantNo;
	@JsonProperty("tradeNo")
	private String tradeNo;
	@JsonProperty("status")
	private String status;
	@JsonProperty("tradeTime")
	private String tradeTime;
	@JsonProperty("amount")
	private String amount;
	@JsonProperty("transCur")
	private String transCur;
	@JsonProperty("memo")
	private String memo;
	
	private boolean converted;

    public UcfPayResult() {
    }
 	
	public UcfPayResult convert()  throws Exception{
			return this;
	}

  
 	 	 
     public String getResCode(){
     	return resCode;
     }

     public void setResCode(String resCode){
     	this.resCode = resCode;
     }
	 	 	 
     public String getResMessage(){
     	return resMessage;
     }

     public void setResMessage(String resMessage){
     	this.resMessage = resMessage;
     }
	 	 	 
     public String getMerchantId(){
     	return merchantId;
     }

     public void setMerchantId(String merchantId){
     	this.merchantId = merchantId;
     }
	 	 	 
     public String getMerchantNo(){
     	return merchantNo;
     }

     public void setMerchantNo(String merchantNo){
     	this.merchantNo = merchantNo;
     }
	 	 	 
     public String getTradeNo(){
     	return tradeNo;
     }

     public void setTradeNo(String tradeNo){
     	this.tradeNo = tradeNo;
     }
	 	 	 
     public String getStatus(){
     	return status;
     }

     public void setStatus(String status){
     	this.status = status;
     }
	 	 	 
     public String getTradeTime(){
     	return tradeTime;
     }

     public void setTradeTime(String tradeTime){
     	this.tradeTime = tradeTime;
     }
	 	 	 
     public String getAmount(){
     	return amount;
     }

     public void setAmount(String amount){
     	this.amount = amount;
     }
	 	 	 
     public String getTransCur(){
     	return transCur;
     }

     public void setTransCur(String transCur){
     	this.transCur = transCur;
     }
	 	 	 
     public String getMemo(){
     	return memo;
     }

     public void setMemo(String memo){
     	this.memo = memo;
     }
	 	 
}
