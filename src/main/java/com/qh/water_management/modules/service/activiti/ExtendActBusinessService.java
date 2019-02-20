package com.qh.water_management.modules.service.activiti;

import com.qh.water_management.modules.entity.activiti.ExtendActBusinessEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2019/1/9 13:34
 * @Description: 业务流程  对应的 业务表
 */
public interface ExtendActBusinessService {

    List<Map<String, Object>> getListByLevelId(Map<String, Object> params);

    List<Map<String, Object>>  getListByActKeyAndLevelId(Map<String, Object> params);

    List<Map<String, Object>> getListByParentId(Map<String, Object> params);

    Map<String, Object> getDetailByUuid(String uuid);

    String getFatherIds(String uuid);

    void saveRoot(ExtendActBusinessEntity actBusinessEntity);

    void save(ExtendActBusinessEntity actBusinessEntity);

    void update(ExtendActBusinessEntity actBusinessEntity);

    void delete(Map<String, Object> params);
}
