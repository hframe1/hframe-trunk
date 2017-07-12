package com.hframe.service.handler;

import com.hframe.domain.model.*;
import com.hframe.service.interfaces.*;
import com.hframework.base.bean.AbstractBusinessHandler;
import com.hframework.base.service.DataSetLoaderHelper;
import com.hframework.base.service.DataSetLoaderService;
import com.hframework.common.annotation.extension.AfterCreateHandler;
import com.hframework.common.annotation.extension.AfterDeleteHandler;
import com.hframework.common.annotation.extension.AfterUpdateHandler;
import com.hframework.common.annotation.extension.BeforeUpdateHandler;
import com.hframework.generator.web.bean.HfModelContainerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangquanhong on 2016/10/14.
 */
@Service
public class HfmdEntityHandler extends AbstractBusinessHandler<HfmdEntity> {

    private static final long ENUM_CLASS_DEFAULT_HOLDER = 2;

    @Resource
    private IHfpmDataSetSV hfpmDataSetSV;
    @Resource
    private IHfpmProgramSV hfpmProgramSV;

    @AfterCreateHandler
    @AfterUpdateHandler
    public boolean updateDataSetService(HfmdEntity hfmdEntity) throws Exception {
        HfpmProgram hfpmProgram = hfpmProgramSV.getHfpmProgramByPK(hfmdEntity.getHfpmProgramId());

        String companyCode = "hframe";
        String programCode = hfpmProgram.getHfpmProgramCode();

        DataSetLoaderService dataSetLoaderService = DataSetLoaderHelper.getDataSetLoaderService(
                companyCode, programCode, null);
        dataSetLoaderService.overrideHfmdEntity(hfmdEntity);
        return true;
    }

    @AfterCreateHandler
    public boolean generateDataSet(HfmdEntity hfmdEntity) throws Exception {
        HfpmDataSet dataSet = HfModelContainerUtil.getDataSetFromEntity(hfmdEntity);
        hfpmDataSetSV.create(dataSet);
        HfpmDataSet qryDataSet = HfModelContainerUtil.getQryDataSetFromEntity(hfmdEntity);
        hfpmDataSetSV.create(qryDataSet);
        return true;
    }

    @AfterDeleteHandler
    public boolean deleteDataSet(HfmdEntity hfmdEntity) throws Exception {
        HfpmDataSet_Example example = new HfpmDataSet_Example();
        example.createCriteria().andMainHfmdEntityIdEqualTo(hfmdEntity.getHfmdEntityId());
        List<HfpmDataSet> hfpmDataSetList = hfpmDataSetSV.getHfpmDataSetListByExample(example);
        if(hfpmDataSetList != null) {
            for (HfpmDataSet dataSet : hfpmDataSetList) {
                hfpmDataSetSV.delete(dataSet);
            }
        }
        return true;
    }







}
