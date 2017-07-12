package com.hframe.controller;

import com.hframework.beans.controller.Pagination;
import com.hframework.beans.controller.ResultCode;
import com.hframework.beans.controller.ResultData;
import com.hframework.common.util.ExampleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.ServletRequestDataBinder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import com.hframework.web.ControllerHelper;
import com.hframe.domain.model.HfcfgProgramTemplate;
import com.hframe.domain.model.HfcfgProgramTemplate_Example;
import com.hframe.service.interfaces.IHfcfgProgramTemplateSV;

@Controller
@RequestMapping(value = "/hframe/hfcfgProgramTemplate")
public class HfcfgProgramTemplateController   {
    private static final Logger logger = LoggerFactory.getLogger(HfcfgProgramTemplateController.class);

	@Resource
	private IHfcfgProgramTemplateSV iHfcfgProgramTemplateSV;
  




    @InitBinder
    protected void initBinder(HttpServletRequest request,
        ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    /**
     * 查询展示项目模板列表
     * @param hfcfgProgramTemplate
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryListByAjax.json")
    @ResponseBody
    public ResultData list(@ModelAttribute("hfcfgProgramTemplate") HfcfgProgramTemplate hfcfgProgramTemplate,
                                      @ModelAttribute("example") HfcfgProgramTemplate_Example example, Pagination pagination){
        logger.debug("request : {},{},{}", hfcfgProgramTemplate, example, pagination);
        try{
            ExampleUtils.parseExample(hfcfgProgramTemplate, example);
            //设置分页信息
            example.setLimitStart(pagination.getStartIndex());
            example.setLimitEnd(pagination.getEndIndex());

            final List< HfcfgProgramTemplate> list = iHfcfgProgramTemplateSV.getHfcfgProgramTemplateListByExample(example);
            pagination.setTotalCount(iHfcfgProgramTemplateSV.getHfcfgProgramTemplateCountByExample(example));

            return ResultData.success().add("list",list).add("pagination",pagination);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 查询展示项目模板明细
     * @param hfcfgProgramTemplate
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryOneByAjax.json")
    @ResponseBody
    public ResultData detail(@ModelAttribute("hfcfgProgramTemplate") HfcfgProgramTemplate hfcfgProgramTemplate){
        logger.debug("request : {},{}", hfcfgProgramTemplate.getHfcfgProgramTemplateId(), hfcfgProgramTemplate);
        try{
            HfcfgProgramTemplate result = iHfcfgProgramTemplateSV.getHfcfgProgramTemplateByPK(hfcfgProgramTemplate.getHfcfgProgramTemplateId());
            if(result != null) {
                return ResultData.success(result);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
    * 创建项目模板
    * @param hfcfgProgramTemplate
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.json")
    @ResponseBody
    public ResultData create(@ModelAttribute("hfcfgProgramTemplate") HfcfgProgramTemplate hfcfgProgramTemplate) {
        logger.debug("request : {}", hfcfgProgramTemplate);
        try {
            ControllerHelper.setDefaultValue(hfcfgProgramTemplate, "hfcfgProgramTemplateId");
            int result = iHfcfgProgramTemplateSV.create(hfcfgProgramTemplate);
            if(result > 0) {
                return ResultData.success(hfcfgProgramTemplate);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 批量维护项目模板
    * @param hfcfgProgramTemplates
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createsByAjax.json")
    @ResponseBody
    public ResultData batchCreate(@RequestBody HfcfgProgramTemplate[] hfcfgProgramTemplates) {
        logger.debug("request : {}", hfcfgProgramTemplates);

        try {
            ControllerHelper.setDefaultValue(hfcfgProgramTemplates, "hfcfgProgramTemplateId");
            ControllerHelper.reorderProperty(hfcfgProgramTemplates);

            int result = iHfcfgProgramTemplateSV.batchOperate(hfcfgProgramTemplates);
            if(result > 0) {
                return ResultData.success(hfcfgProgramTemplates);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 更新项目模板
    * @param hfcfgProgramTemplate
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.json")
    @ResponseBody
    public ResultData update(@ModelAttribute("hfcfgProgramTemplate") HfcfgProgramTemplate hfcfgProgramTemplate) {
        logger.debug("request : {}", hfcfgProgramTemplate);
        try {
            ControllerHelper.setDefaultValue(hfcfgProgramTemplate, "hfcfgProgramTemplateId");
            int result = iHfcfgProgramTemplateSV.update(hfcfgProgramTemplate);
            if(result > 0) {
                return ResultData.success(hfcfgProgramTemplate);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 删除项目模板
    * @param hfcfgProgramTemplate
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.json")
    @ResponseBody
    public ResultData delete(@ModelAttribute("hfcfgProgramTemplate") HfcfgProgramTemplate hfcfgProgramTemplate) {
        logger.debug("request : {}", hfcfgProgramTemplate);

        try {
            ControllerHelper.setDefaultValue(hfcfgProgramTemplate, "hfcfgProgramTemplateId");
            int result = iHfcfgProgramTemplateSV.delete(hfcfgProgramTemplate);
            if(result > 0) {
                return ResultData.success(hfcfgProgramTemplate);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }
  	//getter
 	
	public IHfcfgProgramTemplateSV getIHfcfgProgramTemplateSV(){
		return iHfcfgProgramTemplateSV;
	}
	//setter
	public void setIHfcfgProgramTemplateSV(IHfcfgProgramTemplateSV iHfcfgProgramTemplateSV){
    	this.iHfcfgProgramTemplateSV = iHfcfgProgramTemplateSV;
    }
}
