package com.ucfgroup.client.beetle;

import com.hframework.common.annotation.Key;
import com.hframework.common.annotation.Source;
import com.hframework.common.util.ResourceWrapper;
import java.lang.reflect.InvocationTargetException;

@Source("third/beetle.properties")
public class BeetleConfig   {

	@Key( "third.beetle.test_model")
	private String testModel;
	@Key( "third.beetle.client")
	private String client;
	@Key( "third.beetle.sign_type")
	private String signType;
	@Key( "third.beetle.key")
	private String key;
	@Key( "third.beetle.interface.getNodeInfo")
	private String getNodeInfo;
	@Key( "third.beetle.interface.getNodeData")
	private String getNodeData;
	@Key( "third.beetle.interface.getRegDetail")
	private String getRegDetail;
	@Key( "third.beetle.interface.getFirstinvestDetail")
	private String getFirstinvestDetail;
	@Key( "third.beetle.interface.getRepayPlan")
	private String getRepayPlan;
	@Key( "third.beetle.interface.getAssetChange")
	private String getAssetChange;
	@Key( "third.beetle.interface.getAdList")
	private String getAdList;
  
 
 	
	public String getTestModel(){
		return testModel;
	}

	public void setTestModel(String testModel){
    	this.testModel = testModel;
    }

 	
	public String getClient(){
		return client;
	}

	public void setClient(String client){
    	this.client = client;
    }

 	
	public String getSignType(){
		return signType;
	}

	public void setSignType(String signType){
    	this.signType = signType;
    }

 	
	public String getKey(){
		return key;
	}

	public void setKey(String key){
    	this.key = key;
    }

 	
	public String getGetNodeInfo(){
		return getNodeInfo;
	}

	public void setGetNodeInfo(String getNodeInfo){
    	this.getNodeInfo = getNodeInfo;
    }

 	
	public String getGetNodeData(){
		return getNodeData;
	}

	public void setGetNodeData(String getNodeData){
    	this.getNodeData = getNodeData;
    }

 	
	public String getGetRegDetail(){
		return getRegDetail;
	}

	public void setGetRegDetail(String getRegDetail){
    	this.getRegDetail = getRegDetail;
    }

 	
	public String getGetFirstinvestDetail(){
		return getFirstinvestDetail;
	}

	public void setGetFirstinvestDetail(String getFirstinvestDetail){
    	this.getFirstinvestDetail = getFirstinvestDetail;
    }

 	
	public String getGetRepayPlan(){
		return getRepayPlan;
	}

	public void setGetRepayPlan(String getRepayPlan){
    	this.getRepayPlan = getRepayPlan;
    }

 	
	public String getGetAssetChange(){
		return getAssetChange;
	}

	public void setGetAssetChange(String getAssetChange){
    	this.getAssetChange = getAssetChange;
    }

 	
	public String getGetAdList(){
		return getAdList;
	}

	public void setGetAdList(String getAdList){
    	this.getAdList = getAdList;
    }

	private static BeetleConfig instance;

	private BeetleConfig() {
		super();
	}

	public  static BeetleConfig getInstance(){
		if(instance == null) {
			synchronized (BeetleConfig.class) {
				if(instance == null) {
					try {
						return instance = ResourceWrapper.getResourceBean(BeetleConfig.class);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
					return instance = new BeetleConfig();
				}
			}
		}
		return instance;
	}

}
