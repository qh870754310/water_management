package com.qh.water_management.modules.service.sys.impl;

import com.qh.water_management.modules.common.utils.ShiroUtils;
import com.qh.water_management.modules.common.utils.Utils;
import com.qh.water_management.modules.dao.sys.CodeItemDao;
import com.qh.water_management.modules.entity.sys.CodeItem;
import com.qh.water_management.modules.entity.sys.User;
import com.qh.water_management.modules.service.sys.CodeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/12/12 14:05
 * @Description:
 */
@Service("codeItemService")
public class CodeItemServiceImpl implements CodeItemService {

    @Autowired
    private CodeItemDao codeItemDao;

    @Override
    public List<Map<String, Object>> getListByCodeSetIdAndLevelId(Map<String, Object> params) {
        return codeItemDao.getListByCodeSetIdAndLevelId(params);
    }

    @Override
    public List<Map<String, Object>> getListByLevelId(Map<String, Object> params) {
        return codeItemDao.getListByLevelId(params);
    }

    @Override
    public List<Map<String, Object>> getListByParentId(Map<String, Object> params) {
        return codeItemDao.getListByParentId(params);
    }

    @Override
    public String getFatherIds(String uuid) {
        return codeItemDao.getFatherIds(uuid);
    }

    @Override
    public Map<String, Object> getDetailByUuid(String uuid) {
        return codeItemDao.getDetailByUuid(uuid);
    }

    @Override
    @Transactional
    public void saveRoot(CodeItem codeItem) throws Exception {
        codeItem.setId(Utils.uuid());
        User user = ShiroUtils.getUser();
        codeItem.setCreateId(user.getId());
        codeItem.setCreateTime(new Date());
        codeItem.setLevelId(0);
        codeItem.setCreatorOrgId(user.getCreatorOrgId());
        if (codeItemDao.save(codeItem) != 1) {
            throw new Exception("保存失败！");
        }
    }

    @Override
    @Transactional
    public void save(CodeItem codeItem) throws Exception {
        codeItem.setId(Utils.uuid());
        User user = ShiroUtils.getUser();
        codeItem.setCreateId(user.getId());
        codeItem.setCreateTime(new Date());
        codeItem.setCreatorOrgId(user.getCreatorOrgId());
        codeItem.setLevelId(codeItem.getLevelId() + 1);
        if (codeItemDao.save(codeItem) != 1) {
            throw new Exception("保存失败！");
        }
    }

    @Override
    @Transactional
    public void update(CodeItem codeItem) throws Exception {
        User user = ShiroUtils.getUser();
        codeItem.setUpdateId(user.getId());
        codeItem.setUpdateTime(new Date());
        if (codeItemDao.update(codeItem) != 1) {
            throw new Exception("更新失败！");
        }
    }

    @Override
    @Transactional
    public void delete(Map<String, Object> params) throws Exception {
        String uuids = (String) params.get("id");
        String[] uuid_array = uuids.split(",");
        if (codeItemDao.delete(uuid_array) != uuid_array.length) {
            throw new Exception("删除失败");
        }
    }

    @Override
    public String getZoneFillbackData(String id) {
        return codeItemDao.getZoneFillbackData(id);
    }
}
