package com.qh.water_management.modules.service.sys.impl;

import com.qh.water_management.modules.common.utils.ShiroUtils;
import com.qh.water_management.modules.common.utils.Utils;
import com.qh.water_management.modules.dao.sys.AuthGroupDao;
import com.qh.water_management.modules.entity.sys.AuthGroup;
import com.qh.water_management.modules.entity.sys.User;
import com.qh.water_management.modules.service.sys.AuthGroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/12/12 18:03
 * @Description:
 */
@Service("authGroupService")
public class AuthGroupServiceImpl implements AuthGroupService {

    @Autowired
    private AuthGroupDao authGroupDao;

    @Override
    public List<Map<String,Object>> getListByLevelId(Integer levelId) {
        return authGroupDao.getListByLevelId(levelId);
    }

    @Override
    public List<AuthGroup> getListByCodeSetIdAndLevelId(Map<String, Object> params) {
        return authGroupDao.getListByCodeSetIdAndLevelId(params);
    }

    @Override
    public List<AuthGroup> getListByParentId(String parentId) {
        return authGroupDao.getListByParentId(parentId);
    }

    @Override
    public Map<String, Object> getDetailByUuid(String uuid) {
        return authGroupDao.getDetailByUuid(uuid);
    }

    @Override
    @Transactional
    public void save(AuthGroup authGroup) throws Exception {
        authGroup.setId(Utils.uuid());
        User user = ShiroUtils.getUser();
        authGroup.setCreateId(user.getId());
        authGroup.setCreateTime(new Date());
        authGroup.setCreatorOrgId(user.getCreatorOrgId());
        if (authGroupDao.save(authGroup) != 1) {
            throw new Exception("保存失败！");
        }
    }

    @Override
    @Transactional
    public Integer update(AuthGroup authGroup) throws Exception {
        User user = ShiroUtils.getUser();
        authGroup.setUpdateId(user.getId());
        authGroup.setUpdateTime(new Date());
        return authGroupDao.update(authGroup);
    }

    @Override
    @Transactional
    public void delete(Map<String, Object> params) throws Exception {
        String uuids = (String) params.get("id");
        String[] uuid_array = uuids.split(",");
        if (authGroupDao.delete(uuid_array) != uuid_array.length) {
            throw new Exception("删除失败");
        }
    }
}
