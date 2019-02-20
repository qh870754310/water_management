package com.qh.water_management.modules.service.sys;

import com.qh.water_management.modules.entity.sys.DictionaryDetail;

import java.util.Map;

/**
 * @Author: qh
 * @Date: 2018/10/29 13:07
 * @Description:
 */
public interface DicItemService {
    /**
     * 查询字典项
     * @param params
     * @return
     */
    Map<String, Object> getPageSet(Map<String, Object> params);

    /**
     * 保存
     * @param dictionaryDetail
     * @param code
     * @throws Exception
     */
    void save(DictionaryDetail dictionaryDetail, String code) throws Exception;

    /**
     * 编辑
     * @param dictionaryDetail
     * @throws Exception
     */
    void update(DictionaryDetail dictionaryDetail) throws Exception;

    /**
     * 删除
     * @param params
     * @throws Exception
     */
    void delete(Map<String, Object> params) throws Exception;
}
