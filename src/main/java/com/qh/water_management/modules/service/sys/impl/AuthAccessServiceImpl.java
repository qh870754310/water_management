package com.qh.water_management.modules.service.sys.impl;

import com.qh.water_management.modules.common.utils.ShiroUtils;
import com.qh.water_management.modules.common.utils.Utils;
import com.qh.water_management.modules.dao.sys.AuthAccessDao;
import com.qh.water_management.modules.entity.sys.AuthAccess;
import com.qh.water_management.modules.entity.sys.ResourceManagement;
import com.qh.water_management.modules.entity.sys.User;
import com.qh.water_management.modules.service.sys.AuthAccessService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author: qh
 * @Date: 2018/10/26 10:08
 * @Description:
 */
@Service("authAccessService")
public class AuthAccessServiceImpl implements AuthAccessService {

    @Autowired
    private AuthAccessDao authAccessDao;

    @Override
    public List<ResourceManagement> getTopMenuList(String userId) {
        List<ResourceManagement> resourceManagements;
        if (userId.equals(com.qh.modules.common.common.Constant.SUPERR_USER)) {
            resourceManagements = authAccessDao.getTopMenuList(new HashedMap());
        } else {
            resourceManagements = authAccessDao.getTopMenuListByUserId(userId);
        }
        return resourceManagements;
    }

    @Override
    public List<ResourceManagement> getMenuListByRoleIdAndCodeSetId(Map<String, Object> params, String userId) {
        List<ResourceManagement> resourceManagements;
        if (!userId.equals(com.qh.modules.common.common.Constant.SUPERR_USER)) {
            params.put("userId", userId);
        }
        resourceManagements = authAccessDao.getMenuListByRoleIdAndCodeSetId(params);
        return resourceManagements;
    }

    @Override
    public List<ResourceManagement> getMenuListByPid(Map<String, Object> params, String userId) {
        List<ResourceManagement> resourceManagements;
        if (!userId.equals(com.qh.modules.common.common.Constant.SUPERR_USER)) {
            params.put("userId", userId);
        }
        resourceManagements = authAccessDao.getMenuListByPid(params);
        return resourceManagements;
    }

    @Override
    public List<Map<String, Object>> getListByRoleId(Map<String, Object> params) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (params.containsKey("menuId") && StringUtils.isNotEmpty(params.get("menuId").toString())) {
            list = authAccessDao.getListByRoleId(params);
            list.parallelStream().forEach(map -> {
                String id = (String) map.get("id");
                String roleId = (String) params.get("roleId");
                Map param = new HashMap();
                param.put("id", id);
                param.put("roleId", roleId);
                AuthAccess authAccess =  authAccessDao.getMenuByMenuIdAndRoleId(param);
                if (authAccess != null && authAccess.getId().length() > 0) {
                    map.put("accessAuth", 1);
                    map.put("roleId", authAccess.getRoleId());
                } else {
                    map.put("accessAuth", 0);
                    map.put("roleId", null);
                }

            });
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getListByRoleIdAndParentId(Map<String, Object> params) {
        return authAccessDao.getListByRoleIdAndParentId(params);
    }

    @Override
    @Transactional
    public void syncMenu(Map<String, Object> params) throws Exception {
        authAccessDao.syncMenu(params);
    }

    @Override
    @Transactional
    public List<Object> grant(Map<String, Object> params) {
        List<Object> list = new ArrayList<>();
        AuthAccess authAccess = new AuthAccess();
        User user = ShiroUtils.getUser();
        authAccess.setCreateId(user.getId());
        authAccess.setRoleId(params.get("roleId").toString());
        String id_str = (String) params.get("id");
        String[] ids = id_str.split(",");
        for (String id : ids) {
            authAccess.setId(Utils.uuid());
            authAccess.setCreateTime(new Date());
            authAccess.setMenuId(id);
            list.add(authAccess);
        }
        int grant = authAccessDao.grant(list);
        list.clear();
        for (int i = 0; i < grant; i++) {
            Map map = new LinkedHashMap();
            map.put("accessAuth", "1");
            list.add(map);
        }
        return list;
    }

}
