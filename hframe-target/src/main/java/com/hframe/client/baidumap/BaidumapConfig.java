package com.hframe.client.baidumap;

import com.hframework.common.annotation.Key;
import com.hframework.common.annotation.Source;
import com.hframework.common.util.ResourceWrapper;
import java.lang.reflect.InvocationTargetException;

@Source("third/baidumap.properties")
public class BaidumapConfig   {

	@Key( "third.baidumap.test_model")
	private String testModel;
	@Key( "third.baidumap.zk")
	private String zk;
	@Key( "third.baidumap.domain")
	private String domain;
	@Key( "third.baidumap.interface.getAddress")
	private String getAddress;
	@Key( "third.baidumap.interface.getAddress2")
	private String getAddress2;
  
 
 	
	public String getTestModel(){
		return testModel;
	}

	public void setTestModel(String testModel){
    	this.testModel = testModel;
    }

 	
	public String getZk(){
		return zk;
	}

	public void setZk(String zk){
    	this.zk = zk;
    }

 	
	public String getDomain(){
		return domain;
	}

	public void setDomain(String domain){
    	this.domain = domain;
    }

 	
	public String getGetAddress(){
		return getAddress;
	}

	public void setGetAddress(String getAddress){
    	this.getAddress = getAddress;
    }

 	
	public String getGetAddress2(){
		return getAddress2;
	}

	public void setGetAddress2(String getAddress2){
    	this.getAddress2 = getAddress2;
    }

	private static BaidumapConfig instance;

	private BaidumapConfig() {
		super();
	}

	public  static BaidumapConfig getInstance(){
		if(instance == null) {
			synchronized (BaidumapConfig.class) {
				if(instance == null) {
					try {
						return instance = ResourceWrapper.getResourceBean(BaidumapConfig.class);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					return instance = new BaidumapConfig();
				}
			}
		}
		return instance;
	}

}
