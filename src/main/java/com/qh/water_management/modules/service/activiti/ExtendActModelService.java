package com.qh.water_management.modules.service.activiti;

import com.qh.water_management.modules.entity.activiti.ExtendActModelEntity;

import java.util.Map;

/**
 * @Author: qh
 * @Date: 2019/1/10 11:04
 * @Description:
 */
public interface ExtendActModelService {

    Map<String, Object> getPageSetData(Map<String, Object> params);

    Map<String, Object> getDetailByUuid(String uuid);

    String save(ExtendActModelEntity actModelEntity) throws Exception;

    int update(ExtendActModelEntity actModelEntity);

    void delete(String[] ids);
}
