package com.ucfgroup.client.beetle;

import java.util.*;

import com.hframework.common.util.message.JsonUtils;
import com.hframework.common.util.protocol.OldHttpClient;
import com.hframework.common.util.UrlHelper;

import com.ucfgroup.client.beetle.bean.*;
import com.hframework.common.util.FileUtils;

/**
 * generated by hframework on 2016-04-22.
 */
public class BeetleClient   {

	
	public static GetNodeInfoResponse getNodeInfo(String node)  throws Exception{
			Map<String, String> parameterMap = new LinkedHashMap();
			parameterMap.put("node" ,node);
			parameterMap.put("client" ,BeetleConfig.getInstance().getClient());
			parameterMap.put("sign_type" ,BeetleConfig.getInstance().getSignType());
			parameterMap.put("time" ,BeetleHelper.getCurTimestamp());
			parameterMap.put("sign" ,BeetleHelper.sign(parameterMap));
			String url = UrlHelper.getFinalUrl(BeetleConfig.getInstance().getGetNodeInfo(), parameterMap);
			String result;
			if("true".equals(BeetleConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/beetle/getNodeInfo.response").getPath());
			}else {
			   result = OldHttpClient.doGet(url, new HashMap());
			}
			GetNodeInfoResponse responseData = JsonUtils.readValue(result, GetNodeInfoResponse.class);
			return responseData.convert();
	}

	
	public static GetNodeDataResponse getNodeData(String node, String cycle, int isLeaf)  throws Exception{
			Map<String, String> parameterMap = new LinkedHashMap();
			parameterMap.put("node" ,node);
			parameterMap.put("cycle" ,cycle);
			parameterMap.put("isLeaf" ,String.valueOf(isLeaf));
			parameterMap.put("client" ,BeetleConfig.getInstance().getClient());
			parameterMap.put("sign_type" ,BeetleConfig.getInstance().getSignType());
			parameterMap.put("time" ,BeetleHelper.getCurTimestamp());
			parameterMap.put("sign" ,BeetleHelper.sign(parameterMap));
			String url = UrlHelper.getFinalUrl(BeetleConfig.getInstance().getGetNodeData(), parameterMap);
			String result;
			if("true".equals(BeetleConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/beetle/getNodeData.response").getPath());
			}else {
			   result = OldHttpClient.doGet(url, new HashMap());
			}
			GetNodeDataResponse responseData = JsonUtils.readValue(result,GetNodeDataResponse.class);
			return responseData.convert();
	}

	
	public static GetRegDetailResponse getRegDetail(String node, String cycle, int isleaf)  throws Exception{
			Map<String, String> parameterMap = new LinkedHashMap();
			parameterMap.put("node" ,node);
			parameterMap.put("cycle" ,cycle);
			parameterMap.put("isleaf" ,String.valueOf(isleaf));
			parameterMap.put("client" ,BeetleConfig.getInstance().getClient());
			parameterMap.put("sign_type" ,BeetleConfig.getInstance().getSignType());
			parameterMap.put("time" ,BeetleHelper.getCurTimestamp());
			parameterMap.put("sign" ,BeetleHelper.sign(parameterMap));
			String url = UrlHelper.getFinalUrl(BeetleConfig.getInstance().getGetRegDetail(), parameterMap);
			String result;
			if("true".equals(BeetleConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/beetle/getRegDetail.response").getPath());
			}else {
			   result = OldHttpClient.doGet(url, new HashMap());
			}
			GetRegDetailResponse responseData = JsonUtils.readValue(result,GetRegDetailResponse.class);
			return responseData.convert();
	}

	
	public static GetFirstinvestDetailResponse getFirstinvestDetail(String node, String cycle, int isleaf)  throws Exception{
			Map<String, String> parameterMap = new LinkedHashMap();
			parameterMap.put("node" ,node);
			parameterMap.put("cycle" ,cycle);
			parameterMap.put("isleaf" ,String.valueOf(isleaf));
			parameterMap.put("client" ,BeetleConfig.getInstance().getClient());
			parameterMap.put("sign_type" ,BeetleConfig.getInstance().getSignType());
			parameterMap.put("time" ,BeetleHelper.getCurTimestamp());
			parameterMap.put("sign" ,BeetleHelper.sign(parameterMap));
			String url = UrlHelper.getFinalUrl(BeetleConfig.getInstance().getGetFirstinvestDetail(), parameterMap);
			String result;
			if("true".equals(BeetleConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/beetle/getFirstinvestDetail.response").getPath());
			}else {
			   result = OldHttpClient.doGet(url, new HashMap());
			}
			GetFirstinvestDetailResponse responseData = JsonUtils.readValue(result,GetFirstinvestDetailResponse.class);
			return responseData.convert();
	}

	
	public static GetRepayPlanResponse getRepayPlan(int p2pId, int status, int page, int pageNum)  throws Exception{
			Map<String, String> parameterMap = new LinkedHashMap();
			parameterMap.put("p2p_id" ,String.valueOf(p2pId));
			parameterMap.put("status" ,String.valueOf(status));
			parameterMap.put("page" ,String.valueOf(page));
			parameterMap.put("page_num" ,String.valueOf(pageNum));
			parameterMap.put("client" ,BeetleConfig.getInstance().getClient());
			parameterMap.put("sign_type" ,BeetleConfig.getInstance().getSignType());
			parameterMap.put("time" ,BeetleHelper.getCurTimestamp());
			parameterMap.put("sign" ,BeetleHelper.sign(parameterMap));
			String url = UrlHelper.getFinalUrl(BeetleConfig.getInstance().getGetRepayPlan(), parameterMap);
			String result;
			if("true".equals(BeetleConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/beetle/getRepayPlan.response").getPath());
			}else {
			   result = OldHttpClient.doGet(url, new HashMap());
			}
			GetRepayPlanResponse responseData = JsonUtils.readValue(result,GetRepayPlanResponse.class);
			return responseData.convert();
	}

	
	public static GetAssetChangeResponse getAssetChange(int p2pId, String logInfo, int page, int pageNum)  throws Exception{
			Map<String, String> parameterMap = new LinkedHashMap();
			parameterMap.put("p2p_id" ,String.valueOf(p2pId));
			parameterMap.put("log_info" ,logInfo);
			parameterMap.put("page" ,String.valueOf(page));
			parameterMap.put("page_num" ,String.valueOf(pageNum));
			parameterMap.put("client" ,BeetleConfig.getInstance().getClient());
			parameterMap.put("sign_type" ,BeetleConfig.getInstance().getSignType());
			parameterMap.put("time" ,BeetleHelper.getCurTimestamp());
			parameterMap.put("sign" ,BeetleHelper.sign(parameterMap));
			String url = UrlHelper.getFinalUrl(BeetleConfig.getInstance().getGetAssetChange(), parameterMap);
			String result;
			if("true".equals(BeetleConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/beetle/getAssetChange.response").getPath());
			}else {
			   result = OldHttpClient.doGet(url, new HashMap());
			}
			GetAssetChangeResponse responseData = JsonUtils.readValue(result,GetAssetChangeResponse.class);
			return responseData.convert();
	}

	
	public static GetAdListResponse getAdList(String type, int limit)  throws Exception{
			Map<String, String> parameterMap = new LinkedHashMap();
			parameterMap.put("type" ,type);
			parameterMap.put("limit" ,String.valueOf(limit));
			parameterMap.put("client" ,BeetleConfig.getInstance().getClient());
			parameterMap.put("sign_type" ,BeetleConfig.getInstance().getSignType());
			parameterMap.put("time" ,BeetleHelper.getCurTimestamp());
			parameterMap.put("sign" ,BeetleHelper.sign(parameterMap));
			String url = UrlHelper.getFinalUrl(BeetleConfig.getInstance().getGetAdList(), parameterMap);
			String result;
			if("true".equals(BeetleConfig.getInstance().getTestModel())) {
			   result = FileUtils.readFile(Thread.currentThread().getContextClassLoader().getResource(
			          "third/beetle/getAdList.response").getPath());
			}else {
			   result = OldHttpClient.doGet(url, new HashMap());
			}
			GetAdListResponse responseData = JsonUtils.readValue(result,GetAdListResponse.class);
			return responseData.convert();
	}

  
 
}
