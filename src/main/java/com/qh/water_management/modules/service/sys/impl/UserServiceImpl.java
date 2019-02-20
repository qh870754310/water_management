package com.qh.water_management.modules.service.sys.impl;

import com.qh.water_management.modules.common.utils.ShiroUtils;
import com.qh.water_management.modules.common.utils.Utils;
import com.qh.water_management.modules.dao.sys.UserDao;
import com.qh.water_management.modules.entity.sys.User;
import com.qh.water_management.modules.service.sys.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/11/16 11:19
 * @Description:
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByLoginName(String userLoginName) {
        return userDao.findUserByLoginName(userLoginName);
    }

    @Override
    public List<Map<String, Object>> getListByKeywords() {
        return userDao.getListByKeywords();
    }

    @Override
    public Map<String, Object> getPageSet(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        List<User> list = userDao.queryList(params);
        int total = userDao.queryTotal(params);
        result.put("rows", list);
        result.put("total", total);
        return result;
    }

    @Override
    public Map<String, Object> getDetailByUuid(String uuid) {
        return userDao.getDetailByUuid(uuid);
    }

    @Override
    @Transactional
    public void save(User user) throws Exception {
        User currentUser = ShiroUtils.getUser();
        user.setId(Utils.uuid());
        String salt = RandomStringUtils.randomAlphanumeric(20);
        String password = ShiroUtils.EncodeSalt("123456", salt);
        user.setPassword(password);
        user.setSalt(salt);
        user.setCreateId(currentUser.getId());
        user.setCreateTime(new Date());
        if (userDao.save(user) != 1) {
            throw new Exception("保存失败！");
        }
    }

    @Override
    @Transactional
    public Integer update(User user) {
        User currentUser = ShiroUtils.getUser();
        user.setUpdateId(currentUser.getId());
        user.setUpdateTime(new Date());
        return userDao.update(user);
    }

    @Override
    @Transactional
    public void delete(@RequestParam User user) throws Exception {
        String id = user.getId();
        String[] ids = id.split(",");
        if (userDao.delete(ids) != ids.length) {
            throw new Exception("删除失败");
        }
    }

    @Override
    public List<?> getExportData() {
        return null;
    }

    @Override
    @Transactional
    public void updatePassword(User user) throws Exception {
        String salt = RandomStringUtils.randomAlphanumeric(20);
        String password = ShiroUtils.EncodeSalt(user.getPassword(), salt);
        user.setPassword(password);
        user.setSalt(salt);
        if (userDao.updatePassword(user) != 1) {
            throw new Exception("密码重置失败");
        }
    }
}
