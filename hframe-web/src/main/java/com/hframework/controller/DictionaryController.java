package com.hframework.controller;

import com.alibaba.fastjson.JSONObject;
import com.hframework.base.bean.AuthContext;
import com.hframework.base.bean.KVBean;
import com.hframework.beans.controller.ResultData;
import com.hframework.common.ext.CollectionUtils;
import com.hframework.common.ext.Mapping;
import com.hframework.common.util.ResourceWrapper;
import com.hframework.web.bean.ComponentDescriptor;
import com.hframework.web.bean.DataSetDescriptor;
import com.hframework.web.bean.PageDescriptor;
import com.hframework.web.bean.WebContext;
import com.hframework.web.config.bean.component.AppendElement;
import com.hframework.web.config.bean.component.Effect;
import com.hframework.web.config.bean.component.Event;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by zhangquanhong on 2017/2/10.
 */
@Controller
@RequestMapping(value = "/frame")
public class DictionaryController extends AbstractController{

    @Resource
    private AuthServiceProxy authServiceProxy;

    /**
     * 通过功能id获取所有的事件信息
      * @param request
     * @return
     */
    @RequestMapping(value = "/getEventsByFuncId.json")
    @ResponseBody
    public ResultData getEventsByFuncId(HttpServletRequest request) throws Exception {

        List<Event> allEventList = new ArrayList<Event>();

        Map<String, String> parameters = parseParameterForDataCondition(request);
        WebContext webContext = WebContext.get();
        AuthContext authContext = authServiceProxy.getAuthContext(request);
        List<Class> authFunctionClass = authContext.getAuthManager().getAuthFunctionClass();

        String functionId = null;
        if(authFunctionClass != null) {
            for (Class authFunctionClass1 : authFunctionClass) {
                DataSetDescriptor dataSet = webContext.getDataSet(authFunctionClass1);
                String keyPropertyName = dataSet.getKeyField().getCode();
                if(parameters.containsKey(keyPropertyName)) {
                    functionId = parameters.get(keyPropertyName);
                    break;
                }else if(parameters.containsKey(ResourceWrapper.JavaUtil.getJavaVarName(keyPropertyName))){
                    functionId = parameters.get(ResourceWrapper.JavaUtil.getJavaVarName(keyPropertyName));
                    break;
                }
            }
        }

        if(StringUtils.isNoneBlank(functionId)) {
            for (Map.Entry<String, Long> funcIdAndUrl : authContext.getAuthFunctionManager().entrySet()) {
                if(funcIdAndUrl.getValue().equals(Long.valueOf(functionId))) {
                    String url = funcIdAndUrl.getKey();
                    if(url.matches("/[a-zA-Z1-9_]+/[a-zA-Z1-9_]+.html")) {
                        String module = url.substring(url.indexOf("/") + 1, url.lastIndexOf("/"));
                        String page = url.substring(url.lastIndexOf("/") + 1, url.indexOf(".html"));
                        PageDescriptor pageInfo = webContext.getPageInfo(module, page);
                        Collection<ComponentDescriptor> components = pageInfo.getComponents().values();

                        for (ComponentDescriptor component : components) {
                            if(component.getDataContainer() != null) {
                                allEventList.addAll(component.getDataContainer().getAllEvent());
                            }
                        }
                    }
                }
            }
        }

        final Set<String> keySet = new HashSet<String>();
        List<KVBean> kvBeans = CollectionUtils.from(allEventList, new Mapping<Event, KVBean>() {
            public KVBean from(Event event) {
                boolean isMod = false;
                if (event.getEffectList() != null) {
                    for (Effect effect : event.getEffectList()) {
                        if (!"component.reload".equals(effect.getType()) && !"page.reload".equals(effect.getType())) {
                            isMod = true;
                        }
                    }
                }

                if (!isMod) return null;
//                if(keySet.contains(event.getName())) {
//                    return null;
//                }
                keySet.add(event.getName());

                String text = event.getDescription();
                if(StringUtils.isBlank(text) && event.getAttach() != null && event.getAttach().getAppendElementList() != null) {
                    for (AppendElement appendElement : event.getAttach().getAppendElementList()) {
                        String param = appendElement.getParam();
                        if(StringUtils.isNoneBlank(param)) {
                            JSONObject jsonObject = JSONObject.parseObject(param);
                            if(jsonObject.containsKey("btnText")) {
                                text = jsonObject.getString("btnText");
                            }
                        }

                    }
                }



                KVBean kvBean = new KVBean();
                kvBean.setText(text);
                kvBean.setValue(event.getName());
                return kvBean;
            }
        });
        Iterator<KVBean> iterator = kvBeans.iterator();
        while (iterator.hasNext()) {
            if(iterator.next() == null) {
                iterator.remove();
            }
        }

        return ResultData.success(kvBeans);
    }

}
