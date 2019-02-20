package com.qh.water_management.modules.dao.sys;

import com.qh.water_management.modules.common.dao.BaseDao;
import com.qh.water_management.modules.entity.sys.AuthData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/12/14 10:27
 * @Description:
 */
@Mapper
public interface SysAuthDataDao extends BaseDao<AuthData> {

    List<Map<String, Object>> getListByLevelId(Integer levelId);

    List<Map<String, Object>> getListByParentId(String parentId);
}
