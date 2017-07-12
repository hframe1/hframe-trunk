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
import com.hframe.domain.model.HfpmPageCfg;
import com.hframe.domain.model.HfpmPageCfg_Example;
import com.hframe.service.interfaces.IHfpmPageCfgSV;

@Controller
@RequestMapping(value = "/hframe/hfpmPageCfg")
public class HfpmPageCfgController   {
    private static final Logger logger = LoggerFactory.getLogger(HfpmPageCfgController.class);

	@Resource
	private IHfpmPageCfgSV iHfpmPageCfgSV;
  




    @InitBinder
    protected void initBinder(HttpServletRequest request,
        ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    /**
     * 查询展示页面配置列表
     * @param hfpmPageCfg
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryListByAjax.json")
    @ResponseBody
    public ResultData list(@ModelAttribute("hfpmPageCfg") HfpmPageCfg hfpmPageCfg,
                                      @ModelAttribute("example") HfpmPageCfg_Example example, Pagination pagination){
        logger.debug("request : {},{},{}", hfpmPageCfg, example, pagination);
        try{
            ExampleUtils.parseExample(hfpmPageCfg, example);
            //设置分页信息
            example.setLimitStart(pagination.getStartIndex());
            example.setLimitEnd(pagination.getEndIndex());

            final List< HfpmPageCfg> list = iHfpmPageCfgSV.getHfpmPageCfgListByExample(example);
            pagination.setTotalCount(iHfpmPageCfgSV.getHfpmPageCfgCountByExample(example));

            return ResultData.success().add("list",list).add("pagination",pagination);
        }catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }

    /**
     * 查询展示页面配置明细
     * @param hfpmPageCfg
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "/queryOneByAjax.json")
    @ResponseBody
    public ResultData detail(@ModelAttribute("hfpmPageCfg") HfpmPageCfg hfpmPageCfg){
        logger.debug("request : {},{}", hfpmPageCfg.getHfpmPageCfgId(), hfpmPageCfg);
        try{
            HfpmPageCfg result = iHfpmPageCfgSV.getHfpmPageCfgByPK(hfpmPageCfg.getHfpmPageCfgId());
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
    * 创建页面配置
    * @param hfpmPageCfg
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createByAjax.json")
    @ResponseBody
    public ResultData create(@ModelAttribute("hfpmPageCfg") HfpmPageCfg hfpmPageCfg) {
        logger.debug("request : {}", hfpmPageCfg);
        try {
            ControllerHelper.setDefaultValue(hfpmPageCfg, "hfpmPageCfgId");
            int result = iHfpmPageCfgSV.create(hfpmPageCfg);
            if(result > 0) {
                return ResultData.success(hfpmPageCfg);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 批量维护页面配置
    * @param hfpmPageCfgs
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/createsByAjax.json")
    @ResponseBody
    public ResultData batchCreate(@RequestBody HfpmPageCfg[] hfpmPageCfgs) {
        logger.debug("request : {}", hfpmPageCfgs);

        try {
            ControllerHelper.setDefaultValue(hfpmPageCfgs, "hfpmPageCfgId");
            ControllerHelper.reorderProperty(hfpmPageCfgs);

            int result = iHfpmPageCfgSV.batchOperate(hfpmPageCfgs);
            if(result > 0) {
                return ResultData.success(hfpmPageCfgs);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 更新页面配置
    * @param hfpmPageCfg
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/updateByAjax.json")
    @ResponseBody
    public ResultData update(@ModelAttribute("hfpmPageCfg") HfpmPageCfg hfpmPageCfg) {
        logger.debug("request : {}", hfpmPageCfg);
        try {
            ControllerHelper.setDefaultValue(hfpmPageCfg, "hfpmPageCfgId");
            int result = iHfpmPageCfgSV.update(hfpmPageCfg);
            if(result > 0) {
                return ResultData.success(hfpmPageCfg);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
        return ResultData.error(ResultCode.UNKNOW);
    }

    /**
    * 删除页面配置
    * @param hfpmPageCfg
    * @return
    * @throws Throwable
    */
    @RequestMapping(value = "/deleteByAjax.json")
    @ResponseBody
    public ResultData delete(@ModelAttribute("hfpmPageCfg") HfpmPageCfg hfpmPageCfg) {
        logger.debug("request : {}", hfpmPageCfg);

        try {
            ControllerHelper.setDefaultValue(hfpmPageCfg, "hfpmPageCfgId");
            int result = iHfpmPageCfgSV.delete(hfpmPageCfg);
            if(result > 0) {
                return ResultData.success(hfpmPageCfg);
            }else {
                return ResultData.error(ResultCode.RECODE_IS_NOT_EXISTS);
            }
        } catch (Exception e) {
            logger.error("error : ", e);
            return ResultData.error(ResultCode.ERROR);
        }
    }
  	//getter
 	
	public IHfpmPageCfgSV getIHfpmPageCfgSV(){
		return iHfpmPageCfgSV;
	}
	//setter
	public void setIHfpmPageCfgSV(IHfpmPageCfgSV iHfpmPageCfgSV){
    	this.iHfpmPageCfgSV = iHfpmPageCfgSV;
    }
}
