package com.qh.water_management.modules.dao.sys;

import com.qh.water_management.modules.common.dao.BaseDao;
import com.qh.water_management.modules.entity.sys.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/11/16 17:41
 * @Description:
 */
@Mapper
public interface UserDao extends BaseDao<User> {

    User findUserByLoginName(String userLoginName);

    List<Map<String, Object>> getListByKeywords();

    int updatePassword(User user);
}
