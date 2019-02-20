package com.qh.water_management.modules.service.generator;

import java.util.List;
import java.util.Map;

/**
 * @Author: qh
 * @Date: 2019/1/7 10:18
 * @Description:
 */
public interface SysGeneratorService {

    List<Map<String, Object>> getPageSet(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    /**
     * 生成代码
     * @param tableNames 表名集
     * @param genType  生成方式，详见Constant枚举类 0本地 1 web形式
     * @return
     */
    byte[] generatorCode(String[] tableNames, int genType);

    Map<String, String>  queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}
