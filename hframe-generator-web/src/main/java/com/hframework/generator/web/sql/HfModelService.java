package com.hframework.generator.web.sql;

import com.google.common.collect.Lists;
import com.hframe.domain.model.*;
import com.hframe.service.interfaces.*;
import com.hframework.common.ext.CollectionUtils;
import com.hframework.common.ext.Mapper;
import com.hframework.common.frame.ServiceFactory;
import com.hframework.generator.web.bean.HfModelContainer;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zhangquanhong on 2016/8/2.
 */
public class HfModelService {
    @Resource
    private IHfpmProgramSV iHfpmProgramSV = ServiceFactory.getService(IHfpmProgramSV.class);
    @Resource
    private IHfpmModuleSV iHfpmModuleSV = ServiceFactory.getService(IHfpmModuleSV.class);
    @Resource
    private IHfmdEntitySV iHfmdEntitySV = ServiceFactory.getService(IHfmdEntitySV.class);
    @Resource
    private IHfmdEntityAttrSV iHfmdEntityAttrSV = ServiceFactory.getService(IHfmdEntityAttrSV.class);
    @Resource
    private IHfpmDataSetSV iHfpmDataSetSV = ServiceFactory.getService(IHfpmDataSetSV.class);
    @Resource
    private IHfpmDataFieldSV iHfpmDataFieldSV = ServiceFactory.getService(IHfpmDataFieldSV.class);
    @Resource
    private IHfmdEnumClassSV hfmdEnumClassSV = ServiceFactory.getService(IHfmdEnumClassSV.class);


    public HfModelContainer getModelContainerFromDB(String programCode, String programeName, String moduleCode, String moduleName) throws Exception {

        HfModelContainer hfModelContainer = new HfModelContainer();

        HfpmProgram_Example example = new HfpmProgram_Example();
        example.or() .andHfpmProgramCodeEqualTo(programCode);
        List<HfpmProgram> hfpmProgramList = iHfpmProgramSV.getHfpmProgramListByExample(example);

        if(hfpmProgramList != null && hfpmProgramList.size() > 0) {
            HfpmProgram hfpmProgram = hfpmProgramList.get(0);
            Long programId = hfpmProgram.getHfpmProgramId();

            hfModelContainer.setProgram(hfpmProgram);

            //获取模块信息
            HfpmModule_Example moduleExample = new HfpmModule_Example();
            moduleExample.or().andHfpmProgramIdEqualTo(programId);
            List<HfpmModule> hfpmModuleList = iHfpmModuleSV.getHfpmModuleListByExample(moduleExample);

            if(hfpmModuleList != null && hfpmModuleList.size() > 0) {
                for (HfpmModule hfpmModule : hfpmModuleList) {
                    hfModelContainer.getModuleMap().put(hfpmModule.getHfpmModuleId(),hfpmModule);
                }
            }

            //获取实体信息
            HfmdEntity_Example entityExample = new HfmdEntity_Example();
            entityExample.or().andHfpmProgramIdEqualTo(programId);
            List<HfmdEntity> hfmdEntityList = iHfmdEntitySV.getHfmdEntityListByExample(entityExample);
            Map<String,HfmdEntity> entityMap = new HashMap<String, HfmdEntity>();
            Map<Long,HfmdEntity> entityIdEntityMap = new HashMap<Long, HfmdEntity>();
            if(hfmdEntityList != null && hfmdEntityList.size() > 0) {
                //实体信息<entityCode,HfmdEntity>
                for (HfmdEntity entity : hfmdEntityList) {
                    entityMap.put(entity.getHfmdEntityCode(),entity);
                    entityIdEntityMap.put(entity.getHfmdEntityId(),entity);
                }
            }
            hfModelContainer.setEntityMap(entityMap);

            //获取实体属�?�信�?
            HfmdEntityAttr_Example entityAttrExample = new HfmdEntityAttr_Example();
            entityAttrExample.or().andHfpmProgramIdEqualTo(programId);
            List<HfmdEntityAttr> hfmdEntityAttrList = iHfmdEntityAttrSV.getHfmdEntityAttrListByExample(entityAttrExample);
            if(hfmdEntityAttrList != null && hfmdEntityAttrList.size() > 0) {
                //实体属�?�信�?<entityCode.entityAttrCode,HfmdEntityAttr>
                Map<String,HfmdEntityAttr> entityAttrMap = new HashMap<String, HfmdEntityAttr>();
                for (HfmdEntityAttr hfmdEntityAttr : hfmdEntityAttrList) {
                    HfmdEntity entity =  entityIdEntityMap.get(hfmdEntityAttr.getHfmdEntityId());
                    if(entity == null) {//垃圾数据过滤
                        continue;
                    }

                    if("hfpm_page_cfg.hfpm_program_cfg_id".equals(entity.getHfmdEntityCode() + "." + hfmdEntityAttr.getHfmdEntityAttrCode())) {
                        System.out.println("1");
                    }

                    entityAttrMap.put(entity.getHfmdEntityCode() + "." + hfmdEntityAttr.getHfmdEntityAttrCode(),hfmdEntityAttr);
                }
                hfModelContainer.setEntityAttrMap(entityAttrMap);
            }

            //获取数据集信�?
            HfpmDataSet_Example dataSetExample = new HfpmDataSet_Example();
            dataSetExample.or() .andHfpmProgramIdEqualTo(programId);
            List<HfpmDataSet> hfpmDataSets = iHfpmDataSetSV.getHfpmDataSetListByExample(dataSetExample);
            if(hfpmDataSets != null) {
                Map<String, HfpmDataSet> dataSetMap = new HashMap<String, HfpmDataSet>();
                hfModelContainer.setDataSetMap(dataSetMap);
                Map<String, List<HfpmDataField>> dataFieldListMap = new HashMap<String, List<HfpmDataField>>();
                hfModelContainer.setDataFieldListMap(dataFieldListMap);
                for (HfpmDataSet hfpmDataSet : hfpmDataSets) {
                    dataSetMap.put(hfpmDataSet.getHfpmDataSetCode(),hfpmDataSet);
                    //获取数据列信�?
                    HfpmDataField_Example dataFieldExample = new HfpmDataField_Example();
                    dataFieldExample.or().andHfpmDataSetIdEqualTo(hfpmDataSet.getHfpmDataSetId());
                    List<HfpmDataField> hfpmDataFieldList = iHfpmDataFieldSV.getHfpmDataFieldListByExample(dataFieldExample);
                    dataFieldListMap.put(hfpmDataSet.getHfpmDataSetCode(), hfpmDataFieldList);
                }
            }

            //添加枚举值
            HfmdEnumClass_Example example1 = new HfmdEnumClass_Example();
            example1.createCriteria().andHfpmProgramIdEqualTo(programId);
            example1.or().andHfpmProgramIdIsNull();
            List<HfmdEnumClass> hfmdEnumClasses = hfmdEnumClassSV.getHfmdEnumClassListByExample(example1);
            hfModelContainer.setEnumClassCodeMap(CollectionUtils.convert(hfmdEnumClasses, new Mapper<String, HfmdEnumClass>() {
                public <K> K getKey(HfmdEnumClass hfmdEnumClass) {
                    return (K) hfmdEnumClass.getHfmdEnumClassCode();
                }
            }));
            hfModelContainer.setEnumClassMap(CollectionUtils.convert(hfmdEnumClasses, new Mapper<Long, HfmdEnumClass>() {
                public <K> K getKey(HfmdEnumClass hfmdEnumClass) {
                    return (K) hfmdEnumClass.getHfmdEnumClassId();
                }
            }));

        }

        return hfModelContainer;
    }

    private void executeModel(HfModelContainer hfModelContainer,int operType) throws Exception {
        //项目信息
        HfpmProgram hfpmProgram = hfModelContainer.getProgram();
        if(hfpmProgram != null) {
            if(operType == 1) {
                iHfpmProgramSV.create(hfpmProgram);
            }else {
                iHfpmProgramSV.update(hfpmProgram);
            }
        }

        //模块信息
        for (HfpmModule targetModule : hfModelContainer.getModuleMap().values()) {
            if(operType == 1) {
                iHfpmModuleSV.create(targetModule);
            }else {
                iHfpmModuleSV.update(targetModule);
            }
        }
        //实体信息
        Map<Long, Long> entityIdChangeMap = new HashMap<Long, Long>();
        Map<String, HfmdEntity> targetEntityMap = hfModelContainer.getEntityMap();
        if(targetEntityMap != null) {
            for (String entityCode : targetEntityMap.keySet()) {
                HfmdEntity targetEntity = targetEntityMap.get(entityCode);
                Long tempId = targetEntity.getHfmdEntityId();
                if(operType == 1) {
                    iHfmdEntitySV.create(targetEntity);
                }else {
                    iHfmdEntitySV.update(targetEntity);
                }
                entityIdChangeMap.put(tempId,targetEntity.getHfmdEntityId());
            }
        }

        //实体属�?�信�?
        Map<Long, Long> entityAttrIdChangeMap = new HashMap<Long, Long>();
        Map<String,HfmdEntityAttr> targetEntityAttrMap = hfModelContainer.getEntityAttrMap();
        if(targetEntityAttrMap != null) {
            List<HfmdEntityAttr> entityAttrList = new ArrayList<HfmdEntityAttr>(targetEntityAttrMap.values());
            Collections.sort(entityAttrList, new Comparator<HfmdEntityAttr>() {
                public int compare(HfmdEntityAttr o1, HfmdEntityAttr o2) {
                    if(o1.getRelHfmdEntityAttrId() == null && o2.getRelHfmdEntityAttrId() == null) return 0;
                    if(o1.getRelHfmdEntityAttrId() == null) return -1;
                    if(o2.getRelHfmdEntityAttrId() == null) return 1;
                    return 0;
                }
            });
            for (HfmdEntityAttr targetEntityAttr : entityAttrList) {
                Long tempId = targetEntityAttr.getHfmdEntityAttrId();
                Long hfmdEntityId = entityIdChangeMap.get(targetEntityAttr.getHfmdEntityId());
                if (hfmdEntityId != null) {
                    targetEntityAttr.setHfmdEntityId(hfmdEntityId);
                }

                if(targetEntityAttr.getRelHfmdEntityAttrId() != null && entityAttrIdChangeMap.containsKey(targetEntityAttr.getRelHfmdEntityAttrId())){
                    targetEntityAttr.setRelHfmdEntityAttrId(entityAttrIdChangeMap.get(targetEntityAttr.getRelHfmdEntityAttrId()));
                }

                if(operType == 1) {
                    iHfmdEntityAttrSV.create(targetEntityAttr);
                }else {
                    iHfmdEntityAttrSV.update(targetEntityAttr);
                }
                entityAttrIdChangeMap.put(tempId, targetEntityAttr.getHfmdEntityAttrId());
            }
        }
        Map<String, HfpmDataSet> dataSetMap = hfModelContainer.getDataSetMap();
        if (dataSetMap != null) {
            for (HfpmDataSet hfpmDataSet : dataSetMap.values()) {
                Long hfmdEntityId = entityIdChangeMap.get(hfpmDataSet.getMainHfmdEntityId());
                if (hfmdEntityId != null) {
                    hfpmDataSet.setMainHfmdEntityId(hfmdEntityId);
                }
                if(operType == 1) {
                    iHfpmDataSetSV.create(hfpmDataSet);
                }else {
                    iHfpmDataSetSV.update(hfpmDataSet);
                }
            }
        }
        Map<String, List<HfpmDataField>> dataFieldListMap = hfModelContainer.getDataFieldListMap();
        if(dataFieldListMap != null) {
            for (String dataSetCode : dataFieldListMap.keySet()) {
                List<HfpmDataField> hfpmDataFieldList =  dataFieldListMap.get(dataSetCode);
                if(hfpmDataFieldList != null) {
                    for (HfpmDataField hfpmDataField : hfpmDataFieldList) {
                        if(dataSetMap.get(dataSetCode) != null) {
                            hfpmDataField.setHfpmDataSetId(dataSetMap.get(dataSetCode).getHfpmDataSetId());
                        }

                        Long hfmdEntityId = entityIdChangeMap.get(hfpmDataField.getHfmdEntityId());
                        if (hfmdEntityId != null) {
                            hfpmDataField.setHfmdEntityId(hfmdEntityId);
                        }
                        Long entityAttrId = entityAttrIdChangeMap.get(hfpmDataField.getHfmdEntityAttrId());
                        if(entityAttrId != null) {
                            hfpmDataField.setHfmdEntityAttrId(entityAttrId);
                        }
                        if(operType == 1) {
                            iHfpmDataFieldSV.create(hfpmDataField);
                        }else {
                            iHfpmDataFieldSV.update(hfpmDataField);
                        }
                    }
                }
            }
        }
    }

    public void executeModelUpdate(HfModelContainer hfModelContainer) throws Exception {
        executeModel(hfModelContainer,2);
    }

    public void executeModelInsert(HfModelContainer hfModelContainer) throws Exception {
        executeModel(hfModelContainer,1);
    }

    public static HfModelService get(){
        return new HfModelService();
    }
}
