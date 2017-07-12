package com.hframe.client.baidumap;

import java.util.HashMap;

import com.hframework.common.util.message.JsonUtils;
import com.hframework.common.util.protocol.OldHttpClient;
import java.text.MessageFormat;

import com.hframe.client.baidumap.bean.*;
import com.hframework.common.util.FileUtils;


public class BaidumapClient   {


	public static ResponseData getAddress(String address, long mobile, RequestData requestData)  throws Exception{
			String url = MessageFormat.format(BaidumapConfig.getInstance().getGetAddress(),address,mobile,BaidumapHelper.md5(String.valueOf(address),String.valueOf(mobile)));
			String result;
			if("true".equals(BaidumapConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/baidumap/getAddress.response").getPath());
			}else {
			   result = OldHttpClient.doJsonPost(url, requestData);
			}
			ResponseData responseData = JsonUtils.readValue(result, ResponseData.class);
			return responseData;
	}

	public static String getAddress2()  throws Exception{
			String url = MessageFormat.format(BaidumapConfig.getInstance().getGetAddress2(),null);
			String result;
			if("true".equals(BaidumapConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/baidumap/getAddress2.response").getPath());
			}else {
			   result = OldHttpClient.doPost(url, new HashMap());
			}
			return result;
	}

  
 
}
