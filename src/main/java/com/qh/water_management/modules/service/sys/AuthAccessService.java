package com.qh.water_management.modules.service.sys;

import com.qh.water_management.modules.entity.sys.ResourceManagement;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/10/26 10:07
 * @Description:
 */
public interface AuthAccessService {

    /**
     * 获取菜单
     * @param userId 用户id
     * @return 菜单数据
     */
    List<ResourceManagement> getTopMenuList(String userId);

    /**
     * 根据菜单id获取菜单信息
     * @param params 查询条件
     * @param userId 当前用户
     * @return
     */
    List<ResourceManagement> getMenuListByRoleIdAndCodeSetId(Map<String, Object> params, String userId);

    /**
     *
     * @param params
     * @param userId
     * @return
     */
    List<ResourceManagement> getMenuListByPid(Map<String, Object> params, String userId);

    List<Map<String, Object>> getListByRoleId(Map<String, Object> params);

    List<Map<String, Object>> getListByRoleIdAndParentId(Map<String, Object> params);

    void syncMenu(Map<String, Object> params) throws Exception;

    List<Object> grant(Map<String, Object> params);
}
