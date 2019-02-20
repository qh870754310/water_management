package com.qh.water_management.modules.dao.sys;

import com.qh.water_management.modules.common.dao.BaseDao;
import com.qh.water_management.modules.entity.sys.Organization;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/12/10 17:48
 * @Description:
 */
@Mapper
public interface OrganizationDao extends BaseDao<Organization> {

    List<Map<String, Object>> getListByLevelId(Integer levelId);

    List<Map<String, Object>> getListByParentId(String parentId);

    int saveRoot(Organization organization);

    String getFatherIds(String id);
}
