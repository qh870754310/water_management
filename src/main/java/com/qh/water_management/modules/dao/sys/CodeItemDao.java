package com.qh.water_management.modules.dao.sys;

import com.qh.water_management.modules.common.dao.BaseDao;
import com.qh.water_management.modules.entity.sys.CodeItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/12/12 14:09
 * @Description:
 */
@Mapper
public interface CodeItemDao extends BaseDao<CodeItem> {

    List<Map<String, Object>> getListByCodeSetIdAndLevelId(Map<String, Object> params);

    List<Map<String, Object>> getListByLevelId(Map<String, Object> params);

    List<Map<String, Object>> getListByParentId(Map<String, Object> params);

    String getFatherIds(String uuid);

    String getZoneFillbackData(String id);
}
