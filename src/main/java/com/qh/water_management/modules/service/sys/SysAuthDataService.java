package com.qh.water_management.modules.service.sys;

import com.qh.water_management.modules.entity.sys.AuthData;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/12/14 10:24
 * @Description:
 */
public interface SysAuthDataService {

    List<Map<String,Object>> getListByLevelId(Integer levelId);

    List<Map<String,Object>> getListByParentId(String parentId);

    Map<String, Object> getDetailByUuid(String uuid);

    void save(AuthData authData) throws Exception;

    void deleteBatch(Map<String, Object> params) throws Exception;

    void update(AuthData authData) throws Exception;
}
