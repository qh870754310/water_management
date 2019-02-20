package com.qh.water_management.modules.service.sys;

import com.qh.water_management.modules.entity.sys.AuthGroup;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/12/12 18:02
 * @Description:
 */
public interface AuthGroupService {

    List<Map<String,Object>> getListByLevelId(Integer levelId);

    List<AuthGroup> getListByCodeSetIdAndLevelId(Map<String, Object> params);

    List<AuthGroup> getListByParentId(String parentId);

    Map<String, Object> getDetailByUuid(String uuid);

    void save(AuthGroup authGroup) throws Exception;

    Integer update(AuthGroup authGroup) throws Exception;

    void delete(Map<String, Object> params) throws Exception;
}
