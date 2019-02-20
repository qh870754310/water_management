package com.qh.water_management.modules.dao.sys;

import com.qh.water_management.modules.common.dao.BaseDao;
import com.qh.water_management.modules.entity.sys.AuthGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/12/12 18:06
 * @Description:
 */
@Mapper
public interface AuthGroupDao extends BaseDao<AuthGroup> {

    List<AuthGroup> getListByCodeSetIdAndLevelId(Map<String, Object> params);

    List<AuthGroup> getListByParentId(String parentId);

    List<Map<String, Object>> getListByLevelId(Integer levelId);
}
