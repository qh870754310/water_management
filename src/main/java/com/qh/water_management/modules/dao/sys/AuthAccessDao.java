package com.qh.water_management.modules.dao.sys;

import com.qh.water_management.modules.entity.sys.AuthAccess;
import com.qh.water_management.modules.entity.sys.ResourceManagement;
import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/10/26 10:22
 * @Description:
 */
@Mapper
public interface AuthAccessDao {

    /**
     *
     * @param hashedMap
     * @return
     */
    List<ResourceManagement> getTopMenuList(HashedMap hashedMap);

    /**
     *
     * @param userId
     * @return
     */
    List<ResourceManagement> getTopMenuListByUserId(String userId);

    /**
     *
     * @param params
     * @return
     */
    List<ResourceManagement> getMenuListByRoleIdAndCodeSetId(Map<String, Object> params);

    /**
     *
     * @param params
     * @return
     */
    List<ResourceManagement> getMenuListByPid(Map<String, Object> params);

    List<Map<String, Object>> getListByRoleId(Map<String, Object> params);

    List<Map<String, Object>> getListByRoleIdAndParentId(Map<String, Object> params);

    void syncMenu(Map<String, Object> params);

    AuthAccess getMenuByMenuIdAndRoleId(Map param);

    int grant(List<Object> list);
}
