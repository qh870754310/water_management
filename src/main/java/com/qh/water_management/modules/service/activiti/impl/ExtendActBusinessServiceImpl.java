package com.qh.water_management.modules.service.activiti.impl;

import com.qh.water_management.modules.common.utils.ShiroUtils;
import com.qh.water_management.modules.common.utils.Utils;
import com.qh.water_management.modules.dao.activiti.ExtendActBusinessDao;
import com.qh.water_management.modules.entity.activiti.ExtendActBusinessEntity;
import com.qh.water_management.modules.entity.sys.User;
import com.qh.water_management.modules.service.activiti.ExtendActBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2019/1/9 13:35
 * @Description:
 */
@Service("businessService")
public class ExtendActBusinessServiceImpl implements ExtendActBusinessService {

    @Autowired
    private ExtendActBusinessDao extendActBusinessDao;

    @Override
    public List<Map<String, Object>> getListByLevelId(Map<String, Object> params) {
        return extendActBusinessDao.getListByLevelId(params);
    }

    @Override
    public List<Map<String, Object>> getListByActKeyAndLevelId(Map<String, Object> params) {
        return extendActBusinessDao.getListByActKeyAndLevelId(params);
    }

    @Override
    public List<Map<String, Object>> getListByParentId(Map<String, Object> params) {
        return extendActBusinessDao.getListByParentId(params);
    }

    @Override
    public String getFatherIds(String uuid) {
        return extendActBusinessDao.getFatherIds(uuid);
    }

    @Override
    public Map<String, Object> getDetailByUuid(String uuid) {
        return extendActBusinessDao.getDetailByUuid(uuid);
    }

    @Override
    @Transactional
    public void saveRoot(ExtendActBusinessEntity actBusinessEntity){
        actBusinessEntity.setId(Utils.uuid());
        User user = ShiroUtils.getUser();
        actBusinessEntity.setCreateId(user.getId());
        actBusinessEntity.setCreateTime(new Date());
        actBusinessEntity.setCreatorOrgId(user.getCreatorOrgId());
        actBusinessEntity.setLevelId(1);
        extendActBusinessDao.save(actBusinessEntity);
    }

    @Override
    @Transactional
    public void save(ExtendActBusinessEntity actBusinessEntity) {
        actBusinessEntity.setId(Utils.uuid());
        User user = ShiroUtils.getUser();
        actBusinessEntity.setCreateId(user.getId());
        actBusinessEntity.setCreateTime(new Date());
        actBusinessEntity.setCreatorOrgId(user.getCreatorOrgId());
        actBusinessEntity.setLevelId(actBusinessEntity.getLevelId() + 1);
        extendActBusinessDao.save(actBusinessEntity);
    }

    @Override
    @Transactional
    public void update(ExtendActBusinessEntity actBusinessEntity) {
        User user = ShiroUtils.getUser();
        actBusinessEntity.setUpdateId(user.getId());
        actBusinessEntity.setUpdateTime(new Date());
        extendActBusinessDao.update(actBusinessEntity);
    }

    @Override
    @Transactional
    public void delete(Map<String, Object> params) {
        String uuids = (String) params.get("id");
        String[] uuid_array = uuids.split(",");
        extendActBusinessDao.delete(uuid_array);
    }


}
