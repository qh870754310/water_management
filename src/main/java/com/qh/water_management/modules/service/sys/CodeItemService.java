package com.qh.water_management.modules.service.sys;

import com.qh.water_management.modules.entity.sys.CodeItem;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/12/12 14:05
 * @Description:
 */
public interface CodeItemService {

    List<Map<String, Object>> getListByCodeSetIdAndLevelId(Map<String, Object> params);

    List<Map<String, Object>> getListByLevelId(Map<String, Object> params);

    List<Map<String, Object>> getListByParentId(Map<String, Object> params);

    String getFatherIds(String uuid);

    Map<String, Object> getDetailByUuid(String uuid);

    void saveRoot(CodeItem codeItem) throws Exception;

    void save(CodeItem codeItem) throws Exception;

    void update(CodeItem codeItem) throws Exception;

    void delete(Map<String, Object> params) throws Exception;

    String getZoneFillbackData(String id);
}
