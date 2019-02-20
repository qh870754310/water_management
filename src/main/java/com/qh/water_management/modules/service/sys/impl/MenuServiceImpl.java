package com.qh.water_management.modules.service.sys.impl;

import com.qh.modules.common.common.Constant;
import com.qh.water_management.modules.common.utils.ShiroUtils;
import com.qh.water_management.modules.common.utils.Utils;
import com.qh.water_management.modules.dao.sys.MenuDao;
import com.qh.water_management.modules.entity.sys.ResourceManagement;
import com.qh.water_management.modules.entity.sys.User;
import com.qh.water_management.modules.service.sys.MenuService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: qh
 * @Date: 2018/11/20 14:27
 * @Description:
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<ResourceManagement> getMenu(String userId) {
        List<ResourceManagement> resourceManagements = null;
        if (userId.equals(Constant.SUPERR_USER)) {
            resourceManagements = menuDao.getMenuList(new HashedMap());
        } else {
            resourceManagements = menuDao.queryMenuByUserId(userId);
        }
        List<String> menuIds = new ArrayList<>();
        menuIds = resourceManagements.parallelStream().filter(menu -> menu != null && null != menu.getId()).map(ResourceManagement::getId).collect(Collectors.toList());
        //查询出根菜单
        List<ResourceManagement> rootMenus = queryMenuByParentId("1", menuIds);
        //递归查询除所有子资源的子资源
        List<ResourceManagement> treeMenus = getTreeMenus(rootMenus, menuIds);
        return treeMenus;
    }

    @Override
    public List<ResourceManagement> getMenuListByRoleIdAndCodeSetId(Map<String, Object> params, String userId) {
        List<ResourceManagement> resourceManagements = null;
        if (!userId.equals(Constant.SUPERR_USER)) {
            params.put("userId", userId);
        }
        resourceManagements = menuDao.getMenuListByRoleIdAndCodeSetId(params);
        //递归查询除所有子资源的子资源

        return resourceManagements;
    }

    @Override
    public List<ResourceManagement> getMenuListByPid(String parentId, String userId) {
        List<ResourceManagement> resourceManagements = null;
        Map<String, Object> params = new HashMap<>();
        params.put("parentId", parentId);
        if (!userId.equals(Constant.SUPERR_USER)) {
            params.put("userId", userId);
        }
        resourceManagements = menuDao.getMenuListByPid(params);
        //递归查询除所有子资源的子资源
        return resourceManagements;
    }

    @Override
    public List<Map> getListByLevelId(Integer levelId) {
        return menuDao.getListByLevelId(levelId);
    }

    @Override
    public List<Map> getListByPid(String parentId) {
        return menuDao.getListByPid(parentId);
    }

    @Override
    public String getFatherIds(String uuid) {
        return menuDao.getFatherIds(uuid);
    }

    @Override
    @Transactional
    public void save(ResourceManagement resourceManagement) throws Exception {
        resourceManagement.setId(Utils.uuid());
        User user = ShiroUtils.getUser();
        resourceManagement.setCreateId(user.getId());
        resourceManagement.setCreateTime(new Date());
        resourceManagement.setLevelId(resourceManagement.getLevelId() + 1);
        resourceManagement.setCreatorOrgId(user.getCreatorOrgId());
        if (menuDao.save(resourceManagement) != 1) {
            throw new Exception("保存失败！");
        }
    }

    @Override
    public Map<String, Object> getDetailByUuid(String uuid) {
        return menuDao.getDetailByUuid(uuid);
    }

    @Override
    @Transactional
    public void update(ResourceManagement resourceManagement) throws Exception {
        User user = ShiroUtils.getUser();
        resourceManagement.setUpdateId(user.getId());
        resourceManagement.setUpdateTime(new Date());
        if (menuDao.update(resourceManagement) != 1) {
            throw new Exception("更新失败!");
        }
    }

    @Override
    @Transactional
    public void delete(String[] uuids) throws Exception {
        if (menuDao.delete(uuids) != uuids.length) {
            throw new Exception("删除失败！");
        }
    }

    /**
     * 递归查出所有菜单的子菜单，子菜单的所有子菜单，只包括用户授权的资源
     * @param resources 源菜单
     * @param menuIds   用户所有授权资源
     * @return
     */
    private List<ResourceManagement> getTreeMenus(List<ResourceManagement> resources, List<String> menuIds) {
        List<ResourceManagement> treeMenus = new ArrayList<>();
        for (ResourceManagement menu: resources) {
            if (Constant.MenuType.MENU.getValue().equals(menu.getResourceType())) {
                List<ResourceManagement> childMenus = queryMenuByParentId(menu.getId(), menuIds);
                menu.setList(getTreeMenus(childMenus, menuIds));
            }
            treeMenus.add(menu);
        }
        return treeMenus;
    }

    /**
     * 根据上级父id，查询除下级所有该用户已经授权的资源
     * @param parentId 父id
     * @param menuIds 授权资源ids
     * @return
     */
    private List<ResourceManagement> queryMenuByParentId(String parentId, List<String> menuIds) {
        //根据父id，查询所有下级菜单
        List<ResourceManagement> resourceManagements = menuDao.queryListParentId(parentId);
        List<ResourceManagement> reMenus = resourceManagements.parallelStream().filter(menu -> menuIds.contains(menu.getId())).collect(Collectors.toList());
        return reMenus;
    }
}
