package com.hframe.client.baidumap;

import com.hframe.client.baidumap.mapping.*;
import com.hframework.common.util.security.AESUtil;
import com.hframework.common.util.security.MD5Util;


public class BaidumapHelper   {


	public static String encryptAES(String encryptString)  throws Exception{
			return MD5Util.encrypt(AESUtil.encrypt(encryptString, BaidumapConfig.getInstance().getZk()));
	}

	public static String decryptAES(String encryptString)  throws Exception{
			return AESUtil.decrypt(encryptString, BaidumapConfig.getInstance().getZk());
	}

	public static String md5(String address, String mobile)  throws Exception{
			return MD5Util.encrypt(address + mobile + BaidumapConfig.getInstance().getZk());
	}

	public static String mobileMapping(String sourceValue)  throws Exception{
			return new MobileValueMapper().mapping(sourceValue);
	}

	public static String userIdMapping(String sourceValue)  throws Exception{
			return new UserIdValueMapper().mapping(sourceValue);
	}

	public static String cityCodeMapping(String sourceValue)  throws Exception{
			return new CityCodeValueMapper().mapping(sourceValue);
	}

	public static String productIdMapping(String sourceValue)  throws Exception{
			return new ProductIdValueMapper().mapping(sourceValue);
	}

	public static String serviceInstanceIdMapping(String sourceValue)  throws Exception{
			return new ServiceInstanceIdValueMapper().mapping(sourceValue);
	}

  
 
}
