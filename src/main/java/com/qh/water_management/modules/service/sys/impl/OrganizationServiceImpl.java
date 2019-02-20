package com.qh.water_management.modules.service.sys.impl;

import com.qh.water_management.modules.common.utils.ShiroUtils;
import com.qh.water_management.modules.common.utils.Utils;
import com.qh.water_management.modules.dao.sys.OrganizationDao;
import com.qh.water_management.modules.entity.sys.Organization;
import com.qh.water_management.modules.entity.sys.User;
import com.qh.water_management.modules.service.sys.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/12/10 17:46
 * @Description:
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public List<Map<String, Object>> getListByLevelId(Integer levelId) {
        return organizationDao.getListByLevelId(levelId);
    }

    @Override
    public List<Map<String, Object>> getListByParentId(String parentId) {
        return organizationDao.getListByParentId(parentId);
    }

    @Override
    @Transactional
    public void saveRoot(Organization organization) throws Exception {
        organization.setId(Utils.uuid());
        User user = ShiroUtils.getUser();
        List<Map<String, Object>> listByLevelId = organizationDao.getListByLevelId(0);
        organization.setParentId((String) listByLevelId.get(0).get("uuid"));
        organization.setCreateId(user.getId());
        organization.setCreateTime(new Date());
        organization.setCreatorOrgId(user.getCreatorOrgId());
        if (organizationDao.saveRoot(organization) != 1) {
            throw new Exception("保存失败！");
        }
    }

    @Override
    @Transactional
    public void save(Organization organization) throws Exception {
        organization.setId(Utils.uuid());
        User user = ShiroUtils.getUser();
        organization.setCreateId(user.getId());
        organization.setCreateTime(new Date());
        organization.setCreatorOrgId(user.getCreatorOrgId());
        organization.setLevelId(organization.getLevelId() + 1);
        if (organizationDao.saveRoot(organization) != 1) {
            throw new Exception("保存失败！");
        }
    }

    @Override
    @Transactional
    public void update(Organization organization) throws Exception {
        User user = ShiroUtils.getUser();
        organization.setUpdateId(user.getId());
        organization.setUpdateTime(new Date());
        if (organizationDao.update(organization) != 1) {
            throw new Exception("更新失败！");
        }
    }

    @Override
    @Transactional
    public void delete(Map<String, Object> params) throws Exception {
        String uuids = (String) params.get("id");
        String[] uuid_array = uuids.split(",");
        if (organizationDao.delete(uuid_array) != uuid_array.length) {
            throw new Exception("删除失败");
        }
    }

    @Override
    public String getFatherIds(String id) {
        return organizationDao.getFatherIds(id);
    }

    @Override
    public Map<String, Object> getDetailByUuid(String uuid) {
        return organizationDao.getDetailByUuid(uuid);
    }
}
