package com.qh.water_management.modules.service.sys.impl;

import com.qh.water_management.modules.common.utils.UserUtils;
import com.qh.water_management.modules.common.utils.Utils;
import com.qh.water_management.modules.dao.sys.ConfigDao;
import com.qh.water_management.modules.entity.sys.BasicConfig;
import com.qh.water_management.modules.entity.sys.User;
import com.qh.water_management.modules.service.sys.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/11/30 16:09
 * @Description:
 */
@Service("configService")
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigDao configDao;

    @Override
    public Map<String, Object> getPageSetData(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        List<BasicConfig> list = configDao.queryList(params);
        int total = configDao.queryTotal(params);
        result.put("rows", list);
        result.put("total", total);
        return result;
    }

    @Override
    @Transactional
    public void save(BasicConfig basicConfig) throws Exception {
        basicConfig.setId(Utils.uuid());
        User user = UserUtils.getCurrentUser();
        basicConfig.setCreateId(user.getId());
        basicConfig.setCreateTime(new Date());
        basicConfig.setCreatorOrgId(user.getCreatorOrgId());
        if (configDao.save(basicConfig) != 1) {
            throw new Exception("保存失败!");
        }
    }

    @Override
    @Transactional
    public void update(BasicConfig basicConfig) throws Exception {
        User user = UserUtils.getCurrentUser();
        basicConfig.setUpdateId(user.getId());
        basicConfig.setUpdateTime(new Date());
        if (configDao.update(basicConfig) != 1) {
            throw new Exception("更新失败!");
        }
    }

    @Override
    @Transactional
    public void delete(Map<String, Object> params) throws Exception {
        String uuids = (String) params.get("id");
        String[] uuid_array = uuids.split(",");
        if (configDao.delete(uuid_array) != uuid_array.length) {
            throw new Exception("删除失败!");
        }
    }


}
