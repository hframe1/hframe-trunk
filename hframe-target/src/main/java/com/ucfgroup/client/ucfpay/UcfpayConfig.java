package com.ucfgroup.client.ucfpay;

import com.hframework.common.annotation.*;
import com.hframework.common.util.ResourceWrapper;
import java.lang.reflect.InvocationTargetException;

@Source("third/ucfpay.properties")
public class UcfpayConfig   {

	@Key( "third.ucfpay.test_model")
	private String testModel;
	@Key( "third.ucfpay.domain")
	private String domain;
	@Key( "third.ucfpay.secKey")
	private String secKey;
	@Key( "third.ucfpay.orderExpiredTime")
	private String orderExpiredTime;
	@Key( "third.ucfpay.secId")
	private String secId;
	@Key( "third.ucfpay.version")
	private String version;
	@Key( "third.ucfpay.merchantId")
	private String merchantId;
	@Key( "third.ucfpay.transCur")
	private String transCur;
	@Key( "third.ucfpay.certificateType")
	private String certificateType;
	@Key( "third.ucfpay.orderNoticeUrl")
	private String orderNoticeUrl;
	@Key( "third.ucfpay.authPay_merchantId")
	private String authPayMerchantId;
	@Key( "third.ucfpay.authPay_secKey")
	private String authPaySecKey;
	@Key( "third.ucfpay.interface.createOrder")
	private String createOrder;
	@Key( "third.ucfpay.interface.confirmOrder")
	private String confirmOrder;
	@Key( "third.ucfpay.interface.resendSms")
	private String resendSms;
	@Key( "third.ucfpay.interface.queryCardBin")
	private String queryCardBin;
	@Key( "third.ucfpay.interface.queryOrder")
	private String queryOrder;
  
 
 	
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

 	
	public String getSecKey(){
		return secKey;
	}

	public void setSecKey(String secKey){
    	this.secKey = secKey;
    }

 	
	public String getOrderExpiredTime(){
		return orderExpiredTime;
	}

	public void setOrderExpiredTime(String orderExpiredTime){
    	this.orderExpiredTime = orderExpiredTime;
    }

 	
	public String getSecId(){
		return secId;
	}

	public void setSecId(String secId){
    	this.secId = secId;
    }

 	
	public String getVersion(){
		return version;
	}

	public void setVersion(String version){
    	this.version = version;
    }

 	
	public String getMerchantId(){
		return merchantId;
	}

	public void setMerchantId(String merchantId){
    	this.merchantId = merchantId;
    }

 	
	public String getTransCur(){
		return transCur;
	}

	public void setTransCur(String transCur){
    	this.transCur = transCur;
    }

 	
	public String getCertificateType(){
		return certificateType;
	}

	public void setCertificateType(String certificateType){
    	this.certificateType = certificateType;
    }

 	
	public String getOrderNoticeUrl(){
		return orderNoticeUrl;
	}

	public void setOrderNoticeUrl(String orderNoticeUrl){
    	this.orderNoticeUrl = orderNoticeUrl;
    }

 	
	public String getAuthPayMerchantId(){
		return authPayMerchantId;
	}

	public void setAuthPayMerchantId(String authPayMerchantId){
    	this.authPayMerchantId = authPayMerchantId;
    }

 	
	public String getAuthPaySecKey(){
		return authPaySecKey;
	}

	public void setAuthPaySecKey(String authPaySecKey){
    	this.authPaySecKey = authPaySecKey;
    }

 	
	public String getCreateOrder(){
		return createOrder;
	}

	public void setCreateOrder(String createOrder){
    	this.createOrder = createOrder;
    }

 	
	public String getConfirmOrder(){
		return confirmOrder;
	}

	public void setConfirmOrder(String confirmOrder){
    	this.confirmOrder = confirmOrder;
    }

 	
	public String getResendSms(){
		return resendSms;
	}

	public void setResendSms(String resendSms){
    	this.resendSms = resendSms;
    }

 	
	public String getQueryCardBin(){
		return queryCardBin;
	}

	public void setQueryCardBin(String queryCardBin){
    	this.queryCardBin = queryCardBin;
    }

 	
	public String getQueryOrder(){
		return queryOrder;
	}

	public void setQueryOrder(String queryOrder){
    	this.queryOrder = queryOrder;
    }

	private static UcfpayConfig instance;

	private UcfpayConfig() {
		super();
	}

	public  static UcfpayConfig getInstance(){
		if(instance == null) {
			synchronized (UcfpayConfig.class) {
				if(instance == null) {
					try {
						return instance = ResourceWrapper.getResourceBean(UcfpayConfig.class);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					return instance = new UcfpayConfig();
				}
			}
		}
		return instance;
	}

}
