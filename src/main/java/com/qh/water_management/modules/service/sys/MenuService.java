package com.qh.water_management.modules.service.sys;

import com.qh.water_management.modules.entity.sys.ResourceManagement;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/11/20 14:26
 * @Description: 系统菜单-业务逻辑接口
 */
public interface MenuService {

    /**
     * 获取菜单
     * @param userId  用户id
     * @return 菜单数据
     */
    List<ResourceManagement> getMenu(String userId);

    /**
     * 根据菜单id获取菜单信息
     * @param params 查询条件
     * @param userId 当前用户
     * @return
     */
    List<ResourceManagement> getMenuListByRoleIdAndCodeSetId(Map<String, Object> params, String userId);

    /**
     * 根据父菜单id查询信息
     * @param parentId 父菜单id
     * @param userId 当前用户
     * @return
     */
    List<ResourceManagement> getMenuListByPid(String parentId, String userId);

    /**
     * 获取一级菜单
     * @param levelId 层级
     * @return
     */
    List<Map> getListByLevelId(Integer levelId);

    /**
     * 根据父菜单id查询子菜单
     * @param parentId 父菜单id
     * @return
     */
    List<Map> getListByPid(String parentId);

    /**
     * 根据子节点查询父节点
     * @param uuid
     * @return
     */
    String getFatherIds(String uuid);

    /**
     * 保存
     * @param resourceManagement
     * @throws Exception
     */
    void save(ResourceManagement resourceManagement) throws Exception;

    /**
     * 根据UUid查询
     * @param uuid
     * @return
     */
    Map<String, Object> getDetailByUuid(String uuid);

    /**
     * 编辑
     * @param resourceManagement
     * @throws Exception
     */
    void update(ResourceManagement resourceManagement) throws Exception;

    /**
     * 删除
     * @param uuids
     * @throws Exception
     */
    void delete(String[] uuids) throws Exception;
}
