package com.qh.water_management.modules.service.sys;

import com.qh.water_management.modules.entity.sys.User;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/11/16 11:17
 * @Description:
 */
public interface UserService {

    User findUserByLoginName(String userLoginName);

    List<Map<String, Object>> getListByKeywords();

    Map<String, Object> getPageSet(Map<String, Object> params);

    void save(User user) throws Exception;

    Map<String, Object> getDetailByUuid(String uuid);

    Integer update(User user);

    void delete(User user) throws Exception;

    List<?> getExportData();

    void updatePassword(User user) throws Exception;
}
