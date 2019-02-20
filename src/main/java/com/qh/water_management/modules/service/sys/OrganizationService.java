package com.qh.water_management.modules.service.sys;

import com.qh.water_management.modules.entity.sys.Organization;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/12/10 17:46
 * @Description:
 */
public interface OrganizationService {

    List<Map<String, Object>> getListByLevelId(Integer levelId);

    List<Map<String, Object>> getListByParentId(String parentId);

    void saveRoot(Organization organization) throws Exception;

    void save(Organization organization) throws Exception;

    void update(Organization organization) throws Exception;

    void delete(Map<String, Object> params) throws Exception;

    String getFatherIds(String id);

    Map<String, Object> getDetailByUuid(String uuid);
}
