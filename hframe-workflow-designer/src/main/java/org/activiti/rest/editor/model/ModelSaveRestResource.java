package org.activiti.rest.editor.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hframe.domain.model.HfpmDataField;
import com.hframe.domain.model.HfpmDataField_Example;
import com.hframe.domain.model.HfpmDataSet;
import com.hframe.domain.model.HfpmProgram;
import com.hframe.service.interfaces.IHfpmDataFieldSV;
import com.hframe.service.interfaces.IHfpmDataSetSV;
import com.hframe.service.interfaces.IHfpmProgramSV;
import com.hframework.common.util.FileUtils;
import com.hframework.web.bean.WebContextHelper;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by zhangquanhong on 2017/1/11.
 */
@RestController
public class ModelSaveRestResource implements ModelDataJsonConstants {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ModelSaveRestResource.class);

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IHfpmDataFieldSV hfpmDataFieldSV;

    @Autowired
    private IHfpmDataSetSV hfpmDataSetSV;

    @Autowired
    private IHfpmProgramSV hfpmProgramSV;

    @RequestMapping(value="/model/{modelId}/save", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveModel(@PathVariable String modelId, String name , String description, String json_xml, String svg_xml) {
        try {

            Model model = repositoryService.getModel(modelId);

            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());

            modelJson.put(MODEL_NAME, name);
            modelJson.put(MODEL_DESCRIPTION, description);
            model.setMetaInfo(modelJson.toString());
            model.setName(name);

            repositoryService.saveModel(model);

            repositoryService.addModelEditorSource(model.getId(), json_xml.getBytes());

            InputStream svgStream = new ByteArrayInputStream(svg_xml.getBytes());
            TranscoderInput input = new TranscoderInput(svgStream);

            PNGTranscoder transcoder = new PNGTranscoder();
            // Setup output
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(outStream);

            // Do the transformation
            transcoder.transcode(input, output);
            final byte[] result = outStream.toByteArray();
            repositoryService.addModelEditorSourceExtra(model.getId(), result);
            outStream.close();

            HfpmDataField_Example example = new HfpmDataField_Example();
            example.createCriteria().andWorkfowModelIdEqualTo(modelId);
            List<HfpmDataField> hfpmDataFieldList = hfpmDataFieldSV.getHfpmDataFieldListByExample(example);
            if(hfpmDataFieldList.size() > 0) {
                HfpmDataSet hfpmDataSet = hfpmDataSetSV.getHfpmDataSetByPK(hfpmDataFieldList.get(0).getHfpmDataSetId());
                HfpmProgram hfpmProgram = hfpmProgramSV.getHfpmProgramByPK(hfpmDataSet.getHfpmProgramId());

                String companyCode = "hframe";
                String programCode = hfpmProgram.getHfpmProgramCode();


                final ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(json_xml.getBytes());
                BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(modelNode);
                byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);
                String workflowContent = new String(bpmnBytes);
                String workflowFileName = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";

                WebContextHelper contextHelper = new WebContextHelper(companyCode, programCode, null, null);
                String workflowFilePath = contextHelper.programConfigRootDir + "/" +
                        contextHelper.programConfigDataSetDir + "/process/" + workflowFileName;
                FileUtils.writeFile(workflowFilePath, workflowContent);
            }
        } catch (Exception e) {
            LOGGER.error("Error saving model", e);
            throw new ActivitiException("Error saving model", e);
        }
    }
}
