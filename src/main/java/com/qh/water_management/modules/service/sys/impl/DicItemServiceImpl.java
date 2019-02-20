package com.qh.water_management.modules.service.sys.impl;

import com.qh.water_management.modules.common.utils.UserUtils;
import com.qh.water_management.modules.common.utils.Utils;
import com.qh.water_management.modules.dao.sys.DicItemDao;
import com.qh.water_management.modules.dao.sys.DictSetDao;
import com.qh.water_management.modules.entity.sys.DictionaryDetail;
import com.qh.water_management.modules.entity.sys.User;
import com.qh.water_management.modules.service.sys.DicItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/10/29 13:08
 * @Description:
 */
@Service("dicItemService")
public class DicItemServiceImpl implements DicItemService {

    @Autowired
    private DicItemDao dicItemDao;

    @Autowired
    private DictSetDao dictSetDao;

    @Override
    public Map<String, Object> getPageSet(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        List<DictionaryDetail> list = dicItemDao.queryList(params);
        int total = dicItemDao.queryTotal(params);
        result.put("rows", list);
        result.put("total", total);
        return result;
    }

    @Override
    @Transactional
    public void save(DictionaryDetail dictionaryDetail, String puuid) throws Exception {
        dictionaryDetail.setId(Utils.uuid());
        User user = UserUtils.getCurrentUser();
        dictionaryDetail.setCreateId(user.getId());
        dictionaryDetail.setCreateTime(new Date());
        dictionaryDetail.setCreatorOrgId(user.getCreatorOrgId());
        dictionaryDetail.setPuuid(puuid);
        if (dicItemDao.save(dictionaryDetail) != 1) {
            throw new Exception("保存失败!");
        }
    }

    @Override
    @Transactional
    public void update(DictionaryDetail dictionaryDetail) throws Exception {
        User user = UserUtils.getCurrentUser();
        dictionaryDetail.setUpdateId(user.getId());
        dictionaryDetail.setUpdateTime(new Date());
        if (dicItemDao.update(dictionaryDetail) != 1) {
            throw new Exception("更新失败!");
        }
    }

    @Override
    @Transactional
    public void delete(Map<String, Object> params) throws Exception {
        String uuids = (String) params.get("id");
        String[] uuid_array = uuids.split(",");
        if (dicItemDao.delete(uuid_array) != uuid_array.length) {
            throw new Exception("删除失败!");
        }
    }
}
