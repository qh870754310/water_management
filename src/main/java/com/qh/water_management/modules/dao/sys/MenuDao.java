package com.qh.water_management.modules.dao.sys;

import com.qh.water_management.modules.common.dao.BaseDao;
import com.qh.water_management.modules.entity.sys.ResourceManagement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/11/20 14:29
 * @Description:
 */
@Mapper
public interface MenuDao extends BaseDao<ResourceManagement> {

    List<ResourceManagement> getMenuList(Map<String, Object> map);

    List<ResourceManagement> queryMenuByUserId(String userId);

    /**
     * 根据父菜单id查询菜单
     * @param parentId
     * @return
     */
    List<ResourceManagement> queryListParentId(String parentId);

    List<ResourceManagement> getMenuListByRoleIdAndCodeSetId(Map<String, Object> params);

    /**
     *
     * @param params
     * @return
     */
    List<ResourceManagement> getMenuListByPid(Map<String, Object> params);

    /**
     * 获取一级菜单
     * @param levelId 层级id
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
}
