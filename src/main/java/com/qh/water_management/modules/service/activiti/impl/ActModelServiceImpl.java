package com.qh.water_management.modules.service.activiti.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.qh.modules.common.common.Constant;
import com.qh.water_management.modules.dao.activiti.ExtendActModelDao;
import com.qh.water_management.modules.entity.activiti.ExtendActModelEntity;
import com.qh.water_management.modules.service.activiti.ActModelService;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

/**
 * @Author: qh
 * @Date: 2019/1/10 16:48
 * @Description: activiti模型接口实现类
 */
@Service
public class ActModelServiceImpl implements ActModelService {

    /**
     * RepositoryService是使用Activiti引擎时最先接触的服务。 它提供了管理和控制发布包和流程定义的操作
     */
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ExtendActModelDao extendActModelDao;


    /**
     * 创建模型
     * @param extendActModelEntity  模型信息数据
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public String CreateModel(ExtendActModelEntity extendActModelEntity) throws Exception {
        //editorInfo
        ObjectNode editorNode = objectMapper.createObjectNode();
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);

        //创建模型
        Model model = repositoryService.newModel();

        //metaInfo元信息
        ObjectNode metaNode = objectMapper.createObjectNode();
        metaNode.put(ModelDataJsonConstants.MODEL_NAME, extendActModelEntity.getName());
        metaNode.put(ModelDataJsonConstants.MODEL_REVISION, extendActModelEntity.getActVersion());
        metaNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, extendActModelEntity.getDescription());
        model.setMetaInfo(metaNode.toString());
        model.setKey(extendActModelEntity.getActKey());
        model.setName(extendActModelEntity.getName());

        //保存模型
        repositoryService.saveModel(model);
        //设置模型的可编辑源码
        repositoryService.addModelEditorSource(model.getId(), editorNode.toString().getBytes(StandardCharsets.UTF_8));

        //保存模型扩展表
        extendActModelEntity.setActVersion(model.getVersion());
        extendActModelEntity.setModelId(model.getId());
        extendActModelEntity.setStatus(StringUtils.isEmpty(model.getDeploymentId()) ? Constant.YESNO.NO.getValue() : Constant.YESNO.YES.getValue());
        extendActModelEntity.setDeploymentId(model.getDeploymentId());
        extendActModelDao.save(extendActModelEntity);
        return model.getId();
    }
}
