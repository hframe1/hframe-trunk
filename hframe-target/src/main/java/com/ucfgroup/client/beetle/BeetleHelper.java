package com.ucfgroup.client.beetle;

import com.hframework.common.helper.Rules;

/**
 * generated by hframework on 2016-04-22.
 */
public class BeetleHelper   {

	
	public static String getCurTimestamp()  throws Exception{
			return Rules.getTimeStamp();
	}

	
	public static String sign(Object object)  throws Exception{
			return Rules.signAllParams(object,BeetleConfig.getInstance().getKey());
	}

  
 
}
