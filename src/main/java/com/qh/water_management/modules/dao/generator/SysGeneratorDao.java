package com.qh.water_management.modules.dao.generator;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2019/1/7 10:22
 * @Description:
 */
@Mapper
public interface SysGeneratorDao {

    List<Map<String, Object>> getPageSet(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}
