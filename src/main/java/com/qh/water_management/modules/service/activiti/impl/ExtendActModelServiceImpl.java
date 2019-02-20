package com.qh.water_management.modules.service.activiti.impl;

import com.qh.water_management.modules.common.utils.ShiroUtils;
import com.qh.water_management.modules.common.utils.Utils;
import com.qh.water_management.modules.dao.activiti.ExtendActModelDao;
import com.qh.water_management.modules.entity.activiti.ExtendActModelEntity;
import com.qh.water_management.modules.entity.sys.User;
import com.qh.water_management.modules.service.activiti.ExtendActModelService;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2019/1/10 11:05
 * @Description:
 */
@Service("extendActModelService")
public class ExtendActModelServiceImpl implements ExtendActModelService {

    @Autowired
    private ExtendActModelDao extendActModelDao;

    @Autowired
    private ActModelServiceImpl actModelService;

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public Map<String, Object> getPageSetData(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        List<ExtendActModelEntity> list = extendActModelDao.queryList(params);
        int total = extendActModelDao.queryTotal(params);
        result.put("rows", list);
        result.put("total", total);
        return result;
    }

    @Override
    public Map<String, Object> getDetailByUuid(String uuid) {
        return extendActModelDao.getDetailByUuid(uuid);
    }

    @Override
    @Transactional
    public String save(ExtendActModelEntity actModelEntity) throws Exception {
        actModelEntity.setId(Utils.uuid());
        User user = ShiroUtils.getUser();
        actModelEntity.setCreateId(user.getId());
        actModelEntity.setCreateTime(new Date());
        return actModelService.CreateModel(actModelEntity);
    }

    @Override
    @Transactional
    public int update(ExtendActModelEntity actModelEntity) {
        User user = ShiroUtils.getUser();
        actModelEntity.setUpdateId(user.getId());
        actModelEntity.setUpdateTime(new Date());
        return extendActModelDao.update(actModelEntity);
    }

    @Override
    @Transactional
    public void delete(String[] ids) {
        for (String id : ids) {
            ExtendActModelEntity actModelEntity = extendActModelDao.queryObject(id);

        }
    }
}
