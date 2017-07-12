package com.ucfgroup.client.weixinpay;

import com.hframework.common.annotation.*;
import com.hframework.common.util.ResourceWrapper;
import java.lang.reflect.InvocationTargetException;

@Source("third/weixinpay.properties")
public class WeixinpayConfig   {

	@Key( "third.weixinpay.test_model")
	private String testModel;
	@Key( "third.weixinpay.domain")
	private String domain;
	@Key( "third.weixinpay.key")
	private String key;
	@Key( "third.weixinpay.appid")
	private String appid;
	@Key( "third.weixinpay.mch_id")
	private String mchId;
	@Key( "third.weixinpay.device_info")
	private String deviceInfo;
	@Key( "third.weixinpay.fee_type")
	private String feeType;
	@Key( "third.weixinpay.goods_tag")
	private String goodsTag;
	@Key( "third.weixinpay.trade_type")
	private String tradeType;
	@Key( "third.weixinpay.limit_pay")
	private String limitPay;
	@Key( "third.weixinpay.refund_fee_type")
	private String refundFeeType;
	@Key( "third.weixinpay.unifiedorder_callback")
	private String unifiedorderCallback;
	@Key( "third.weixinpay.interface.unifiedorder")
	private String unifiedorder;
	@Key( "third.weixinpay.interface.orderquery")
	private String orderquery;
	@Key( "third.weixinpay.interface.closeorder")
	private String closeorder;
	@Key( "third.weixinpay.interface.downloadbill")
	private String downloadbill;
	@Key( "third.weixinpay.interface.refund")
	private String refund;
	@Key( "third.weixinpay.interface.refundquery")
	private String refundquery;
	@Key( "third.weixinpay.interface.notice")
	private String notice;
  
 
 	
	public String getTestModel(){
		return testModel;
	}

	public void setTestModel(String testModel){
    	this.testModel = testModel;
    }

 	
	public String getDomain(){
		return domain;
	}

	public void setDomain(String domain){
    	this.domain = domain;
    }

 	
	public String getKey(){
		return key;
	}

	public void setKey(String key){
    	this.key = key;
    }

 	
	public String getAppid(){
		return appid;
	}

	public void setAppid(String appid){
    	this.appid = appid;
    }

 	
	public String getMchId(){
		return mchId;
	}

	public void setMchId(String mchId){
    	this.mchId = mchId;
    }

 	
	public String getDeviceInfo(){
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo){
    	this.deviceInfo = deviceInfo;
    }

 	
	public String getFeeType(){
		return feeType;
	}

	public void setFeeType(String feeType){
    	this.feeType = feeType;
    }

 	
	public String getGoodsTag(){
		return goodsTag;
	}

	public void setGoodsTag(String goodsTag){
    	this.goodsTag = goodsTag;
    }

 	
	public String getTradeType(){
		return tradeType;
	}

	public void setTradeType(String tradeType){
    	this.tradeType = tradeType;
    }

 	
	public String getLimitPay(){
		return limitPay;
	}

	public void setLimitPay(String limitPay){
    	this.limitPay = limitPay;
    }

 	
	public String getRefundFeeType(){
		return refundFeeType;
	}

	public void setRefundFeeType(String refundFeeType){
    	this.refundFeeType = refundFeeType;
    }

 	
	public String getUnifiedorderCallback(){
		return unifiedorderCallback;
	}

	public void setUnifiedorderCallback(String unifiedorderCallback){
    	this.unifiedorderCallback = unifiedorderCallback;
    }

 	
	public String getUnifiedorder(){
		return unifiedorder;
	}

	public void setUnifiedorder(String unifiedorder){
    	this.unifiedorder = unifiedorder;
    }

 	
	public String getOrderquery(){
		return orderquery;
	}

	public void setOrderquery(String orderquery){
    	this.orderquery = orderquery;
    }

 	
	public String getCloseorder(){
		return closeorder;
	}

	public void setCloseorder(String closeorder){
    	this.closeorder = closeorder;
    }

 	
	public String getDownloadbill(){
		return downloadbill;
	}

	public void setDownloadbill(String downloadbill){
    	this.downloadbill = downloadbill;
    }

 	
	public String getRefund(){
		return refund;
	}

	public void setRefund(String refund){
    	this.refund = refund;
    }

 	
	public String getRefundquery(){
		return refundquery;
	}

	public void setRefundquery(String refundquery){
    	this.refundquery = refundquery;
    }

 	
	public String getNotice(){
		return notice;
	}

	public void setNotice(String notice){
    	this.notice = notice;
    }

	private static WeixinpayConfig instance;

	private WeixinpayConfig() {
		super();
	}

	public  static WeixinpayConfig getInstance(){
		if(instance == null) {
			synchronized (WeixinpayConfig.class) {
				if(instance == null) {
					try {
						return instance = ResourceWrapper.getResourceBean(WeixinpayConfig.class);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					return instance = new WeixinpayConfig();
				}
			}
		}
		return instance;
	}

}
