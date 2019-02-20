package com.qh.water_management.modules.dao.activiti;

import com.qh.water_management.modules.common.dao.BaseDao;
import com.qh.water_management.modules.entity.activiti.ExtendActBusinessEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2019/1/9 14:52
 * @Description:  业务流程  对应的 业务表
 */
@Mapper
public interface ExtendActBusinessDao extends BaseDao<ExtendActBusinessEntity> {

    List<Map<String, Object>> getListByLevelId(Map<String, Object> params);

    List<Map<String, Object>> getListByActKeyAndLevelId(Map<String, Object> params);

    List<Map<String, Object>> getListByParentId(Map<String, Object> params);

    String getFatherIds(String uuid);
}
