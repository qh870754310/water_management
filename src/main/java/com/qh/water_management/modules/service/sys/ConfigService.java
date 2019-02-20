package com.qh.water_management.modules.service.sys;

import com.qh.water_management.modules.entity.sys.BasicConfig;

import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/11/30 16:09
 * @Description:
 */
public interface ConfigService {
    /**
     * 查询基础配置数据
     * @param params
     * @return
     */
    Map<String, Object> getPageSetData(Map<String, Object> params);

    /**
     * 添加
     * @param basicConfig
     * @throws Exception
     */
    void save(BasicConfig basicConfig) throws Exception;

    /**
     * 编辑
     * @param basicConfig
     * @throws Exception
     */
    void update(BasicConfig basicConfig) throws Exception;

    /**
     * 删除
     * @param params
     * @throws Exception
     */
    void delete(Map<String, Object> params) throws Exception;
}
