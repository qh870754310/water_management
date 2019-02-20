package com.qh.water_management.modules.service.sys.impl;

import com.qh.water_management.modules.common.utils.ShiroUtils;
import com.qh.water_management.modules.common.utils.Utils;
import com.qh.water_management.modules.dao.sys.SysAuthDataDao;
import com.qh.water_management.modules.entity.sys.AuthData;
import com.qh.water_management.modules.entity.sys.User;
import com.qh.water_management.modules.service.sys.SysAuthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/12/14 10:25
 * @Description:
 */
@Service("sysAuthDataService")
public class SysAuthDataServiceImpl implements SysAuthDataService {

    @Autowired
    private SysAuthDataDao sysAuthDataDao;

    @Override
    public List<Map<String, Object>> getListByLevelId(Integer levelId) {
        return sysAuthDataDao.getListByLevelId(levelId);
    }

    @Override
    public List<Map<String, Object>> getListByParentId(String parentId) {
        return sysAuthDataDao.getListByParentId(parentId);
    }

    @Override
    public Map<String, Object> getDetailByUuid(String uuid) {
        return sysAuthDataDao.getDetailByUuid(uuid);
    }

    @Override
    @Transactional
    public void save(AuthData authData) throws Exception {
        authData.setId(Utils.uuid());
        User user = ShiroUtils.getUser();
        authData.setCreateId(user.getId());
        authData.setCreateTime(new Date());
        if (sysAuthDataDao.save(authData) != 1) {
            throw new Exception("保存成功");
        }
    }

    @Override
    @Transactional
    public void deleteBatch(Map<String, Object> params) throws Exception {
        String ids = (String) params.get("id");
        String[] uuids = ids.split(",");
        sysAuthDataDao.delete(uuids);
    }

    @Override
    @Transactional
    public void update(AuthData authData) throws Exception {
        User user = ShiroUtils.getUser();
        authData.setUpdateId(user.getId());
        authData.setUpdateTime(new Date());
        if (sysAuthDataDao.update(authData) != 1) {
            throw new Exception("更新失败！");
        }
    }
}
