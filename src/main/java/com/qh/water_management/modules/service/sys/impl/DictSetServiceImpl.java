package com.qh.water_management.modules.service.sys.impl;

import com.qh.water_management.modules.common.utils.UserUtils;
import com.qh.water_management.modules.common.utils.Utils;
import com.qh.water_management.modules.dao.sys.DictSetDao;
import com.qh.water_management.modules.entity.sys.DictionaryCate;
import com.qh.water_management.modules.entity.sys.User;
import com.qh.water_management.modules.service.sys.DictSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/10/29 10:07
 * @Description:
 */
@Service("dictSetService")
public class DictSetServiceImpl implements DictSetService {

    @Autowired
    private DictSetDao dictSetDao;

    @Override
    public Map<String, Object> getPageSet(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        List<DictionaryCate> list = dictSetDao.queryList(params);
        int total = dictSetDao.queryTotal(params);
        result.put("rows", list);
        result.put("total", total);
        return result;
    }

    @Override
    @Transactional
    public void save(DictionaryCate dictionaryCate) throws Exception {
        dictionaryCate.setId(Utils.uuid());
        User user = UserUtils.getCurrentUser();
        dictionaryCate.setCreateId(user.getId());
        dictionaryCate.setCreateTime(new Date());
        dictionaryCate.setCreatorOrgId(user.getCreatorOrgId());
        if (dictSetDao.save(dictionaryCate) != 1) {
            throw new Exception("保存失败!");
        }
    }

    @Override
    @Transactional
    public void update(DictionaryCate dictionaryCate) throws Exception {
        User user = UserUtils.getCurrentUser();
        dictionaryCate.setUpdateId(user.getId());
        dictionaryCate.setUpdateTime(new Date());
        if (dictSetDao.update(dictionaryCate) != 1) {
            throw new Exception("更新失败!");
        }
    }

    @Override
    @Transactional
    public void delete(Map<String, Object> params) throws Exception {
        String uuids = (String) params.get("id");
        String[] uuid_array = uuids.split(",");
        if (dictSetDao.delete(uuid_array) != uuid_array.length) {
            throw new Exception("删除失败!");
        }
    }

    @Override
    public List<Map> getDicItemByCode(String code) {
        return dictSetDao.getDicItemByCode(code);
    }

}
