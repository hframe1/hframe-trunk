package com.hframework.controller.ext;

import com.hframe.domain.model.HfpmDataField;
import com.hframe.domain.model.HfpmDataField_Example;
import com.hframe.service.interfaces.IHfpmDataFieldSV;
import com.hframework.common.frame.ServiceFactory;
import com.hframework.common.util.StringUtils;
import com.hframework.web.bean.WebContext;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.util.IoUtil;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.explorer.ExplorerApp;
import org.activiti.explorer.ui.Images;
import org.activiti.explorer.ui.custom.PrettyTimeLabel;
import org.activiti.explorer.ui.custom.UserProfileLink;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangquanhong on 2017/1/16.
 */
@Controller
public class WorkflowController {

    @Resource
    private IHfpmDataFieldSV hfpmDataFieldSV;

    /**
     * 页面跳转
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/modeler.html")
    public ModelAndView modeler(@ModelAttribute("modelId") String modelId, @ModelAttribute("dataSetId") String dataSetId,

                                HttpServletRequest request, HttpServletResponse response) throws Throwable {
        ModelAndView mav = new ModelAndView();

        if(StringUtils.isBlank(modelId) && dataSetId != null && StringUtils.isNotBlank(dataSetId)) {
            HfpmDataField_Example example = new HfpmDataField_Example();
            example.createCriteria().andHfpmDataSetIdEqualTo(Long.valueOf(dataSetId)).andWorkfowModelIdIsNotNull().andWorkfowModelIdNotEqualTo("");
            List<HfpmDataField> hfpmDataFieldListByExample = hfpmDataFieldSV.getHfpmDataFieldListByExample(example);
            if(hfpmDataFieldListByExample != null && hfpmDataFieldListByExample.size() > 0) {
                modelId = hfpmDataFieldListByExample.get(0).getWorkfowModelId();
            }
        }

        mav.addObject("modelId", modelId);

        mav.setViewName("/modeler");
        return mav;
    }

}
